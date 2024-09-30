package com.ToMist.GraceAcademy.repository;

import com.ToMist.GraceAcademy.entity.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
}
