package com.gen.script.api.ddl.domain;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;

import com.gen.script.api.ddl.controller.DDLController;
import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DdlDTO extends CommonVO{
	private String projectCode;
	private String tableName;
	private String tableCol;
	private String colType;
	private String colTypeLmt;
	private int nullable;
	private int colPk;
	private String defaultValue;
	private String comment;
	private String dbType;
	private String alterType;
	private String charset;
	private List<DdlDTO> addList;
	private List<DdlDTO> updateList;
	private List<DdlDTO> deleteList;
	
	public DdlEntity toEntity() {
		DdlEntity ddl = new DdlEntity();
		ddl.setProjectCode(projectCode);
		ddl.setComment(comment);
		ddl.setTableName(tableName);
		ddl.setTableCol(tableCol);
		ddl.setColType(colType);
		ddl.setColTypeLmt(colTypeLmt);
		ddl.setNullable(nullable);
		ddl.setColPk(colPk);
		ddl.setDefaultValue(defaultValue);
		ddl.setComment(comment);
		ddl.setSortSeq(this.getSortSeq());
		ddl.setInsId(colType);
		
		return ddl;
	}
	
	public EntityModel<DdlEntity> toModel() throws Exception{
		return EntityModel.of(toEntity())
					.add(linkTo(methodOn(DDLController.class).get(this)).withRel("get").withType("get").withMedia(MediaType.APPLICATION_JSON_VALUE));
	}
}
