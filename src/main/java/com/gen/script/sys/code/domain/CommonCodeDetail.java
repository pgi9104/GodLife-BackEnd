package com.gen.script.sys.code.domain;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodeDetail extends CommonVO{
	private String code;			//공통코드
	private String codeDetailCd;	//상세코드
	private String codeDetailName;	//상세코드명
	private String codeDesc;		//설명
}
