package com.ToMist.GraceAcademy.controller;

import com.ToMist.GraceAcademy.dto.UserInfoDTO;
import com.ToMist.GraceAcademy.dto.request.AuthRequest;
import com.ToMist.GraceAcademy.security.JwtService;
import com.ToMist.GraceAcademy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired private UserInfoService service;
    @Autowired private JwtService jwtService;
    @Autowired private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "보호 받지 않는 페이지입니다.";
    }

    @PostMapping("/user")
    public String addNewUser(UserInfoDTO userInfo) {
        return service.addNewUser(userInfo);
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(){
        // TODO: 마이페이지 구현 예정
        return "user-profile";
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public String adminProfile() {
        // TODO: 어드민 페이지 구현 예정
        return "admin-prifile";
    }

    @PostMapping("/generate-token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("해당 작업이 불가능합니다.");
        }
    }

}
