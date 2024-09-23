package com.ToMist.GraceAcademy.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware()  {
        return () -> Optional.of("grace");  // TODO: 스프링 시큐리티 인증 기능을 추가할 때, createdAt 제대로 설정
    }

}
