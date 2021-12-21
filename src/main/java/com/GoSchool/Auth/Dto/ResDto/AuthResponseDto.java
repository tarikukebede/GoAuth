package com.GoSchool.Auth.Dto.ResDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String access_token;
    private String refresh_token;

}
