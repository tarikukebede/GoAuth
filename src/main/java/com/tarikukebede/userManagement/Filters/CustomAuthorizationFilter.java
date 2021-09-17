package com.tarikukebede.userManagement.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.tarikukebede.userManagement.Util.Constants.TOKEN_SIGNATURE;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    //Todo: Abstract all non related functionalities from this module

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //If the user is trying to login there is no need to check for
        //tokens.
        if(request.getServletPath().equals("/auth/login") || request.getServletPath().equals("auth/refreshtoken")){
            filterChain.doFilter(request, response);
        }else{

            //Can just be tokens, 'Bearer ' is just customery
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if( authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring(7);
                    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SIGNATURE.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    stream(roles).forEach(role -> {
                         authorities.add(new SimpleGrantedAuthority(role));
                    });

                    //Building a spring security user and setting the necessary
                    //roles for api access
                    UsernamePasswordAuthenticationToken authenticationToken  =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    //Passing the request to the next filter in the chain
                    filterChain.doFilter(request, response);
                }catch (Exception e){
                   log.error("Error : logging in: {}", e.getMessage());
                   response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                   response.setHeader("error", e.getMessage());
                   response.setStatus(FORBIDDEN.value());
                   //response.sendError(FORBIDDEN.value());
                   Map<String,String> error = new HashMap<>();
                   error.put("error_message", e.getMessage());
                }

            }else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
