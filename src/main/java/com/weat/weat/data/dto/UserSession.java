package com.weat.weat.data.dto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.weat.weat.common.utils.BeanUtil;
import com.weat.weat.data.model.Role;
import com.weat.weat.data.model.User;
import com.weat.weat.service.CoreUserService;
import com.weat.weat.service.impl.CoreUserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserSession implements UserDetails {
	private static final long serialVersionUID = 5234010773674487687L;
	private String idI;
	private String loginId;
	private String userName;
	private Long id;
	private String password;
	private String description;
	private List<String> groups;
	private boolean isEnabled;
	private String fullName;
	private String email;

	private Set<Role> roleList;

	private Collection<? extends GrantedAuthority> authority;

	public UserSession() {
	}

	public UserSession(Long id, String password, boolean isEnabled, Collection<? extends GrantedAuthority> authority,
                       Set<Role> roleList, String fullName, String email) {
		this.id = id;
		this.password = password;
		this.userName = email;
		this.isEnabled = isEnabled;
		this.authority = authority;
		this.roleList = roleList;
		this.fullName = fullName;
		this.email = email;

	}

	public static UserSession create(User user) {

		ApplicationContext context = BeanUtil.getAppContext();
		CoreUserService userService = context.getBean("coreUserServiceImpl", CoreUserServiceImpl.class);
		List<GrantedAuthority> authorities = userService.getRoles(user).stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

		return new UserSession(user.getId(), user.getPassword(), user.getActivated(), authorities, user.getRoles(),
				user.getFirstName(), user.getEmail());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public String getUsername() {
		return userName;
	}
}