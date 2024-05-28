package com.gen.script.sys.code.domain;

import com.gen.script.common.domain.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCode extends CommonVO{
	private String code;			//공통코드
	private String codeName;		//코드네임
	private String codeDesc;		//설명
}
