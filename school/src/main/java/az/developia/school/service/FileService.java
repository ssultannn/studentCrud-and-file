package az.developia.school.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.developia.school.entity.FileEntity;
import az.developia.school.repository.FileRepository;

@Service
public class FileService {

	private final String upload = "C:\\Users\\texno\\OneDrive\\Desktop\\Test";

	@Autowired
	private FileRepository fileRepository;

	public FileEntity uploadFile(MultipartFile file) throws IOException {
		String filePath = upload + "\\" +  file.getOriginalFilename();

		Files.write(Paths.get(filePath), file.getBytes());

		FileEntity fileEntity = new FileEntity(file.getOriginalFilename(), file.getContentType(), filePath);

		return fileRepository.save(fileEntity);

	}
}
