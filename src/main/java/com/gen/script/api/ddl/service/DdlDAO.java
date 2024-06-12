package com.gen.script.api.ddl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.gen.script.api.ddl.domain.DdlDTO;
import com.gen.script.common.dao.CommonDAO;

@Repository("ddlDAO")
public class DdlDAO extends CommonDAO{
	public List<DdlDTO> selectDdlList(DdlDTO dto) throws Exception{
		return selectList("ddl.selectDdlList",dto);
	}
	
	public List<DdlDTO> selectBox(DdlDTO dto) throws Exception{
		return selectList("ddl.selectBox",dto);
	}
	
	public int insertBatch(DdlDTO dto) throws Exception{
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<DdlDTO> addList = dto.getAddList();
		dto.setInsId(userId);
		dto.setUpdId(userId);
		int result = 0;
		int size	= addList.size();
		int perCnt	= 500;
		int maxCnt	= size%perCnt>0
					? addList.size()/perCnt+1
					: addList.size()/perCnt;
		
		for(int i = 0; i < maxCnt; i++) {
			int start	= perCnt*i;
			int end		= perCnt*(i+1) < size? perCnt*(i+1) : size;
			List<DdlDTO> subInsertList = addList.subList(start, end).stream().map((item)->{
				item.setInsId(userId);
				item.setUpdId(userId);
				return item;
			}).collect(Collectors.toList());
			result += insert("ddl.insertDdlList", subInsertList);
		}
		
		return result;
	}
	
	public int updateBatch(DdlDTO dto) throws Exception{
		List<DdlDTO> updateList = dto.getUpdateList();
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		int result = 0;
		int size	= updateList.size();
		int perCnt	= 500;
		int maxCnt	= size%perCnt>0
					? updateList.size()/perCnt+1
					: updateList.size()/perCnt;
		
		for(int i = 0; i < maxCnt; i++) {
			int start	= perCnt*i;
			int end		= perCnt*(i+1) < size? perCnt*(i+1) : size;
			List<DdlDTO> subUpdateList = updateList.subList(start, end).stream().map((item)->{
				item.setUpdId(userId);
				return item;
			}).collect(Collectors.toList());
			result += insert("ddl.updateDdlList", subUpdateList);
		}
		
		return result;
	}
	
	public int deleteBatch(DdlDTO dto) throws Exception{
		List<DdlDTO> deleteList = dto.getDeleteList();
		int result = 0;
		int size	= deleteList.size();
		int perCnt	= 500;
		int maxCnt	= size%perCnt>0
					? deleteList.size()/perCnt+1
					: deleteList.size()/perCnt;
		
		for(int i = 0; i < maxCnt; i++) {
			int start	= perCnt*i;
			int end		= perCnt*(i+1) < size? perCnt*(i+1) : size;
			result += insert("ddl.deleteDdlList", deleteList.subList(start, end));
		}
		
		return result;
	}
}
