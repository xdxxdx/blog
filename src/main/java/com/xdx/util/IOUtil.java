package com.xdx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class IOUtil {
	static{
		System.out.println("加载IOUtil");
	}
	public IOUtil(){
		System.out.println("实例化IOUtil");
	}
	private final static int BUFFER_SIZE = 16 * 1024;

	/**
	 * 将数据流从源文件传输到目标文件
	 * 
	 * @param fromFile
	 * @param toFile
	 */
	public static void copy(File fromFile, File toFile) {
		InputStream in = null;
		BufferedOutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(fromFile),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(toFile),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			try {
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param realPath
	 */
	public static void deleteFile(HttpServletRequest request, String realPath) {
		// 得到文件绝对路径
		realPath = request.getServletContext().getRealPath("/" + realPath);
		File file = new File(realPath);
		try {
			file.delete();// 删除附件文件
		} catch (SecurityException e) {// 存在安全管理器，且其
										// SecurityManager.checkDelete(java.lang.String)
										// 方法拒绝对文件进行删除访问
			file.deleteOnExit();// 在Java虚拟机正常终止时删除文件
		}
	}
	/**
	 * 获取文件的后缀名
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName){
		int index = fileName.lastIndexOf(".");
		String suffix = fileName.substring((index + 1),
				fileName.length());// 后缀名
		return suffix;
	}
	public static Map<String,Object> upload(HttpServletRequest req, MultipartFile upload,String uploadFile) {
		Map<String,Object>map=new HashMap<String, Object>();
		String []data = new String[1];
		int errno = 1;
		String originalFileName = upload.getOriginalFilename();
		String suffix = IOUtil.getFileSuffix(originalFileName);
		String newFileName="wang"+RandomUtil.getTimeStampPlusRand()+"."+suffix;
		String realPath = req.getServletContext().getRealPath(uploadFile);
		try {
			upload.transferTo(new File(realPath + "/" + newFileName));
			errno = 0;
			data[0]=uploadFile + "/" + newFileName;
			map.put("data", data);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("errno", errno);
		return map;
	}

}
