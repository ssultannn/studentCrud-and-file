package az.developia.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.school.entity.Student;
import az.developia.school.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
		
	}

	public Student updateStudent(Long id, Student student) {

		Optional<Student> existingStudent = studentRepository.findById(id);
		if (existingStudent.isPresent()) {
			Student updatedStudent = existingStudent.get();
			updatedStudent.setName(student.getName());
			updatedStudent.setSurname(student.getSurname());
			updatedStudent.setAge(student.getAge());
			return studentRepository.save(updatedStudent);
		}
		return null;
	}
	
	public List<Student> searchStudent(String keyword){	
	return studentRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(keyword,keyword);
	}

}
