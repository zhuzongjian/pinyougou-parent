package com.pinyougou.shop.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.ResponseResult;
import util.FastDFSClient;

/**
 * 文件上传controller
 * @author Administrator
 *
 */
@RestController
public class UploadController implements Serializable {
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER_URL;//上传文件的服务器的地址
	
	@RequestMapping("/upload")
	public ResponseResult upload(MultipartFile file) {
		//取得文件拓展名
		try {
			FastDFSClient fastDFSClient = new  FastDFSClient("classpath:config/fdfs_client.conf");
			String filename = file.getOriginalFilename();
			String extName=filename.substring(filename.lastIndexOf(".")+1);
			String uploadFileName = fastDFSClient.uploadFile(file.getBytes(), extName);//执行上传文件,返回访问文件名
			String urlString=FILE_SERVER_URL+uploadFileName;
			return new ResponseResult(true,urlString);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false,"文件上传失败");
		}
	}
}
