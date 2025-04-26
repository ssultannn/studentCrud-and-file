package az.developia.school.service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import az.developia.school.entity.CourseEntity;
import az.developia.school.entity.Student;
import az.developia.school.repository.CourseRepository;
import az.developia.school.repository.StudentRepository;
import az.developia.school.response.StudentResponse;
//logger
@Service
public class StudentService {

	private static final Logger log=LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Cacheable("allStudents")
	public List<StudentResponse> getAllStudent() {
		System.out.println("загружаем данные из бд");
		List<Student> students = studentRepository.findAll();
		return students.stream().map(student -> modelMapper.map(student, StudentResponse.class))
				.collect(Collectors.toList());
	}

	public Page<Student> getAllStudentPaginated(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public void saveStudent(Student student) {
		log.info("сохраняем студента " + student.getName());
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			log.warn("не удалось найти студента с таким id");
		}
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

	public List<Student> searchStudent(String keyword) {
		return studentRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(keyword, keyword);
	}

	public Optional<CourseEntity> getStudentCourse(Long studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);

		return courseRepository.findById(student.getCourseId());

	}

}
