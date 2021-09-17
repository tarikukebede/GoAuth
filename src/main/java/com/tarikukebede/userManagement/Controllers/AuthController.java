package com.tarikukebede.userManagement.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tarikukebede.userManagement.Dto.ResDto.AuthResponseDto;
import com.tarikukebede.userManagement.Models.AppUser;
import com.tarikukebede.userManagement.Models.Role;
import com.tarikukebede.userManagement.Services.UserService;
import com.tarikukebede.userManagement.Util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.tarikukebede.userManagement.Util.Constants.TOKEN_SIGNATURE;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(
            @RequestParam("username") String username,
            @RequestParam("password") String password ){
        //Todo: Create Authentication log
        log.info("Username {} and Password {}", username, password);
    }

    @PostMapping("/logout")
    public void logout(){
        //Todo: Update Authentication log
        log.info("User logged out");
    }

    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Todo: Abstract all JWT related logic to it's own module
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if( authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(TOKEN_SIGNATURE.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String username = decodedJWT.getSubject();
                AppUser appUser = userService.getUser(username);

                String accessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_EXP))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                appUser.getRoles()
                                        .stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toList()))
                        .sign(algorithm);


                response.setHeader("access_token", accessToken);
                response.setHeader("refresh_token", refresh_token);

//                new ObjectMapper().writeValue(response.getOutputStream(),
//                        new AuthResponseDto(accessToken, refresh_token));

            }catch (Exception e){
                log.error("Error : logging in: {}", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }else {
            throw new RuntimeException("Refresh token invalid!");
        }

    }
}
