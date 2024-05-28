package com.gen.script.api.project.domain;

import java.util.List;

import com.gen.script.api.classdiagram.domain.PackageVO;
import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectVO extends CommonVO{
	private String projectCode;
	private String projectName;
	private String projectComment;
	private List<PackageVO> packages;
	
	private List<ProjectVO> addList;
	private List<ProjectVO> updateList;
	private List<ProjectVO> deleteList;
}
