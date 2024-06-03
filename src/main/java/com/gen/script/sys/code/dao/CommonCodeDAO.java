package com.gen.script.sys.code.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gen.script.common.dao.CommonDAO;
import com.gen.script.sys.code.domain.CommonCodeDTO;
import com.gen.script.utils.TokenUtil;

@Repository(value="commonCodeDAO")
public class CommonCodeDAO extends CommonDAO{
	public List<CommonCodeDTO> selectCodeList(CommonCodeDTO vo){
		return selectList("commonCode.selectCodeList", vo);
	}
	
	public List<CommonCodeDTO> selectCodeDetailList(CommonCodeDTO vo){
		return selectList("commonCode.selectCodeDetailList", vo);
	}
	
	public List<CommonCodeDTO> codeList(String code){
		CommonCodeDTO CommonCodeDTO = new CommonCodeDTO();
		CommonCodeDTO.setCode(code);
		List<CommonCodeDTO> result = selectCodeList(CommonCodeDTO);
		return result;
	}

	public List<CommonCodeDTO> codeDetailList(String code) {
		CommonCodeDTO CommonCodeDTO = new CommonCodeDTO();
		CommonCodeDTO.setCode(code);
		List<CommonCodeDTO> result = selectCodeDetailList(CommonCodeDTO);
		return result;
	}
	
	public int insertCode(List<CommonCodeDTO> addList) {
		int result = 0;
		
		for(CommonCodeDTO vo : addList) {
			vo.setInsId(TokenUtil.getUserName());
			vo.setUpdId(TokenUtil.getUserName());
			result += insert("commonCode.insertCode", vo);
		}
		
		return result;
	}
	
	public int updateCode(List<CommonCodeDTO> updateList) {
		int result = 0;
		
		for(CommonCodeDTO vo : updateList) {
			vo.setUpdId(TokenUtil.getUserName());
			result += update("commonCode.updateCode", vo);
		}
		
		return result;
	}
	
	public int deleteCode(List<CommonCodeDTO> deleteList) {
		int result = 0;
		
		for(CommonCodeDTO vo : deleteList) {
			result += delete("commonCode.deleteCode", vo);
		}
		return result;
	}
	
	public int insertCodeDetail(List<CommonCodeDTO> addList) {
		int result = 0;
		
		for(CommonCodeDTO vo : addList) {
			vo.setInsId(TokenUtil.getUserName());
			vo.setUpdId(TokenUtil.getUserName());
			result += insert("commonCode.insertCodeDetail", vo);
		}
		
		return result;
	}
	
	public int updateCodeDetail(List<CommonCodeDTO> updateList) {
		int result = 0;

		for(CommonCodeDTO vo : updateList) {
			vo.setInsId(TokenUtil.getUserName());
			vo.setUpdId(TokenUtil.getUserName());
			result += update("commonCode.updateCodeDetail", vo);
		}
		
		return result;
	}
	
	public int deleteCodeDetail(List<CommonCodeDTO> deleteList) {
		int result = 0;
		
		for(CommonCodeDTO vo : deleteList) {
			result += delete("commonCode.deleteCodeDetail", vo);
		}
		return result;
	}
}
