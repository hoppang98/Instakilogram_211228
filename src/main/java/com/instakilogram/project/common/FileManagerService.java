package com.instakilogram.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "C:\\손지승\\New_Study\\1-2. Project\\Instakilogram images/";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
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
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		logger.info("/Instakilogram images/" + directioryName + file.getOriginalFilename());
		return "/Instakilogram images/" + directioryName + file.getOriginalFilename();
	}
	
	
	
	
	
	// 파일 삭제
	public static void removeFile(String filePath) {
		if(filePath == null) {
			logger.error("FileManagerService::removeFile - 삭제할 파일 없음");
			return;
		}

		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/Instakilogram images/", "");

		Path path = Paths.get(realFilePath);

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 파일 삭제 실패");
				e.printStackTrace();
			}
		}

		path = path.getParent();

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 디렉토리 삭제 실패");
				e.printStackTrace();
			}
		}
	}
	
}
