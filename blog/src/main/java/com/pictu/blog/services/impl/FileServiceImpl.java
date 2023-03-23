package com.pictu.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pictu.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// File name
		String name = file.getOriginalFilename();

		// Full path of file

		String randomId = UUID.randomUUID().toString();
		// Integer randomInt = UUID.randomUUID().clockSequence();

		// System.out.println("============Clock Sequence"+randomInt);

		String newFileName = randomId.concat(name.substring(name.lastIndexOf(".")));

		String filePath = path + File.separator + newFileName;

		// Create folder if not created

		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();

		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));
		System.out.println("Copied at " + filePath);

		return newFileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) {
		String fullPath = path + File.separator + fileName;
		InputStream is = null;
		try {
			is = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return is;
	}

}
