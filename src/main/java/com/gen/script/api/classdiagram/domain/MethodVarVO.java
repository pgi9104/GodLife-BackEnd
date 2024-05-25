package com.gen.script.api.classdiagram.domain;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MethodVarVO extends CommonVO{
	private String projectCode;
	private String packageName;
	private String className;
	private String method;
	private String methodVar;
	private String returnType;
	private String methodVarComment;
}
