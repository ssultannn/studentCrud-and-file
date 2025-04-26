package az.developia.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.developia.school.entity.AuthorityEntity;
import az.developia.school.entity.UserEntity;
import az.developia.school.exception.MyException;
import az.developia.school.repository.AuthorityRepository;
import az.developia.school.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void register1(UserEntity user) {

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new MyException("такой username уже существует");
		}
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()) );

		userRepository.save(user);

		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setUsername(user.getUsername());

		authorityEntity.setAuthority("ROLE_USER");
		authorityRepository.save(authorityEntity);

	}
}