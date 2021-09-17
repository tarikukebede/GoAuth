package com.tarikukebede.userManagement.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tarikukebede.userManagement.Dto.ResDto.AuthResponseDto;
import com.tarikukebede.userManagement.Util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static com.tarikukebede.userManagement.Util.Constants.TOKEN_SIGNATURE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    //This should come from AppSecurityConfig
    private final AuthenticationManager authenticationManager;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username {} and Password {}", username, password);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SIGNATURE.getBytes());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_EXP))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",
                        user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.REFRESH_TOKEN_EXP))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);


        // response.setHeader("access_token", accessToken);
        // response.setHeader("refresh_token", refreshToken);
        // Map<String,String> tokens = new HashMap<>();
        // tokens.put("access_token", accessToken);
        // tokens.put("refresh_token", refreshToken);

          response.setContentType(MediaType.APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), new AuthResponseDto(accessToken, refreshToken));
    }
}
