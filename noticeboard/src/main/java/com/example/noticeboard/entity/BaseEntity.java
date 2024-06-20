package com.example.noticeboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 시간정보를 다루는 클래스
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    // 생성시 시간정보 제공
    @CreationTimestamp
    @Column (updatable = false) //수정시에는 관여하지 않는다.
    private LocalDateTime createdTime;

    // 업데이트시 시간정보 제공
    @UpdateTimestamp
    @Column(name = "updated_time", insertable = false) //삽입시에는 관여하지 않는다.
    private LocalDateTime updatedTime;
}