package com.clover.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.clover.model.FileInfo;

/**
 * 文件控制类
 * 
 * @author zhangdq
 * @time 2018年3月16日 上午9:56:05
 * @Email qiang900714@126.com
 */
@Controller
@RequestMapping("/file")
public class FileController {
	private String path = "E:\\";

	/**
	 * 文件上传页面
	 * 
	 * @author zhangdq
	 * @time 2018年3月16日 上午10:18:26
	 * @Email qiang900714@126.com
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return "file";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		File localFile = new File(path, file.getOriginalFilename());

		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		try (
				InputStream inputStream = new FileInputStream(new File(path, id + ".jpg")); 
				OutputStream outputStream = response.getOutputStream();) {

			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");

			IOUtils.copy(inputStream, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
