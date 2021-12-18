package com.fallframework.platform.starter.file.qiniu.service.impl;

import com.fallframework.platform.starter.file.qiniu.config.QiniuPropertyResource;
import com.fallframework.platform.starter.file.qiniu.service.QiniuCdnRelateService;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CDN 相关功能
 *
 * @author zhuangpf
 */
@Service
public class QiniuCdnRelateServiceImpl implements QiniuCdnRelateService {

	@Autowired
	private QiniuPropertyResource qiniuPropertyResource;

	/**
	 * 文件刷新
	 */
	public void refreshFile() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		CdnManager c = new CdnManager(auth);
		//待刷新的链接列表
		String[] urls = new String[]{
				"http://javasdk.qiniudn.com/gopher1.jpg",
				"http://javasdk.qiniudn.com/gopher2.jpg",
				//....
		};
		try {
			//单次方法调用刷新的链接不可以超过100个
			CdnResult.RefreshResult result = c.refreshUrls(urls);
			System.out.println(result.code);
			//获取其他的回复内容
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 目录刷新
	 */
	public void refreshDir() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		CdnManager c = new CdnManager(auth);
		//待刷新的目录列表，目录必须以 / 结尾
		String[] dirs = new String[]{
				"http://javasdk.qiniudn.com/gopher1/",
				"http://javasdk.qiniudn.com/gopher2/",
				//....
		};
		try {
			//单次方法调用刷新的目录不可以超过10个，另外刷新目录权限需要联系技术支持开通
			CdnResult.RefreshResult result = c.refreshDirs(dirs);
			System.out.println(result.code);
			//获取其他的回复内容
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 刷新查询
	 */
	public static void getRefreshResult() {
		String accessKey = "";
		String secretKey = "";
		String url = "http://fusion.qiniuapi.com/v2/tune/refresh/list";
		String body = "{\"urls\":[\"http://p9w3dsbag.bkt.clouddn.com/demo.html\"]}";
		Auth auth = Auth.create(accessKey, secretKey);
		StringMap str = auth.authorization(url, body.getBytes(), Client.JsonMime);
		Client client = new Client();
		try {
			Response r = client.post(url, body.getBytes(), str);
			System.out.println(r.reqId);
			System.out.println(r.bodyString());
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文件预取
	 */
	public void filePrefetch() {
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		CdnManager c = new CdnManager(auth);
//待预取的文件链接
		String[] urls = new String[]{
				"http://javasdk.qiniudn.com/gopher1.jpg",
				"http://javasdk.qiniudn.com/gopher2.jpg",
				//...
		};
		try {
			//单次调用方法预取的链接数量不得超过100个
			CdnResult.PrefetchResult result = c.prefetchUrls(urls);
			System.out.println(result.code);
			//获取其他的回复内容
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 获取域名流量
	 */
	public void getFluxData() {
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		CdnManager c = new CdnManager(auth);
		//域名列表
		String[] domains = new String[]{
				"img1.example.com",
				"img2.example.com",
		};
		//开始和结束日期
		String fromDate = "2017-01-02";
		String toDate = "2017-01-10";
		//数据粒度，支持的取值为 5min ／ hour ／day
		String granularity = "day";
		try {
			CdnResult.FluxResult fluxResult = c.getFluxData(domains, fromDate, toDate, granularity);
			//处理得到的结果数据
			//...
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 获取域名带宽
	 */
	public void getBandwidthData() {
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		CdnManager c = new CdnManager(auth);
		//域名列表
		String[] domains = new String[]{
				"img1.example.com",
				"img2.example.com",
		};
		//开始和结束日期
		String fromDate = "2017-01-02";
		String toDate = "2017-01-10";
		//数据粒度，支持的取值为 5min ／ hour ／day
		String granularity = "day";
		try {
			CdnResult.BandwidthResult bandwidthResult = c.getBandwidthData(domains, fromDate, toDate, granularity);
			//处理得到的结果数据
			//...
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 获取日志下载链接
	 */
	public void getCdnLogList() {
		String accessKey = "your access key";
		String secretKey = "your secret key";
		Auth auth = Auth.create(accessKey, secretKey);
		CdnManager c = new CdnManager(auth);
		//域名列表
		String[] domains = new String[]{
				"img1.example.com",
				"img2.example.com",
		};
		//具体日期
		String logDate = "2017-01-02";
		try {
			CdnResult.LogListResult logListResult = c.getCdnLogList(domains, logDate);
			//处理得到的结果数据
			//...
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}

	/**
	 * 构建时间戳防盗链访问链接
	 */
	public void createTimestampAntiLeechUrl() {
		String host = "http://video.example.com";
		String fileName = "基本概括.mp4";
		//查询参数
		StringMap queryStringMap = new StringMap();
		queryStringMap.put("name", "七牛");
		queryStringMap.put("year", 2017);
		queryStringMap.put("年龄", 28);
		//链接过期时间
		long deadline = System.currentTimeMillis() / 1000 + 3600;
		//签名密钥，从后台域名属性中获取
		String encryptKey = "xxx";
		String signedUrl;
		try {
			signedUrl = CdnManager.createTimestampAntiLeechUrl(host, fileName,
					queryStringMap, encryptKey, deadline);
			System.out.println(signedUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
