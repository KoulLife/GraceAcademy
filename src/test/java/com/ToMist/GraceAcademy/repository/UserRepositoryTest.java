package com.ToMist.GraceAcademy.repository;

import static org.assertj.core.api.Assertions.*;

import com.ToMist.GraceAcademy.entity.UserInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

//    @Test
//    void 유저_저장() {
//
//        UserInfo user = UserInfo.of("java","spring","bob", 22, "111-111-111", "###@google.com");
//
//        userInfoRepository.saveAndFlush(user);
//        long currCnt = userInfoRepository.count();
//
//        assertThat(currCnt).isEqualTo(1L);
//    }

}