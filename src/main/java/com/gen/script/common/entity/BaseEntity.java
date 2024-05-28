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

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity{
	@Column(name="INS_ID")
	@NotNull
	@Size(max = 64)
    private String insId;
	
	@Column(name="UPD_ID")
	@NotNull
	@Size(max = 64)
    private String updId;
	
    @Column(name = "INS_DT", updatable = false)
    @CreatedDate
    @NotNull
    private LocalDateTime insDt;

    @Column(name = "UPD_DT")
    @LastModifiedDate
    @NotNull
    private LocalDateTime updDt;
    
    public BaseEntity() {
    	
    }
    
    public BaseEntity(@NotNull @Size(max = 64) String insId, @NotNull @Size(max = 64) String updId) {
    	this.insId = insId;
    	this.updId = updId;
    }
}