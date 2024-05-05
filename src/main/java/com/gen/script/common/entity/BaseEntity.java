package com.gen.script.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
	@Column(name="CREATED_ID")
	@NotNull
	@Size(max = 64)
    private String createdId;
	
	@Column(name="MODIFIED_ID")
	@NotNull
	@Size(max = 64)
    private String modifiedId;
	
    @Column(name = "CREATED_AT", updatable = false)
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    
    public BaseEntity(@NotNull @Size(max = 64) String createdId, @NotNull @Size(max = 64) String modifiedId) {
    	this.createdId = createdId;
    	this.modifiedId = modifiedId;
    }
}