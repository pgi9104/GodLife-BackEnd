package com.gen.script.api.classdiagram.domain;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassVarVO extends CommonVO{
	private String projectCode;
	private String packageName;
	private String className;
	private Integer classVarAccessModifier;
	private String classVarType;
	private String classVarComment;
}
