package org.dcwloadassurant.com.Dcwload.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usertab")
public class User {
	
	
	
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "username")
	private String userName;
	@Column(name = "uemail")
	private String userEmail;
	@Column(name = "upwd")
	private String userPassword;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles_tab", 
	joinColumns = @JoinColumn(name="uid"))
	@Column(name = "uroles")
	private Set<String> userRoles;

}
