package az.developia.school.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import az.developia.school.entity.FileEntity;
import az.developia.school.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileEntity savedFileEntity = fileService.uploadFile(file);
			return "файл успешно загружен " + file.getOriginalFilename();
		} catch (IOException e) {
			return "ОШИБКА ЗАГРУЗКИ" + file.getOriginalFilename();

		}
	}
}
