package com.ToMist.GraceAcademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username", unique = true) @NotBlank private String username;
    @Column(name = "password") @NotBlank private String password;
    @Column(name = "name") @NotBlank private String name;
    @Column(name = "age") @NotNull private int age;
    @Column(name = "phone_num") private String phoneNum;
    @Column(name = "email") private String email;   // 선택 사항
//    @Column(name = "created_at") private LocalDate createdAt;
    @Column(name = "roles") private String roles;
//    @Column(name = "created_at") @Enumerated(EnumType.STRING) private ERole role;

    public UserInfo(String username, String password, String name, int age, String phoneNum, String email,
                    String roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.email = email;
        this.roles = roles;
    }

//
//    // TODO: 추후에 깃잔디 기능 구현 예정
//
//    protected UserInfo(){}
//
//    private UserInfo(String username, String password, String name, int age, String phoneNum, String email) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.age = age;
//        this.phoneNum = phoneNum;
//        this.email = email;
//    }
//
//    public static UserInfo of(String username, String password, String name, int age, String phoneNum, String email) {
//        return new UserInfo(username, password, name, age, phoneNum, email);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof UserInfo user)) {
//            return false;
//        }
//        return userId == user.userId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId);
//    }
}
