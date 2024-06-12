package com.gen.script.api.ddl.domain;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;

import com.gen.script.api.ddl.controller.DDLController;
import com.gen.script.api.ddl.domain.pk.DdlPk;
import com.gen.script.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name="PGI_DDL")
@IdClass(DdlPk.class)
@Entity(name="ddl")
@Data
@EqualsAndHashCode(callSuper = false, of= {"projectCode", "tableName", "colType"})
public class DdlEntity extends BaseEntity{
	@Id
	@Column(name="PROJECT_CODE")
	private String projectCode;
	@Id
	@Column(name="TABLE_NAME")
	private String tableName;
	@Id
	@Column(name="TABLE_COL")
	private String tableCol;
	@Column(name="COL_TYPE")
	private String colType;
	@Column(name="COL_TYPE_LMT")
	private String colTypeLmt;
	@Column(name="NULLABLE")
	private int nullable;
	@Column(name="COL_PK")
	private int colPk;
	@Column(name="DEFAULT_VALUE")
	private String defaultValue;
	@Column(name="COMMENT")
	private String comment;
	@Column(name="SORT_SEQ")
	private int sortSeq;
	
	public DdlDTO toDTO(){
		DdlDTO dto = new DdlDTO();
		dto.setProjectCode(projectCode);
		dto.setTableName(tableName);
		dto.setTableCol(tableCol);
		dto.setColType(colType);
		dto.setColTypeLmt(colTypeLmt);
		dto.setColPk(colPk);
		dto.setDefaultValue(defaultValue);
		dto.setComment(comment);
		dto.setSortSeq(sortSeq);
		return dto;
	}
	
	public EntityModel<DdlEntity> toModel() throws Exception{
		return EntityModel.of(this)
				.add(
					linkTo(methodOn(DDLController.class).get(toDTO()))
						.withSelfRel()
						.withType("get")
						.withMedia(MediaType.APPLICATION_JSON_VALUE));
	}
}
