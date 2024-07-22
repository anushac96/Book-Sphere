//package com.book.sphere.security;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>>  {
//	private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//
//    @Override
//    public Collection<GrantedAuthority> convert(Jwt jwt) {
//        Collection<GrantedAuthority> authorities = defaultGrantedAuthoritiesConverter.convert(jwt);
//        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().replace("ROLE_", "")))
//                .collect(Collectors.toList());
//    }
//}
