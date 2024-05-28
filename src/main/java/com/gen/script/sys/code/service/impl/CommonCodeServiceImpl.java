package com.gen.script.sys.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gen.script.sys.code.dao.CommonCodeDAO;
import com.gen.script.sys.code.domain.CommonCodeDTO;
import com.gen.script.sys.code.service.CommonCodeService;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;

@Transactional
@Service(value="commonCodeService")
public class CommonCodeServiceImpl implements CommonCodeService{
	@Resource(name = "commonCodeDAO")
	private CommonCodeDAO commonCodeDAO;
	
	@Override
	public List<CommonCodeDTO> selectCodeList(String codeId){
		return commonCodeDAO.codeList(codeId);
	}
	
	@Override
	public List<CommonCodeDTO> selectCodeDetailList(String codeId){
		return commonCodeDAO.codeDetailList(codeId);
	}
	
	@Override
	public int saveCodeDetail(CommonCodeDTO vo) {
		int result = 0;
		List<CommonCodeDTO> addList = vo.getAddCodeDetailList();
		List<CommonCodeDTO> deleteList = vo.getDeleteCodeDetailList();
		List<CommonCodeDTO> updateList = vo.getUpdateCodeDetailList();
		if(addList != null && !addList.isEmpty()) {
			result += commonCodeDAO.insertCodeDetail(addList);
		}
		
		if(deleteList != null && !deleteList.isEmpty()) {
			result += commonCodeDAO.deleteCodeDetail(deleteList);
		}
		
		if(updateList != null && !updateList.isEmpty()) {
			result += commonCodeDAO.updateCodeDetail(updateList);
		}
		
		return result;
	}
	
	@Override
	public int saveCode(CommonCodeDTO vo) {
		int result = 0;
		List<CommonCodeDTO> addList = vo.getAddList();
		List<CommonCodeDTO> deleteList = vo.getDeleteList();
		List<CommonCodeDTO> updateList = vo.getUpdateList();
		if(addList != null && !addList.isEmpty()) {
			result += commonCodeDAO.insertCode(addList);
		}
		
		if(deleteList != null && !deleteList.isEmpty()) {
			result += commonCodeDAO.deleteCode(deleteList);
		}
		
		if(updateList != null && !updateList.isEmpty()) {
			result += commonCodeDAO.updateCode(updateList);
		}
		
		return result;
	}
}
