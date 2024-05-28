package com.gen.script.api.project.service;

import java.util.List;

import com.gen.script.api.project.domain.ProjectVO;

public interface ProjectService {
	public List<ProjectVO> selectProjectList(ProjectVO vo) throws Exception;
	public int save(ProjectVO vo) throws Exception;
}
