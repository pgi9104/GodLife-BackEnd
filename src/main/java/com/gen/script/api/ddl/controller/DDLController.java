package com.gen.script.api.ddl.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gen.script.api.ddl.domain.DdlDTO;
import com.gen.script.api.ddl.domain.DdlEntity;
import com.gen.script.api.ddl.service.DdlService;
import com.gen.script.common.response.ResponseMessage;

import jakarta.annotation.Resource;
@RestController
public class DDLController {
	@Resource(name="ddlService")
	private DdlService ddlService;
	
	@GetMapping("/api/init/ddl")
	public ResponseEntity<ResponseMessage> getApi() throws Exception{
		ResponseMessage res = ResponseMessage.builder().build();
		
		//Link
		res.add("list", linkTo(methodOn(DDLController.class).list(null)).withRel("list").withType("get").withMedia(MediaType.APPLICATION_JSON_VALUE));
		res.add("save", linkTo(methodOn(DDLController.class).save(null)).withRel("save").withType("post").withMedia(MediaType.APPLICATION_JSON_UTF8.toString()));
		res.add("script", linkTo(methodOn(DDLController.class).script(null)).withRel("script").withType("get").withMedia(MediaType.APPLICATION_JSON_VALUE));
		res.add("alterScript", linkTo(methodOn(DDLController.class).alterScript(null)).withRel("alterScript").withType("get").withMedia(MediaType.APPLICATION_JSON_VALUE));
		
		//SelectBox
		List<DdlDTO> selectBox = ddlService.selectBox(new DdlDTO());
		Set<String> projectCodeSel = new HashSet<String>();
		projectCodeSel.add("");
		Set<String> tableNameSel = new HashSet<String>();
		tableNameSel.add("");
		Set<String> tableColSel = new HashSet<String>();
		tableColSel.add("");
		for(DdlDTO ddl : selectBox) {
			projectCodeSel.add(ddl.getProjectCode());
			tableNameSel.add(ddl.getTableName());
			tableColSel.add(ddl.getTableCol());
		}
		res.add("projectCodeSel", projectCodeSel);
		res.add("tableNameSel", tableNameSel);
		res.add("tableColSel", tableColSel);
		
		//메뉴명
		res.add("menuName", "DDL 정보");
		
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/api/ddl")
	public ResponseEntity<ResponseMessage> list(@ModelAttribute DdlDTO dto) throws Exception{
		ResponseMessage res = new ResponseMessage();
		Set<String> selectBox = new HashSet<String>();
		selectBox.add("");
		List<EntityModel<DdlEntity>> list = ddlService.list(dto).stream().map((ddl) -> {
			selectBox.add(ddl.getProjectCode());
			try {
				return ddl.toModel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		res.add("list", list);
		res.add("selectBox", selectBox);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/api/ddl/{projectCode}/{tableName}/{tableCol}")
	public ResponseEntity<ResponseMessage> get(DdlDTO dto) throws Exception{
		ResponseMessage res = new ResponseMessage();
		
		return ResponseEntity.ok().body(res);
	}
	
	@PostMapping("/api/ddl")
	public ResponseEntity<ResponseMessage> save(@RequestBody DdlDTO dto) throws Exception{
		ResponseMessage res = new ResponseMessage();
		ddlService.save(dto);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/api/ddl/script")
	public ResponseEntity<ResponseMessage> script(@ModelAttribute DdlDTO dto) throws Exception{
		ResponseMessage res = new ResponseMessage();
		String script = ddlService.script(dto);
		res.add("script", script);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/api/ddl/alter")
	public ResponseEntity<ResponseMessage> alterScript(@ModelAttribute DdlDTO dto) throws Exception{
		ResponseMessage res = new ResponseMessage();
		String script = ddlService.alterScript(dto);
		res.add("script", script);
		return ResponseEntity.ok().body(res);
	}
}
