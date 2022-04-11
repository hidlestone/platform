package com.fallframework.platform.starter.activiti.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhuangpf
 */
public class ActivitiUtil {

	/**
	 * 读取文件为字符串
	 *
	 * @param filePath 文件路径
	 * @return 文件拼接字符
	 */
	public static String read(String filePath) {
		// 读取txt内容为字符串
		StringBuffer txtContent = new StringBuffer();
		// 每次读取的byte数
		byte[] b = new byte[8 * 1024];
		InputStream in = null;
		try {
			// 文件输入流
			in = new FileInputStream(filePath);
			while (in.read(b) != -1) {
				// 字符串拼接
				txtContent.append(new String(b));
			}
			// 关闭流
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return txtContent.toString();
	}
	
}
