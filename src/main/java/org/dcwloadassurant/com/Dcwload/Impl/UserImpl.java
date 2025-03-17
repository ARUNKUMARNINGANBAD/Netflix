package org.dcwloadassurant.com.Dcwload.Impl;

//import com.secuirty.service.Interface.Iuserservice;
//import com.secuirty.service.entity.User;
//import com.secuirty.service.repo.UserRepositorye;
import org.dcwloadassurant.com.Dcwload.Entity.User;
import org.dcwloadassurant.com.Dcwload.Interface.Iuserservice;
import org.dcwloadassurant.com.Dcwload.repo.UserRepositorye;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserImpl implements Iuserservice, UserDetailsService {

	@Autowired
	private UserRepositorye repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public String saveuser(User user) {
		// TODO Auto-generated method stub
	String pwd = user.getUserPassword();
	pwd = encoder.encode(pwd);
	user.setUserPassword(pwd);
	
	user= repo.save(user);
		//user= repo.save(user);
		
		//return user.getUserId();
		return  user.getUserName();
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return repo.findByUserName(userName);
	}


//	@Override
//	public Optional<User> findByUserEmail(String userEmail) {
//		// TODO Auto-generated method stub
//
//		return repo.findByUserEmail(userEmail);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("am load userbyusername");
		//Optional<User> opt = findByUserEmail(username);
		Optional<User> opt = findByUserName(username);
		
//		if(opt.isEmpty()) {
//			throw new UsernameNotFoundException(username + "not exist");
//		}
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException(username + " not exist");
		}
		
		 User user  = opt.get();
		 
		 UserDetails  details = new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getUserPassword(),
				user.getUserRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toList()));
		 System.out.println("here is role"+user.getUserRoles().stream()
					.map(r -> new SimpleGrantedAuthority(r))
					.collect(Collectors.toList()));
		// System.out.println(details);


		
		return details;
	}
	
	

}
