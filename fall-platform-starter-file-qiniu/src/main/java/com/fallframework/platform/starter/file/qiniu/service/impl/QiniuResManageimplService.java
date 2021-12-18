package com.fallframework.platform.starter.file.qiniu.service.impl;

import com.fallframework.platform.starter.file.qiniu.config.QiniuPropertyResource;
import com.fallframework.platform.starter.file.qiniu.service.QiniuResManageService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.processing.OperationStatus;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源管理
 *
 * @author zhuangpf
 */
@Service
public class QiniuResManageimplService implements QiniuResManageService {

	@Autowired
	private QiniuPropertyResource qiniuPropertyResource;

	/**
	 * 获取文件信息
	 */
	public FileInfo getFileInfo() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		String accessKey = "your access key";
		String secretKey = "your secret key";
		String bucket = "your bucket name";
		String key = "your file key";
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		FileInfo fileInfo = null;
		try {
			fileInfo = bucketManager.stat(bucket, key);
			System.out.println(fileInfo.hash);
			System.out.println(fileInfo.fsize);
			System.out.println(fileInfo.mimeType);
			System.out.println(fileInfo.putTime);
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
		return fileInfo;
	}

	/**
	 * 修改文件类型
	 */
	public Response changeFileMime() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		String bucket = "your bucket name";
		String key = "your file key";
		String newMimeType = "new mime type";
		// 修改文件类型
		Response response = null;
		try {
			response = bucketManager.changeMime(bucket, key, newMimeType);
		} catch (QiniuException ex) {
			System.out.println(ex.response.toString());
		}
		return response;
	}

	/**
	 * 移动或重命名文件
	 */
	public Response moveRenameFile() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		String fromBucket = "from bucket name";
		String fromKey = "from key";
		String toBucket = "to bucket name";
		String toKey = "to key";
		Response response = null;
		try {
			response = bucketManager.move(fromBucket, fromKey, toBucket, toKey);
		} catch (QiniuException ex) {
			//如果遇到异常，说明移动失败
			System.err.println(ex.code());
			System.err.println(ex.response.toString());
		}
		return response;
	}

	/**
	 * 复制文件副本
	 * 文件的复制和文件移动其实操作一样，主要的区别是移动后源文件不存在了，而复制的结果是源文件还存在，只是多了一个新的文件副本。
	 */
	public Response copyFile() {
		// 构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		String fromBucket = "from bucket name";
		String fromKey = "from key";
		String toBucket = "to bucket name";
		String toKey = "to key";
		Response response = null;
		try {
			response = bucketManager.copy(fromBucket, fromKey, toBucket, toKey);
		} catch (QiniuException ex) {
			//如果遇到异常，说明复制失败
			System.err.println(ex.code());
		}
		return response;
	}

	/**
	 * 删除空间中的文件
	 */
	public Response deleteFile() {
		// 构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		String bucket = "your bucket name";
		String key = "your file key";
		Response response = null;
		try {
			response = bucketManager.delete(bucket, key);
		} catch (QiniuException ex) {
			//如果遇到异常，说明删除失败
			System.err.println(ex.code());
			System.err.println(ex.response.toString());
		}
		return response;
	}

	/**
	 * 设置或更新文件的生存时间
	 */
	public Response setFileExpire() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		String key = "file key";
		//过期天数，该文件10天后删除
		int days = 10;
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		Response response = null;
		try {
			response = bucketManager.deleteAfterDays(qiniuPropertyResource.getBucket(), key, days);
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
		return response;
	}

	/**
	 * 获取空间文件列表 TODO
	 */
	public void getFileList() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		//文件名前缀
		String prefix = "";
		//每次迭代的长度限制，最大1000，推荐值 1000
		int limit = 1000;
		//指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
		String delimiter = "";
		//列举空间文件列表
		BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiniuPropertyResource.getBucket(), prefix, limit, delimiter);
		while (fileListIterator.hasNext()) {
			//处理获取的file list结果
			FileInfo[] items = fileListIterator.next();
			for (FileInfo item : items) {
				System.out.println(item.key);
				System.out.println(item.hash);
				System.out.println(item.fsize);
				System.out.println(item.mimeType);
				System.out.println(item.putTime);
				System.out.println(item.endUser);
			}
		}
	}

	/**
	 * 抓取网络资源到空间
	 */
	public void getNetResToSpace() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);

		String bucket = "your bucket name";
		String key = "your file key";
		String remoteSrcUrl = "http://devtools.qiniu.com/qiniu.png";

		//抓取网络资源到空间
		try {
			FetchRet fetchRet = bucketManager.fetch(remoteSrcUrl, bucket, key);
			System.out.println(fetchRet.hash);
			System.out.println(fetchRet.key);
			System.out.println(fetchRet.mimeType);
			System.out.println(fetchRet.fsize);
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	/* 资源管理批量操作 */

	/**
	 * 批量获取文件信息
	 */
	public void getBatchFileInfo() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);

		try {
			//单次批量请求的文件数量不得超过1000
			String[] keyList = new String[]{
					"qiniu.jpg",
					"qiniu.mp4",
					"qiniu.png",
			};
			BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
			batchOperations.addStatOps(qiniuPropertyResource.getBucket(), keyList);
			Response response = bucketManager.batch(batchOperations);
			BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
			for (int i = 0; i < keyList.length; i++) {
				BatchStatus status = batchStatusList[i];
				String key = keyList[i];
				System.out.print(key + "\t");
				if (status.code == 200) {
					//文件存在
					System.out.println(status.data.hash);
					System.out.println(status.data.mimeType);
					System.out.println(status.data.fsize);
					System.out.println(status.data.putTime);
				} else {
					System.out.println(status.data.error);
				}
			}
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	/**
	 * 批量修改文件类型
	 */
	public void updateBatchFileMime() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			//单次批量请求的文件数量不得超过1000
			HashMap<String, String> keyMimeMap = new HashMap<>();
			keyMimeMap.put("qiniu.jpg", "image/jpg");
			keyMimeMap.put("qiniu.png", "image/png");
			keyMimeMap.put("qiniu.mp4", "video/mp4");
			BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
			//添加指令
			for (Map.Entry<String, String> entry : keyMimeMap.entrySet()) {
				String key = entry.getKey();
				String newMimeType = entry.getValue();
				batchOperations.addChgmOp(qiniuPropertyResource.getBucket(), key, newMimeType);
			}
			Response response = bucketManager.batch(batchOperations);
			BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
			int index = 0;
			for (Map.Entry<String, String> entry : keyMimeMap.entrySet()) {
				String key = entry.getKey();
				System.out.print(key + "\t");
				BatchStatus status = batchStatusList[index];
				if (status.code == 200) {
					System.out.println("change mime success");
				} else {
					System.out.println(status.data.error);
				}
				index += 1;
			}
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	public void deleteBatch() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			//单次批量请求的文件数量不得超过1000
			String[] keyList = new String[]{
					"qiniu.jpg",
					"qiniu.mp4",
					"qiniu.png",
			};
			BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
			batchOperations.addDeleteOp(qiniuPropertyResource.getBucket(), keyList);
			Response response = bucketManager.batch(batchOperations);
			BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
			for (int i = 0; i < keyList.length; i++) {
				BatchStatus status = batchStatusList[i];
				String key = keyList[i];
				System.out.print(key + "\t");
				if (status.code == 200) {
					System.out.println("delete success");
				} else {
					System.out.println(status.data.error);
				}
			}
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	public void moveRenameBatch() {
		Configuration cfg = new Configuration(Region.region0());
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			//单次批量请求的文件数量不得超过1000
			String[] keyList = new String[]{
					"qiniu.jpg",
					"qiniu.mp4",
					"qiniu.png",
			};
			BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
			for (String key : keyList) {
				batchOperations.addMoveOp(qiniuPropertyResource.getBucket(), key, qiniuPropertyResource.getBucket(), key + "_move");
			}
			Response response = bucketManager.batch(batchOperations);
			BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
			for (int i = 0; i < keyList.length; i++) {
				BatchStatus status = batchStatusList[i];
				String key = keyList[i];
				System.out.print(key + "\t");
				if (status.code == 200) {
					System.out.println("move success");
				} else {
					System.out.println(status.data.error);
				}
			}
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	/**
	 * 批量复制文件
	 */
	public void copyBatch() {
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			//单次批量请求的文件数量不得超过1000
			String[] keyList = new String[]{
					"qiniu.jpg",
					"qiniu.mp4",
					"qiniu.png",
			};
			BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
			for (String key : keyList) {
				batchOperations.addCopyOp(qiniuPropertyResource.getBucket(), key, qiniuPropertyResource.getBucket(), key + "_copy");
			}
			Response response = bucketManager.batch(batchOperations);
			BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
			for (int i = 0; i < keyList.length; i++) {
				BatchStatus status = batchStatusList[i];
				String key = keyList[i];
				System.out.print(key + "\t");
				if (status.code == 200) {
					System.out.println("copy success");
				} else {
					System.out.println(status.data.error);
				}
			}
		} catch (QiniuException ex) {
			System.err.println(ex.response.toString());
		}
	}

	/**
	 * 更新镜像存储空间中文件内容
	 */
	public Response synMirrorSapceFile() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());

		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		BucketManager bucketManager = new BucketManager(auth, cfg);

		String key = "your file key";
		Response response = null;
		try {
			response = bucketManager.prefetch(qiniuPropertyResource.getBucket(), key);
		} catch (QiniuException ex) {
			//如果遇到异常，说明更新失败
			System.err.println(ex.code());
			System.err.println(ex.response.toString());
		}
		return response;
	}

	/**
	 * 发送数据处理请求
	 * 对于已经保存到七牛空间的文件，可以通过发送持久化的数据处理指令来进行处理，这些指令支持七牛官方提供的指令，也包括客户自己开发的自定义数据处理的指令。数据处理的结果还可以通过七牛主动通知的方式告知业务服务器。
	 */
	public void dataProcess() {
		Auth auth = Auth.create(qiniuPropertyResource.getAccessKey(), qiniuPropertyResource.getSecretKey());
		//数据处理指令，支持多个指令
		String saveMp4Entry = String.format("%s:avthumb_test_target.mp4", qiniuPropertyResource.getBucket());
		String saveJpgEntry = String.format("%s:vframe_test_target.jpg", qiniuPropertyResource.getBucket());
		String avthumbMp4Fop = String.format("avthumb/mp4|saveas/%s", UrlSafeBase64.encodeToString(saveMp4Entry));
		String vframeJpgFop = String.format("vframe/jpg/offset/1|saveas/%s", UrlSafeBase64.encodeToString(saveJpgEntry));
		//将多个数据处理指令拼接起来
		String persistentOpfs = StringUtils.join(new String[]{
				avthumbMp4Fop, vframeJpgFop
		}, ";");
		//数据处理队列名称，必须
		String persistentPipeline = "mps-pipe1";
		//数据处理完成结果通知地址
		String persistentNotifyUrl = "http://api.example.com/qiniu/pfop/notify";
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region0());
		//...其他参数参考类注释
		//待处理文件名
		String key = "file key";
		//构建持久化数据处理对象
		OperationManager operationManager = new OperationManager(auth, cfg);
		try {
			String persistentId = operationManager.pfop(qiniuPropertyResource.getBucket(), key, persistentOpfs, persistentPipeline, persistentNotifyUrl, true);
			//可以根据该 persistentId 查询任务处理进度
			System.out.println(persistentId);
			OperationStatus operationStatus = operationManager.prefop(persistentId);
			//解析 operationStatus 的结果
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}


}
