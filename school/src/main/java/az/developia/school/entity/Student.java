package az.developia.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "имя не может быть пустым")
	private String name;
	@NotBlank(message = "фамилия не может быть пустым")
	private String surname;
	@Min(value = 6,message = "минимальный возраст -6")
	@Max(value = 18,message = "максимальный возраст это 17")
	@NotNull(message = "возраст обязательный")
	private Integer age;
	
	private Long courseId;
	
}
//id name   surname age courseId
// 1 Ruslan huseyni 12  4
// 2 Javid  huseyni 15  2

