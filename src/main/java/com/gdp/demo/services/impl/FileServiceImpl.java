package com.gdp.demo.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gdp.demo.exceptions.BadApiRequest;
import com.gdp.demo.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	
	private Logger logger=LoggerFactory.getLogger(FileServiceImpl.class);
	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		
		String originalFilename = file.getOriginalFilename();
		logger.info("Filename : {} ",originalFilename);
		String filename= UUID.randomUUID().toString();
		String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileNameWithExtension = filename+extention;
		
		String FullPathWithFileName =path + File.separator+fileNameWithExtension;
		
		
		if(extention.equalsIgnoreCase(".png")|| extention.equalsIgnoreCase(".jpg")||extention.equalsIgnoreCase(".jpeg"))
		{
			//save the file
			
			//file save 
			File folder =new File(path);
			
			if(!folder.exists()) {
				
				//create the folder
				folder.mkdirs();
			}
			
			//upload
			Files.copy(file.getInputStream(), Paths.get(FullPathWithFileName));
			
			return fileNameWithExtension;
		}else {
			
			throw new BadApiRequest("file with this " + extention +"not allowed");
		}
	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {

		String fullPath =path + File.separator +name;
		InputStream inputStream =new FileInputStream(fullPath);
		
		return inputStream;
	}

}
