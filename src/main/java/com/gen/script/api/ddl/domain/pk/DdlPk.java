package com.gen.script.api.ddl.domain.pk;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"projectCode","tableName","tableCol"})
public class DdlPk implements Serializable{
	private static final long serialVersionUID = 1L;
	private String projectCode;
	private String tableName;
	private String tableCol;
}
