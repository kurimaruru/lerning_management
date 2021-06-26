package com.example.demo.article;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

	@RequestMapping("/img/{name}")
	public byte[] image(@PathVariable String name) {
		File path=new File("upload",name);
		byte[] bytes = null;
		try(FileInputStream fis = new FileInputStream(path)){
			bytes = fis.readAllBytes();
		}
		catch(IOException e) {
			System.err.println("image:"+e);
		}
		return bytes;
	}
}
