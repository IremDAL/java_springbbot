package com.iremdal.todo.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Setter
@Getter

@MappedSuperclass

@EntityListeners(AuditingEntityListener.class)

abstract  public class AuditingAwareBaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @CreatedBy
    @Column(name="created_by")
    protected String createdBy;

    @CreatedDate
    @Column(name="created_date")
    protected String createdDate;

    @LastModifiedBy
    @Column(name="last_modified_by")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name="last_modified_date")
    protected String lastModifiedDate;

}
