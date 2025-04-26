package az.developia.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.school.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByUsername(String username);

}
