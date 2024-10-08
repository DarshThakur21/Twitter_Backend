package com.Twitter_Clone.twitterc.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt=request.getHeader(JwtConstant.JWT_HEADER);


//        Bearer jwt
        if(jwt!=null){
            jwt=jwt.substring(7);

        try{
            SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims= Jwts.parser()
                    .setSigningKey(key)  // Set the secret key for verifying the signature
                    .build()  // Build the JWT parser
                    .parseClaimsJws(jwt)  // Parse the token and get the claims
                    .getBody();

             String email=String.valueOf(claims.get("email"));
             String authorities=String.valueOf(claims.get("authorities"));

             List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
            Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,auths);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            throw new BadCredentialsException("invalid Token...");
        }
        }
        filterChain.doFilter(request,response);

    }
}
