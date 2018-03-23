package lk.dialog.BillVerification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lk.dialog.BillVerification.model.User;
import lk.dialog.BillVerification.rest.resources.UserResource;
import lk.dialog.BillVerification.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TokenAuthenticationService {

    private static final String JWT_CLAIM_PERMISSIONS = "permissions";
    private static final String AUTHORIZATION = "Authorization";
    private static final String SECRET = "ThisIsASecret";
    private static final String DEFAULT_ROLE_PREFIX = "ROLE_";

    @Value("${jwt.token.expiration}")
    private long expirationTime;

    @Value("${jwt.token.issuer}")
    private String issuer;

    private UserDetailsService userDetailsService;
    private final UserService userService;


    @Autowired
    public TokenAuthenticationService(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        Assert.notNull(userDetailsService, "userDetailsService cannot be empty.");
        Assert.notNull(userService, "UserService cannot be empty.");
    }

    public void addAuthentication(HttpServletResponse response, String username) {

        try {
            User loggedInUser = userService.findUserByUsername(username);
            response.addHeader(AUTHORIZATION, generateJwtToken(username));
            new ObjectMapper().writeValue(response.getOutputStream(),new UserResource(loggedInUser));

        } catch (Exception e) {
            throw new ServiceException("Error while generating token for user : {}", e, username);
        }
    }

    public String generateJwtToken(String username) {
        Set<String> stringAuthorities = userDetailsService.loadUserByUsername(username).getAuthorities().stream()
                .map(authority -> (DEFAULT_ROLE_PREFIX + authority.getAuthority())).collect(Collectors.toSet());

        return Jwts.builder()
                .setSubject(username)
                .claim(JWT_CLAIM_PERMISSIONS, stringAuthorities)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    public String getUsernameFromToken(String token) {
        return this.getClaimsFromToken(token).getSubject();
    }

    public Claims getClaimsFromToken(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (MalformedJwtException e1) {
            throw new ServiceException("MalformedJwtException while parsing token : {}", e1, token);

        } catch (SignatureException e2) {
            throw new ServiceException("SignatureException while parsing token : {}", e2, token);

        } catch (ExpiredJwtException e3) {
            throw e3;
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String token) {

        try {
            Set<SimpleGrantedAuthority> authorities;
            ArrayList<String> permissions = this.getClaimsFromToken(token).get("permissions", ArrayList.class);
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            return authorities;
        } catch (Exception e) {
            throw new ServiceException("Error occurred while retrieving authorities from token : {}", e, token);
        }
    }
}
