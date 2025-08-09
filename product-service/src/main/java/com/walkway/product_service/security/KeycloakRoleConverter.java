package com.walkway.product_service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

    private final String resourceName;

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> realmAuthorities = new ArrayList<>();
        Collection<GrantedAuthority> resourceAuthorities = new ArrayList<>();
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, Object> realmAccess = source.getClaim("realm_access");
        if(realmAccess!=null && realmAccess.containsKey("roles")){
            List<String> realmRoles = (List<String>) realmAccess.get("roles");
            realmRoles.forEach(roleName -> realmAuthorities.add(new SimpleGrantedAuthority(roleName)));
        }

        Map<String, Object> resourceAccess = source.getClaim("resource_access");
        if(resourceAccess!=null && resourceAccess.containsKey(resourceName)){
            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get(resourceName);
            if(clientRoles.containsKey("roles")){
                List<String> roles = (List<String>) clientRoles.get("roles");
                roles.forEach(roleName -> resourceAuthorities.add(new SimpleGrantedAuthority(roleName)));
            }
        }

        authorities.addAll(realmAuthorities);
        authorities.addAll(resourceAuthorities);
        return authorities;
    }
}
