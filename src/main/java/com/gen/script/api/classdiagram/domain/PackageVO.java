package com.gen.script.api.classdiagram.domain;

import java.util.List;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PackageVO extends CommonVO{
	private String projectCode;
	private String packageName;
	private String parent;
	private List<ClassVO> classes;
}
