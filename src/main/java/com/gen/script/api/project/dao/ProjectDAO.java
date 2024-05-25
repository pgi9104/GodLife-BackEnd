package com.gen.script.api.project.dao;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.gen.script.api.project.domain.ProjectVO;
import com.gen.script.common.dao.CommonDAO;

@Repository("projectDAO")
public class ProjectDAO extends CommonDAO{
	/**
	 * 
	 * @param vo
	 * @return List<ProjectVO>
	 * @throws Exception
	 */
	public List<ProjectVO> selectProjectList(ProjectVO vo) throws Exception {
		return selectList("project.selectProjectList", vo);
	}
	
	/**
	 * 
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int save(ProjectVO vo) throws Exception {
		int result = 0;
		
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(vo.getAddList() != null && vo.getAddList().size() > 0) {
			for(ProjectVO project: vo.getAddList()) {
				project.setInsId(user);
				project.setUpdId(user);
				result += insert(project);				
			}
		}
		
		if(vo.getUpdateList() != null && vo.getUpdateList().size() > 0) {
			for(ProjectVO project: vo.getUpdateList()) {
				project.setUpdId(user);
				result += update(project);				
			}
		}

		if(vo.getDeleteList() != null && vo.getDeleteList().size() > 0) {
			for(ProjectVO project: vo.getDeleteList()) {
				result += delete(project);				
			}
		}
		
		return result;
	}
	
	private int insert(ProjectVO vo) throws Exception {
		return insert("project.insertProject", vo);
	}
	
	private int update(ProjectVO vo) throws Exception {
		return update("project.updateProject", vo);
	}
	
	private int delete(ProjectVO vo) throws Exception {
		return delete("project.deleteProject", vo);
	}
}
