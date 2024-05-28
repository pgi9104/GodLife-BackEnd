package com.gen.script.api.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gen.script.api.project.dao.ProjectDAO;
import com.gen.script.api.project.domain.ProjectVO;
import com.gen.script.api.project.service.ProjectService;

import jakarta.annotation.Resource;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Resource(name="projectDAO")
	private ProjectDAO projectDAO;

	@Override
	public List<ProjectVO> selectProjectList(ProjectVO vo) throws Exception {
		return projectDAO.selectProjectList(vo);
	}
	
	@Override
	public int save(ProjectVO vo) throws Exception {
		return projectDAO.save(vo);
	}
}
