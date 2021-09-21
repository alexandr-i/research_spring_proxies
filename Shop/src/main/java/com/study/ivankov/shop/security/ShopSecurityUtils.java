package com.study.ivankov.shop.security;

import java.util.Collection;

import com.study.ivankov.shop.exception.UserHasNoRolesException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ivankov_A
 *
 */
public class ShopSecurityUtils {

	public static String getCurrentUserRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().findFirst().orElseThrow(() -> new UserHasNoRolesException("User has no roles: " + authentication.getPrincipal().toString())).getAuthority();
	}
	
	public static String getCurrentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getPrincipal().toString();
	}
}
