package org.dcwloadassurant.com.Dcwload.Interface;

//import com.secuirty.service.entity.User;
import org.dcwloadassurant.com.Dcwload.Entity.User;

import java.util.Optional;

public interface Iuserservice {

	public String saveuser(User user);
	//public Optional<User> findByUserEmail(String userEmail);
	public Optional<User> findByUserName(String userEmail);
}
