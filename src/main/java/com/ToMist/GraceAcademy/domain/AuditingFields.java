package com.ToMist.GraceAcademy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)  // 엔티티의 수정, 생성 시점을 알 수 있다.
@MappedSuperclass   // 다른 클래스의 부모 클래스가 될 수 있다.
public class AuditingFields {

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, length = 100, updatable = false) private String createdBy;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false) private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(nullable = false, length = 100) private String modifiedBy;

}
