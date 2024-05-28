package com.gen.script.sys.code.service;

import java.util.List;

import com.gen.script.sys.code.domain.CommonCodeDTO;

public interface CommonCodeService {
	public List<CommonCodeDTO> selectCodeList(String codeId);
	public List<CommonCodeDTO> selectCodeDetailList(String codeId);
	public int saveCode(CommonCodeDTO vo);
	public int saveCodeDetail(CommonCodeDTO vo);
}
