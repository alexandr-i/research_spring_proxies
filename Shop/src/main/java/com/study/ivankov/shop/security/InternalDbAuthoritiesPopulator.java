package com.study.ivankov.shop.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.study.ivankov.shop.exception.UserHasNoRolesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.study.ivankov.shop.jpa.UserRoleRepository;

/**
 * @author Ivankov_A
 *
 */
@Component
public class InternalDbAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Autowired
	private ApplicationContext appContext;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations paramDirContextOperations, String userName) {
		Set<GrantedAuthority> theResult = new HashSet<>();
		String fromDb = appContext.getBean(UserRoleRepository.class).findAuthorityForUser(userName);
		if (StringUtils.hasText(fromDb)) {
			throw new UserHasNoRolesException("User " + userName + " has NO any role");
		} else {
			theResult.add(new SimpleGrantedAuthority(fromDb));
		}
		return theResult;
	}
}