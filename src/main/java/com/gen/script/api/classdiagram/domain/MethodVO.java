package com.gen.script.api.classdiagram.domain;

import java.util.List;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodVO extends CommonVO{
	private String projectCode;
	private String packageName;
	private String className;
	private String methodAccessModifier;
	private String methodComment;
	private List<MethodVarVO> methodVars;
}
