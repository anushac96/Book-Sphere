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
@Table(name = "book_user")
@EntityListeners(AuditingEntityListener.class)

public class User implements UserDetails, Principal {

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

	@ManyToMany(fetch = FetchType.EAGER) // when fetching users, fetch roles egarly
	private List<Role> roles;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDate;

	@LastModifiedBy
	@Column(insertable = false) // dont insert a new data, just update a existing data
	private LocalDateTime lastModifiedDate;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	private String fullName() {
		return firstName + " " + lastName;
	}

	// Manual Builder Implementation
	public static class Builder {
		private Integer id;
		private String firstName;
		private String lastName;
		private LocalDate dateOfBirth;
		private String email;
		private String password;
		private boolean accountLocked;
		private boolean enabled;
		private List<Role> roles;
		private LocalDateTime createDate;
		private LocalDateTime lastModifiedDate;

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder dateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder accountLocked(boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}

		public Builder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder roles(List<Role> roles) {
			this.roles = roles;
			return this;
		}

		public Builder createDate(LocalDateTime createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder lastModifiedDate(LocalDateTime lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
			return this;
		}

		public User build() {
			User user = new User();
			user.id = this.id;
			user.firstName = this.firstName;
			user.lastName = this.lastName;
			user.dateOfBirth = this.dateOfBirth;
			user.email = this.email;
			user.password = this.password;
			user.accountLocked = this.accountLocked;
			user.enabled = this.enabled;
			user.roles = this.roles;
			user.createDate = this.createDate;
			user.lastModifiedDate = this.lastModifiedDate;
			return user;
		}
	}
}
