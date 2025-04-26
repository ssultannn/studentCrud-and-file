package az.developia.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.school.entity.AuthorityEntity;
@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long>{

}
