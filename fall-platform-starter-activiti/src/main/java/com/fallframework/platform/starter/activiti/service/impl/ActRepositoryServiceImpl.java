package com.fallframework.platform.starter.activiti.service.impl;

import com.fallframework.platform.starter.activiti.model.ModelQueryRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionQueryRequest;
import com.fallframework.platform.starter.activiti.model.StartProcessResponse;
import com.fallframework.platform.starter.activiti.service.ActRepositoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author zhuangpf
 */
@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActRepositoryServiceImpl.class);

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private FormService formService;
	;


	@Override
	public ResponseResult deployByInputStream(String resourceName, InputStream inputStream) {
		Deployment deploy = repositoryService.createDeployment().addInputStream(resourceName, inputStream).deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deployByClasspathResource(String resource) {
		Deployment deploy = repositoryService.createDeployment().addClasspathResource(resource).deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deployByString(String resourceName, String str) {
		Deployment deploy = repositoryService.createDeployment().addString(resourceName, str).deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deployByZipInputStream(ZipInputStream zipInputStream) {
		Deployment deploy = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deployByBpmnModel(String resourceName, BpmnModel bpmnModel) {
		Deployment deploy = repositoryService.createDeployment().addBpmnModel(resourceName, bpmnModel).deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deployByBpmnName(String bpmnName) {
		String bpmn = "processes/" + bpmnName + ".xml";
		String png = "processes/" + bpmnName + ".png";
		LOGGER.debug(String.format("deployByBpmnName xml:%s, png", bpmn, png));
		Deployment deploy = repositoryService.createDeployment()
				.name(bpmnName)
				.addInputStream(bpmn, this.getClass().getClassLoader().getResourceAsStream(bpmn))
				.deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deleteDeployment(String deploymentId) {
		// 非级联删除
		// repositoryService.deleteDeployment(deploymentId);
		// 级联删除
		repositoryService.deleteDeployment(deploymentId, true);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<Leaf<ProcessDefinition>> getProcessDefinitionList(ProcessDefinitionQueryRequest request) {
		// 查询对象
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		// 查询条件
		// id
		if (StringUtils.isNotEmpty(request.getId())) {
			definitionQuery.processDefinitionId(request.getId());
		}
		if (!CollectionUtils.isEmpty(request.getIds())) {
			definitionQuery.deploymentIds(request.getIds());
		}
		if (StringUtils.isNotEmpty(request.getCategory())) {
			definitionQuery.processDefinitionCategory(request.getCategory());
		}
		if (StringUtils.isNotEmpty(request.getCategoryLike())) {
			definitionQuery.processDefinitionCategoryLike(request.getCategoryLike());
		}
		if (StringUtils.isNotEmpty(request.getCategoryNotEquals())) {
			definitionQuery.processDefinitionCategoryNotEquals(request.getCategoryNotEquals());
		}
		// name
		if (StringUtils.isNotEmpty(request.getName())) {
			definitionQuery.processDefinitionNameLike(request.getName());
		}
		if (StringUtils.isNotEmpty(request.getNameLike())) {
			definitionQuery.processDefinitionNameLike(request.getNameLike());
		}
		// deploymentId
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			definitionQuery.deploymentId(request.getDeploymentId());
		}
		if (!CollectionUtils.isEmpty(request.getDeploymentIds())) {
			definitionQuery.deploymentIds(request.getDeploymentIds());
		}
		// key
		if (StringUtils.isNotEmpty(request.getKey())) {
			definitionQuery.processDefinitionKey(request.getKey());
		}
		if (StringUtils.isNotEmpty(request.getKeyLike())) {
			definitionQuery.processDefinitionKeyLike(request.getKeyLike());
		}
		if (StringUtils.isNotEmpty(request.getResourceName())) {
			definitionQuery.processDefinitionResourceName(request.getResourceName());
		}
		if (StringUtils.isNotEmpty(request.getResourceNameLike())) {
			definitionQuery.processDefinitionResourceNameLike(request.getResourceNameLike());
		}
		// version
		if (null != request.getVersion()) {
			definitionQuery.processDefinitionVersion(request.getVersion());
		}
		if (null != request.getVersionGt()) {
			definitionQuery.processDefinitionVersionGreaterThan(request.getVersionGt());
		}
		if (null != request.getVersionGte()) {
			definitionQuery.processDefinitionVersionGreaterThanOrEquals(request.getVersionGte());
		}
		if (null != request.getVersionLt()) {
			definitionQuery.processDefinitionVersionLowerThan(request.getVersionLt());
		}
		if (null != request.getVersionLte()) {
			definitionQuery.processDefinitionVersionLowerThanOrEquals(request.getVersionLte());
		}
		if (null != request.getLatest()) {
			definitionQuery.latestVersion();
		}
		if ("active".equals(request.getSuspensionState().getStateCode())) {
			definitionQuery.active();
		}
		if ("suspended".equals(request.getSuspensionState().getStateCode())) {
			definitionQuery.suspended();
		}
		if (StringUtils.isNotEmpty(request.getTenantId())) {
			definitionQuery.processDefinitionTenantId(request.getTenantId());
		}
		if (StringUtils.isNotEmpty(request.getTenantIdLike())) {
			definitionQuery.processDefinitionTenantIdLike(request.getTenantIdLike());
		}
		if (true == request.getWithoutTenantId()) {
			definitionQuery.processDefinitionWithoutTenantId();
		}
		if (StringUtils.isNotEmpty(request.getEventSubscriptionName())) {
			definitionQuery.messageEventSubscriptionName(request.getEventSubscriptionName());
		}
		// 总记录数
		long total = definitionQuery.count();
		List<ProcessDefinition> processDefinitionList = definitionQuery
				.orderByDeploymentId().desc()                                // 部署ID降序
				.orderByProcessDefinitionVersion().desc()                    // 部署版本号降序
				.listPage(request.getFirstRow(), request.getPageSize());     // 分页
		Leaf<ProcessDefinition> leaf = new Leaf<>(processDefinitionList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult downloadBpmnile(String definitionKey) {
		InputStream fis = null;
		BufferedInputStream bis = null;
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey(definitionKey)
					.singleResult();
			// 通过流程定义信息，获取部署ID
			String deploymentId = processDefinition.getDeploymentId();
			// 从流程定义表中，获取png图片的目录和名字
			String pngName = processDefinition.getDiagramResourceName();
			// 通过部署id和 文件名字来获取图片的资源
			InputStream pngInput = repositoryService.getResourceAsStream(deploymentId, pngName);
			// 获取bpmn的流
			String bpmnName = processDefinition.getResourceName();
			InputStream bpmnInput = repositoryService.getResourceAsStream(deploymentId, bpmnName);
			// 获取response
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			// 压缩流
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			// 压缩文件的名称
			String downloadFilename = bpmnName + ".zip";
			// 转换中文否则可能会产生乱码
			downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			// response.setContentLength((int) file.length());
			// 设置强制下载不打开
			response.setContentType("application/force-download");
			// 设置文件名
			response.addHeader("Content-Disposition", "attachment;fileName=" + downloadFilename);
			// buffer
			byte[] buffer = new byte[1024];
			// 1、png 文件
			// 设置打包文件名称
			zos.putNextEntry(new ZipEntry(pngName));
			// 设置注释
			// zos.setComment("hello");
			fis = pngInput;
			bis = new BufferedInputStream(fis);
			int i = bis.read(buffer);
			while (i != -1) {
				zos.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			zos.closeEntry();
			// 2、bpm 文件
			zos.putNextEntry(new ZipEntry(bpmnName));
			fis = bpmnInput;
			bis = new BufferedInputStream(fis);
			i = bis.read(buffer);
			while (i != -1) {
				zos.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			zos.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 做关闭操作
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<Leaf<Model>> getModelList(ModelQueryRequest request) {
		// 查询条件
		ModelQuery modelQuery = repositoryService.createModelQuery();
		if (StringUtils.isNotEmpty(request.getId())) {
			modelQuery.modelId(request.getId());
		}
		if (StringUtils.isNotEmpty(request.getCategory())) {
			modelQuery.modelCategory(request.getCategory());
		}
		if (StringUtils.isNotEmpty(request.getCategoryLike())) {
			modelQuery.modelCategoryLike(request.getCategoryLike());
		}
		if (StringUtils.isNotEmpty(request.getCategoryNotEquals())) {
			modelQuery.modelCategoryNotEquals(request.getCategoryNotEquals());
		}
		if (StringUtils.isNotEmpty(request.getName())) {
			modelQuery.modelName(request.getName());
		}
		if (StringUtils.isNotEmpty(request.getNameLike())) {
			modelQuery.modelNameLike(request.getNameLike());
		}
		if (StringUtils.isNotEmpty(request.getKey())) {
			modelQuery.modelKey(request.getKey());
		}
		if (null != request.getVersion()) {
			modelQuery.modelVersion(request.getVersion());
		}
		if (null != request.getLatest()) {
			modelQuery.latestVersion();
		}
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			modelQuery.deploymentId(request.getDeploymentId());
		}
		if (null != request.getNotDeployed()) {
			modelQuery.notDeployed();
		}
		if (null != request.getDeployed()) {
			modelQuery.deployed();
		}
		if (StringUtils.isNotEmpty(request.getTenantId())) {
			modelQuery.modelTenantId(request.getTenantId());
		}
		if (StringUtils.isNotEmpty(request.getTenantIdLike())) {
			modelQuery.modelTenantIdLike(request.getTenantIdLike());
		}
		if (true == request.getWithoutTenantId()) {
			modelQuery.modelWithoutTenantId();
		}
		// 总记录数
		long total = modelQuery.count();
		// 分页数据
		List<Model> modelList = modelQuery.orderByCreateTime().desc().listPage(request.getFirstRow(), request.getPageSize());
		Leaf<Model> leaf = new Leaf(modelList, total, request);
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult deployByModelId(String modelId) throws IOException {
		// 获取模型
		Model modelData = repositoryService.getModel(modelId);
		if (null == modelData) {
			return ResponseResult.fail("模型" + modelId + "不存在");
		}
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
		JsonNode modelNode = modelNode = new ObjectMapper().readTree(bytes);
		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0) {
			return ResponseResult.fail("数据模型不符要求，请至少设计一条主线流程");
		}
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
		// 发布流程
		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment()
				.name(modelData.getName())
				.addString(processName, new String(bpmnBytes, "UTF-8"))
				.deploy();
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<StartProcessResponse> startProcessInfo(String procDefId) {
		// 按照流程定义ID加载流程开启时候需要的表单信息
		StartFormData startFormData = formService.getStartFormData(procDefId);
		List<FormProperty> formProperties = startFormData.getFormProperties();
		StartProcessResponse response = new StartProcessResponse(procDefId, formProperties);
		return ResponseResult.success(response);
	}

	@Override
	public ResponseResult<String> generateDiagram(String processInstanceId) {
		return null;
	}


}
