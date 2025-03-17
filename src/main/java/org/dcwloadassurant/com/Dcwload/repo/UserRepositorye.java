package org.dcwloadassurant.com.Dcwload.repo;


import org.dcwloadassurant.com.Dcwload.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import java.util.Optional;

public interface UserRepositorye extends JpaRepository<User, Integer>{

//	@Column(name = "upwd")
//	Optional<User> findByUserEmail(String userEmail);

	@Column(name = "upwd")
	Optional<User> findByUserName(String userEmail);
}
