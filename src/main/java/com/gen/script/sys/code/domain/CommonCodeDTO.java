package com.gen.script.sys.code.domain;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;

import com.gen.script.common.domain.CommonVO;
import com.gen.script.sys.code.CommonCodeController;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodeDTO extends CommonVO{
	private String code;			//공통코드
	private String codeName;		//코드네임
	private String codeDetailCd;	//상세코드
	private String codeDetailName;	//상세코드명
	private String codeDesc;		//설명
	
	private List<CommonCodeDTO> addList;
	private List<CommonCodeDTO> deleteList;
	private List<CommonCodeDTO> updateList;
	
	private List<CommonCodeDTO> addCodeDetailList;
	private List<CommonCodeDTO> deleteCodeDetailList;
	private List<CommonCodeDTO> updateCodeDetailList;
	
	public CommonCode toCommonCode() throws Exception{
		CommonCode code = new CommonCode();
		code.setCode(this.getCode());
		code.setCodeDesc(this.getCodeDesc());
		code.setCodeName(this.getCodeName());
		code.setSortSeq(this.getSortSeq());
		return code;
	}
	
	public EntityModel<CommonCode> toCommonCodeModel() throws Exception{
		CommonCode code = this.toCommonCode();
		
		EntityModel<CommonCode> model = EntityModel
				.of(code)
				.add(linkTo(methodOn(CommonCodeController.class).selectCodeList(this)).withRel("get").withType("GET").withMedia(MediaType.APPLICATION_JSON_VALUE))
				.add(linkTo(methodOn(CommonCodeController.class).selectCodeDetailList(code.getCode())).withRel("getModal").expand(code.getCode()).withType("GET").withMedia(MediaType.APPLICATION_JSON_VALUE));
				
		return model;
	}
	
	public CommonCodeDetail toCommonCodeDetail() throws Exception{
		CommonCodeDetail code = new CommonCodeDetail();
		code.setCode(this.getCode());
		code.setCodeDetailCd(this.getCodeDetailCd());
		code.setCodeDesc(this.getCodeDesc());
		code.setCodeDetailName(this.getCodeDetailName());
		code.setSortSeq(this.getSortSeq());
		return code;
	}
	
	public EntityModel<CommonCodeDetail> toCommonCodeDetailModel() throws Exception{
		CommonCodeDetail code = this.toCommonCodeDetail();
		
		EntityModel<CommonCodeDetail> model = EntityModel.of(code);
				
		return model;
	}
}
