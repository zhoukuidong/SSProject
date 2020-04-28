package com.zkd.springMVC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestUpAndDownController {

	@RequestMapping("/down")
	public ResponseEntity<byte[]> down(HttpSession session) throws IOException{
		
		//获取文件下载的路径
		String realPath = session.getServletContext().getRealPath("img");
		String finalPath = realPath + File.separator + "GG.jpg" ;
		InputStream is = new FileInputStream(finalPath);
		byte[] b = new byte[is.available()];//获取输入流所读取的文件最大字节数
		is.read(b);
		//设置请求头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition","attachment;filename=zkd.jpg");
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(b,headers,statusCode);
		
		return entity;
	}
	
	@RequestMapping("/first")
	public String  helloWorld(){
		
		return "success";
	}
}
