package az.developia.school.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.school.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	FileEntity findByFileName(String fileName);



}
