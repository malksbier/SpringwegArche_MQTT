package de.springwegarche.webpage.Util.Security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        final String authorisationHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwt = null;
        try {
            if(authorisationHeader != null && authorisationHeader.startsWith("Bearer ")) {
                // USER sends JWT Token?
                jwt = authorisationHeader.replace("Bearer ", "");
                username = jwtUtil.extractUsername(jwt);
            } 
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // JWT Token and User are Valid?
                final UserDetails userDetails = this.userService.loadUserByUsername(username);

                if(userDetails == null) {
                    throw new IllegalArgumentException("wrong username");
                }
                if(jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                } 
            } 
        } catch (SignatureException  e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader("Error", "false token");
        } catch (MalformedJwtException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader("Error", "false token");
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader("Error", "token expired");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setHeader("Error", "no_username_found");
        }
        filterChain.doFilter(request, response);
    }
    
}
