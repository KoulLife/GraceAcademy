package com.ToMist.GraceAcademy.service;

import com.ToMist.GraceAcademy.dto.UserInfoDTO;
import com.ToMist.GraceAcademy.entity.UserInfo;
import com.ToMist.GraceAcademy.repository.UserInfoRepository;
import com.ToMist.GraceAcademy.security.UserInfoDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService{

    @Autowired private UserInfoRepository repository;

    @Autowired private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByUsername(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다 : " + username));
    }

//    public String addNewUser(UserInfo userInfo) {
//        // Encode password before saving the user
//        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
//        repository.save(userInfo);
//        return "정상적으로 회원가입이 완료되었습니다.";
//    }

    @Transactional
    public String addNewUser(UserInfoDTO userInfoDTO) {

        // Null 체크 추가
        if (userInfoDTO.getPassword() == null || userInfoDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 사항입니다.");
        }

        UserInfo userInfo = new UserInfo(
          userInfoDTO.getUsername(), userInfoDTO.getPassword(), userInfoDTO.getName(),
                userInfoDTO.getAge(), userInfoDTO.getPhoneNum(), userInfoDTO.getEmail(), userInfoDTO.getRoles()
        );

        // 비밀번호 암호화
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "정상적으로 회원가입이 완료되었습니다.";
    }

}
