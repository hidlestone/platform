package com.fallframework.platform.starter.activiti.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionRequest;
import com.fallframework.platform.starter.activiti.model.ProcessDefinitionResponse;
import com.fallframework.platform.starter.activiti.service.ActRepositoryService;
import com.fallframework.platform.starter.api.model.Leaf;
import com.fallframework.platform.starter.api.response.ResponseResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhuangpf
 */
@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ResponseResult bpmDeploy(String deploymentId) {
		// 查询对象
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition processDefinition = definitionQuery.processDefinitionId(deploymentId).singleResult();
		if (null == processDefinition) {
			return ResponseResult.fail("process definition is not exist");
		}
		// TODO 加载数据库中的文件
		Deployment deploy = repositoryService.createDeployment()
				.name(processDefinition.getName())
//				.addClasspathResource("bpmn/evection.bpmn")
//				.addClasspathResource("bpmn/evection.png")
				.deploy();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult<Leaf<ProcessDefinitionResponse>> getProcessDefinitionList(ProcessDefinitionRequest request) {
		// 查询对象
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		// 查询条件
		// id
		if (StringUtils.isNotEmpty(request.getId())) {
			definitionQuery.processDefinitionId(request.getId());
		}
		// name
		if (StringUtils.isNotEmpty(request.getName())) {
			definitionQuery.processDefinitionNameLike(request.getName());
		}
		// key
		if (StringUtils.isNotEmpty(request.getKey())) {
			definitionQuery.processDefinitionKeyLike(request.getKey());
		}
		// version
		if (null != request.getVersion()) {
			definitionQuery.processDefinitionVersion(request.getVersion());
		}
		// category
		if (StringUtils.isNotEmpty(request.getCategory())) {
			definitionQuery.processDefinitionCategoryLike(request.getCategory());
		}
		// deploymentId
		if (StringUtils.isNotEmpty(request.getDeploymentId())) {
			definitionQuery.deploymentId(request.getDeploymentId());
		}
		// resourceName
		if (StringUtils.isNotEmpty(request.getResourceName())) {
			definitionQuery.processDefinitionResourceNameLike(request.getResourceName());
		}
		int start = (request.getPageNum() - 1) * request.getPageSize() + 1;
		// 总记录数
		long total = definitionQuery.count();
		definitionQuery.listPage(start, request.getPageSize());
		List<ProcessDefinition> processDefinitionList = definitionQuery
				.orderByDeploymentId().desc()                // 部署ID降序
				.orderByProcessDefinitionVersion().desc()    // 部署版本号降序
				.list();                                     // 获取全部
		List<ProcessDefinitionResponse> responseList = this.ProcessDefinitionToResponse(processDefinitionList);
		Leaf<ProcessDefinitionResponse> leaf = new Leaf<>(responseList, total, (total / request.getPageSize()) + 1, request.getPageNum());
		return ResponseResult.success(leaf);
	}

	@Override
	public ResponseResult downloadBpmnFile(String definitionKey) {
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

	/**
	 * @param processDefinitionList 流程定义列表
	 * @return 响应列表
	 */
	private List<ProcessDefinitionResponse> ProcessDefinitionToResponse(List<ProcessDefinition> processDefinitionList) {
		List<ProcessDefinitionResponse> responseList = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(processDefinitionList)) {
			for (ProcessDefinition processDefinition : processDefinitionList) {
				ProcessDefinitionResponse response = new ProcessDefinitionResponse();
				response.setId(processDefinition.getId());
				response.setName(processDefinition.getName());
				response.setDescription(processDefinition.getDescription());
				response.setKey(processDefinition.getKey());
				response.setVersion(processDefinition.getVersion());
				response.setCategory(processDefinition.getCategory());
				response.setDeploymentId(processDefinition.getDeploymentId());
				response.setResourceName(processDefinition.getResourceName());
				response.setTenantId(processDefinition.getTenantId());
				response.setHistoryLevel(processDefinition.getVersion());
				response.setDiagramResourceName(processDefinition.getDiagramResourceName());
				response.setGraphicalNotationDefined(processDefinition.hasGraphicalNotation());
				response.setHasStartFormKey(processDefinition.hasStartFormKey());
				response.setEngineVersion(processDefinition.getEngineVersion());
				responseList.add(response);
			}
		}
		return responseList;
	}

}
