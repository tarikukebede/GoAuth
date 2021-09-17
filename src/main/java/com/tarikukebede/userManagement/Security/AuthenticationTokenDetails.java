package com.tarikukebede.userManagement.Security;


import com.tarikukebede.userManagement.Models.Role;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Model that holds details about an authentication token.
 *
 * @author Tariku Lemma
 */
public class AuthenticationTokenDetails {

    private final String id;
    private final String username;
    private final Collection<Role> roles;
    private final ZonedDateTime issuedDate;
    private final ZonedDateTime expirationDate;
    private final int refreshCount;
    private final int refreshLimit;

    public AuthenticationTokenDetails(
            String id,
            String username,
            Collection<Role> roles,
            ZonedDateTime issuedDate,
            ZonedDateTime expirationDate,
            int refreshCount,
            int refreshLimit)
    {

        this.id = id;
        this.username = username;
        this.roles = roles;
        this.issuedDate = issuedDate;
        this.expirationDate = expirationDate;
        this.refreshCount = refreshCount;
        this.refreshLimit = refreshLimit;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public ZonedDateTime getIssuedDate() {
        return issuedDate;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public int getRefreshLimit() {
        return refreshLimit;
    }

    /**
     * Builder for the {@link AuthenticationTokenDetails}.
     */

    public static class Builder{

        private String id;
        private String username;
        private Collection<Role> roles;
        private ZonedDateTime issuedDate;
        private ZonedDateTime expirationDate;
        private int refreshCount;
        private int refreshLimit;


        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public Builder withRoles(Collection<Role> roles){
            this.roles = CollectionUtils.isEmpty(roles) ? new ArrayList<>() : roles;
            return this;
        }


        public Builder withIssueDate(ZonedDateTime issuedDate){
            this.issuedDate = issuedDate;
            return this;
        }

        public Builder withExpirationDate(ZonedDateTime expirationDate){
            this.expirationDate = expirationDate;
            return  this;
        }

        public Builder withRefreshCount(int refreshCount){
            this.refreshCount = refreshCount;
            return this;
        }

        public Builder withRefreshLimit(int refreshLimit){
            this.refreshLimit = refreshLimit;
            return this;
        }

    }

    public AuthenticationTokenDetails build(){
        return new AuthenticationTokenDetails(
                id,username, roles, issuedDate, expirationDate, refreshCount, refreshLimit
        );
    }
}
