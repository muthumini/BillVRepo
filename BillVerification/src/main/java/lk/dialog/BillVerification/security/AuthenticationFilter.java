package lk.dialog.BillVerification.security;

import io.jsonwebtoken.ExpiredJwtException;
import lk.dialog.BillVerification.service.TokenAuthenticationService;
import lk.dialog.BillVerification.util.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean{

    private TokenAuthenticationService tokenAuthenticationService;

    public AuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = tokenAuthenticationService.getTokenFromRequest(httpRequest);

        try {
            if (authToken != null) {
                String username = tokenAuthenticationService.getUsernameFromToken(authToken);

                BVAUser user = new BVAUser(username, tokenAuthenticationService.getAuthorities(authToken),
                        true, true, true, true, "null");
                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);

        } catch (ServiceException | ExpiredJwtException se ) {
            response.getWriter().write(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
