package com.hasanjahidul.docManagement.entity;

import com.hasanjahidul.docManagement.config.UserContextHolder;
import com.hasanjahidul.docManagement.utils.DateConversionUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@DynamicInsert
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.createdBy = currentUser();
        this.updatedBy = this.createdBy;
        this.updatedAt=currentISOTime();
        this.createdAt=currentISOTime();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedBy = currentUser();
        this.updatedAt= currentISOTime();
    }
    private Long currentUser() {
//       get user id from user context holder
        return UserContextHolder.getUserDetails() != null
                ? UserContextHolder.getUserDetails().getUserId()
                : 1L;

    }
    private String currentISOTime() {
        return DateConversionUtil.currentISO8601UTC();
    }
}
