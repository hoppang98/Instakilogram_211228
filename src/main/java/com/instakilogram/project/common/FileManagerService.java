package com.instakilogram.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "C:\\손지승\\New_Study\\1-2. Project\\Instakilogram images/";
	
	public static String saveFile(int userId, MultipartFile file) {
		if(file == null ) {
			return null;
		}
		
		String directioryName = userId + "_" + System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directioryName;
		
		File directiory = new File(filePath);
		if(directiory.mkdir() == false) {
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
