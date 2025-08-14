package com.BillingApplication.Billings.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil; 
    private final MyUserDetailsService userDetailsService;
  public JwtAuthenticationFilter(JwtUtil jwtUtil, MyUserDetailsService userDetailsService)
  {
     this.jwtUtil = jwtUtil; 
     this.userDetailsService = userDetailsService;
  }
  @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
  throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization"); 
    String token = null; 
    String username = null;
    if(authHeader!=null && authHeader.startsWith("Bearer "))
    { token = authHeader.substring(7); 
        try{ username = jwtUtil.extractUsername(token);}catch(Exception ignored){}}
    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
      UserDetails ud = userDetailsService.loadUserByUsername(username);
      if(jwtUtil.validate(token)){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    chain.doFilter(request,response);
  }

}
