package com.ToMist.GraceAcademy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
    @NotBlank(message = "사용자 이름은 필수 입력 사항입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String name;

    @NotNull(message = "나이는 필수 입력 사항입니다.")
    private int age;

    private String phoneNum;
    private String email;
    private String roles;

}