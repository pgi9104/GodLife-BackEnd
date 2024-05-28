package com.gen.script.sys.code;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gen.script.common.response.ResponseMessage;
import com.gen.script.sys.code.domain.CommonCode;
import com.gen.script.sys.code.domain.CommonCodeDTO;
import com.gen.script.sys.code.domain.CommonCodeDetail;
import com.gen.script.sys.code.service.CommonCodeService;

import jakarta.annotation.Resource;

@RestController
public class CommonCodeController {
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@GetMapping("/sys/api/code")
	public ResponseEntity<ResponseMessage> init() throws Exception{
		ResponseMessage res = ResponseMessage.builder().build();

		List<CommonCodeDTO> selectYn = commonCodeService.selectCodeDetailList("CD001");
		res.add("selectYn", selectYn);
		res.add("menuName", "공통코드 등록");
		res.add("menuDetailName", "상세공통코드 등록");
		
		CommonCodeDTO code = new CommonCodeDTO();
		res.add("get", linkTo(methodOn(getClass()).selectCodeList(code)).withRel("get").withMedia(MediaType.APPLICATION_JSON_VALUE).withType("GET"));
		res.add("save", linkTo(methodOn(getClass()).saveCodeList(code)).withRel("save").withType("POST").withMedia(MediaType.APPLICATION_JSON_VALUE));
		res.add("saveModal", linkTo(methodOn(getClass()).saveCodeDetailList(code)).withRel("saveModal").withType("POST").withMedia(MediaType.APPLICATION_JSON_VALUE));

		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/sys/code")
	public ModelAndView selectCodeList(CommonCodeDTO vo) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		List<CommonCodeDTO> list = commonCodeService.selectCodeList(vo.getCode());
		List<EntityModel<CommonCode>> result = new ArrayList<EntityModel<CommonCode>>();
		for(CommonCodeDTO item : list) {
			result.add(item.toCommonCodeModel());
		}
		mav.addObject("selectCodeList", result);
		return mav;
	}
	
	@GetMapping("/sys/code/{code}")
	public ModelAndView selectCodeDetailList(@RequestParam("code") String code) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		List<EntityModel<CommonCodeDetail>> result = new ArrayList<>();
		List<CommonCodeDTO> list = commonCodeService.selectCodeDetailList(code);
		for(CommonCodeDTO item : list) {
			result.add(item.toCommonCodeDetailModel());
		}
		mav.addObject("selectCodeDetailList", result);
		return mav;
	}
	
	@PostMapping(value="/sys/code/saveCode.ajax")
	public ModelAndView saveCodeList(@RequestBody CommonCodeDTO vo) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("result",commonCodeService.saveCode(vo));
		return mav;
	}
	
	@PostMapping(value="/sys/code/saveCodeDetail.ajax")
	public ModelAndView saveCodeDetailList(@RequestBody CommonCodeDTO vo) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("result",commonCodeService.saveCodeDetail(vo));
		return mav;
	}
}
