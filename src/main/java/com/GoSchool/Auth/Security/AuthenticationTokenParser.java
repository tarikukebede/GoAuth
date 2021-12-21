package com.GoSchool.Auth.Security;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationTokenParser {

    @Autowired
    private AuthenticationTokenSetting tokenSetting;


    public AuthenticationTokenDetails parseToken(String token){
      return null;
    }
}
