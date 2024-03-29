package com.fallframework.platform.starter.activiti.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试用
 * 流程页面访问的入口，对流程进行初始化
 *
 * @author zhuangpf
 */
@RestController
@RequestMapping("/activiti-explorer/model")
public class ActivitiModelerController {

	/**
	 * 访问：localhost:9910/activiti-explorer/model/create
	 * 跳转：http://localhost:9910/modeler.html?modelId=xxx
	 * 创建基本模型
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("/create")
	public void createModel(HttpServletRequest request, HttpServletResponse response) {
		try {
			String modelName = "modelName";
			String modelKey = "modelKey";
			String description = "description";

			ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

			RepositoryService repositoryService = processEngine.getRepositoryService();

			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			// 定义新模型
			Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(modelName);
			modelData.setKey(modelKey);

			// 保存模型
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
		} catch (Exception e) {
		}
	}

}
