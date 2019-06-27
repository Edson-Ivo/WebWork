package com.example.demo.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	
	public static void salvarimg(String href,MultipartFile img) {
		
		File file=new File(href);
		
		try {
			FileUtils.writeByteArrayToFile(file, img.getBytes());
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
