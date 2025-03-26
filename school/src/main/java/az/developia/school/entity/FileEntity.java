package az.developia.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	
	private String fileType;
	
	
	private String filePath;
	public String getFilePath() {
		return filePath;
	}
	public FileEntity(String fileName,String fileType,String filePath) {
		this.fileName=fileName;
		this.fileType=fileType;
		this.filePath=filePath;
	}
}
