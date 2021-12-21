package com.GoSchool.Auth.Dto.ReqDto;

import lombok.Data;

@Data
public class AddRoleToUserReqDto {
    private String username;
    private String roleName;
}
