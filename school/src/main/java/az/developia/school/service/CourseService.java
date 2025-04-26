package az.developia.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.school.entity.CourseEntity;
import az.developia.school.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public List<CourseEntity> getAllCourses() {
		return courseRepository.findAll();
	}

	public Optional<CourseEntity> getById(Long id) {
		return courseRepository.findById(id);
	}

	public void addCourse(CourseEntity courseEntity) {
		courseRepository.save(courseEntity);
	}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
	
	public void updateCourse(Long id,CourseEntity courseEntity) {
		Optional<CourseEntity> existingCourse=courseRepository.findById(id);
		if (existingCourse.isPresent()) {
			CourseEntity course=existingCourse.get();
			course.setName(courseEntity.getName());
			course.setDescription(courseEntity.getDescription());
			courseRepository.save(course);
		}
	}
}
