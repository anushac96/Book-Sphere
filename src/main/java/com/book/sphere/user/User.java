package com.book.sphere.user;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.book.sphere.role.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)

public class User implements  UserDetails, Principal{
	
	@Id
	@GeneratedValue() 
	private Integer id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	@Column(unique = true)
	private String email;
	private String password;
	private boolean accountLocked;
	private boolean enabled;
	 
	@ManyToMany(fetch = FetchType.EAGER )	//when fetching users, fetch roles egarly 
	private List<Role> roles;
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDate;
	@LastModifiedBy
	@Column(insertable = false)
	private LocalDateTime lastModificationDate;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.roles
				.stream()
				.map(r->new SimpleGrantedAuthority(getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String fullName(){
		return firstName+" "+lastName;
	}

}
