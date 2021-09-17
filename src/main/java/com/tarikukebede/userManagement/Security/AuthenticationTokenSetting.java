package com.tarikukebede.userManagement.Security;


public class AuthenticationTokenSetting {

    private  String secret;
    private  String audience;
    private  String issuer;
    private  String authoritiesClaimName;
    private  String refreshCountClaimName;
    private  String refreshLimitClaimName;


    public AuthenticationTokenSetting
    (
        String secret,
        String audience,
        String issuer,
        String authoritiesClaimName,
        String refreshCountClaimName,
        String refreshLimitClaimName
    )

    {

    this.secret = secret;
    this.audience = audience;
    this.issuer = issuer;
    this.authoritiesClaimName = authoritiesClaimName;
    this.refreshCountClaimName = refreshCountClaimName;
    this.refreshLimitClaimName = refreshLimitClaimName;

    }

    public String getSecret() {
        return secret;
    }

    public String getAudience() {
        return audience;
    }

    public String getIssuer() {
        return issuer;
    }

    public  String getAuthoritiesClaimName() {
        return authoritiesClaimName;
    }

    public  String getRefreshCountClaimName() {
        return refreshCountClaimName;
    }

    public String getRefreshLimitClaimName() {
        return refreshLimitClaimName;
    }
}
