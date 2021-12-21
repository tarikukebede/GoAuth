package com.GoSchool.Auth.Security;


public class AuthenticationTokenIssuer {

//    @Autowired
//    private static AuthenticationTokenSetting setting;
//
//    public static AuthResponseDto issueToken(AuthenticationTokenDetails tokenDetails) {
//        Algorithm algorithm = Algorithm.HMAC256(setting.getSecret().getBytes());
//
//        String accessToken = JWT.create()
//                .withSubject(tokenDetails.getUsername())
//                .withExpiresAt(Date.from())
//                .withIssuer(setting.getIssuer())
//                .withClaim("roles",
//                        tokenDetails.getRoles()
//                                .stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .sign(algorithm);
//
//        String refreshToken = JWT.create()
//                .withSubject(tokenDetails.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.REFRESH_TOKEN_EXP))
//                .withIssuer(setting.getIssuer())
//                .sign(algorithm);
//
//        return new AuthResponseDto(accessToken, refreshToken);
//    }
}
