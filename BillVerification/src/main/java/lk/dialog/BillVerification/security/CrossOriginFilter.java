package lk.dialog.BillVerification.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossOriginFilter extends OncePerRequestFilter {

    //todo fix hard coded values and remove unnecessary Access Control Headers
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, Authorization, Content-Disposition");
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");

        if (!"OPTIONS".equals(httpServletRequest.getMethod())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}