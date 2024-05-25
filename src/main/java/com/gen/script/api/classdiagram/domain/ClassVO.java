package com.gen.script.api.classdiagram.domain;

import java.util.List;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassVO extends CommonVO{
	private String projectCode;
	private String className;
	private String classType;
	private String classComment;
	private List<ClassVarVO> classVars;
	private List<MethodVO> methods;
}
