package com.gen.script.api.project.domain;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.gen.script.api.classdiagram.domain.PackageVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectModel extends RepresentationModel<ProjectModel>{
	private String projectCode;
	private String projectName;
	private String projectComment;
	private List<PackageVO> packages;
	private Integer sortSeq;
}
