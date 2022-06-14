package com.fallframework.platform.starter.httpclient.util;

import com.fallframework.platform.starter.httpclient.config.HttpClientConfig;
import com.fallframework.platform.starter.httpclient.model.DownloadDto;
import com.fallframework.platform.starter.httpclient.model.HttpClientResult;
import com.fallframework.platform.starter.httpclient.model.UploadDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基础请求功能封装
 *
 * @author zhuangpf
 */
public class HttpClientUtil {

	/**
	 * 配置参数
	 */
	private static HttpClientConfig httpClientConfig = new HttpClientConfig();

	/**
	 * 请求配置
	 */
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(httpClientConfig.getSocketTimeout())
			.setConnectTimeout(httpClientConfig.getConnectTimeout())
			.setConnectionRequestTimeout(httpClientConfig.getConnectionRequestTimeout())
			.build();

	/**
	 * GET 不带请求头和请求参数
	 */
	public static HttpClientResult get(String url) throws Exception {
		return get(url, null, null);
	}

	/**
	 * GET 带请求参数
	 */
	public static HttpClientResult get(String url, Map<String, String> params) throws Exception {
		return get(url, null, params);
	}

	/**
	 * GET 带请求头和请求参数
	 *
	 * @param url     url
	 * @param headers 请求头
	 * @param params  请求参数
	 * @return HttpClientResult
	 * @throws Exception
	 */
	public static HttpClientResult get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建访问的地址
		URIBuilder uriBuilder = new URIBuilder(url);
		if (params != null) {
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue());
			}
		}
		// 创建http对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		// 默认配置
		httpGet.setConfig(requestConfig);
		// 设置请求头
		packageHeader(headers, httpGet);
		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;
		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpGet);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}

	/**
	 * POST 不带请求头和请求参数
	 */
	public static HttpClientResult post(String url) throws Exception {
		return post(url, null, null);
	}

	/**
	 * POST 带请求参数
	 */
	public static HttpClientResult post(String url, Map<String, String> params) throws Exception {
		return post(url, null, params);
	}

	/**
	 * POST 带请求头和请求参数
	 */
	private static HttpClientResult post(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建http对象
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		// 设置请求头
		packageHeader(headers, httpPost);
		// 封装请求参数
		packageParam(params, httpPost);
		// 创建httpResponse对象
		CloseableHttpResponse httpResponse = null;
		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}

	/**
	 * PUT 不带请求参数
	 */
	public static HttpClientResult put(String url) throws Exception {
		return put(url, null);
	}

	/**
	 * PUT 带请求参数
	 */
	private static HttpClientResult put(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setConfig(requestConfig);
		packageParam(params, httpPut);
		CloseableHttpResponse httpResponse = null;
		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpPut);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}

	/**
	 * DELETE 不带请求参数
	 */
	public static HttpClientResult delete(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.setConfig(requestConfig);
		CloseableHttpResponse httpResponse = null;
		try {
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpDelete);
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
	}

	/**
	 * DELETE 带请求参数
	 */
	public static HttpClientResult delete(String url, Map<String, String> params) throws Exception {
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put("_method", "delete");
		return post(url, params);
	}

	/**
	 * 获得响应结果
	 */
	public static HttpClientResult getHttpClientResult(CloseableHttpResponse httpResponse,
													   CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws Exception {
		// 执行请求
		httpResponse = httpClient.execute(httpMethod);
		// 获取返回结果
		if (httpResponse != null && httpResponse.getStatusLine() != null) {
			String content = "";
			if (httpResponse.getEntity() != null) {
				content = EntityUtils.toString(httpResponse.getEntity(), httpClientConfig.getEncoding());
			}
			return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
		}
		return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	/**
	 * 封装请求参数
	 *
	 * @param params
	 * @param httpMethod
	 * @throws UnsupportedEncodingException
	 */
	public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
			throws UnsupportedEncodingException {
		// 封装请求参数
		if (params != null) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 设置到请求的http对象中
			httpMethod.setEntity(new UrlEncodedFormEntity(nvps, httpClientConfig.getEncoding()));
		}
	}

	/**
	 * 封装请求头
	 *
	 * @param headers
	 * @param httpMethod
	 */
	public static void packageHeader(Map<String, String> headers, HttpRequestBase httpMethod) {
		// 封装请求头
		if (null != headers && headers.size() > 0) {
			Set<Map.Entry<String, String>> entrySet = headers.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				// 设置到请求头到HttpRequestBase对象中
				httpMethod.setHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 释放资源
	 */
	public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
		// 释放资源
		if (null != httpResponse) {
			httpResponse.close();
		}
		if (null != httpClient) {
			httpClient.close();
		}
	}

	/**
	 * 文件上传
	 */
	public static HttpClientResult upload(UploadDto dto) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		try {
			// 创建http对象
			HttpPost httpPost = new HttpPost(dto.getUrl());
			// HttpMultipartMode.RFC6532参数的设定是为避免文件名为中文时乱码
			MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
			// 设置请求头
			packageHeader(dto.getHeaders(), httpPost);
			// 上传的文件
			File file = new File(dto.getPath() + dto.getFileName());
			builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, dto.getFileName());
			// 封装请求参数
			packageParam(dto.getParams(), httpPost);
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			// 执行请求并获得响应结果
			return getHttpClientResult(httpResponse, httpClient, httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			release(httpResponse, httpClient);
		}
		return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	/**
	 * 文件下载
	 */
	public static HttpClientResult download(DownloadDto dto) throws IOException {

		
		return null;
	}

}
