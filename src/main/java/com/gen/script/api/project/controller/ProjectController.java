package com.gen.script.api.project.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gen.script.api.project.domain.ProjectModel;
import com.gen.script.api.project.domain.ProjectVO;
import com.gen.script.api.project.domain.assembler.ProjectAssembler;
import com.gen.script.api.project.service.impl.ProjectService;
import com.gen.script.common.response.ResponseMessage;

import jakarta.annotation.Resource;

@RestController
public class ProjectController {
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@GetMapping("/api/project")
	public ResponseEntity<ProjectModel> getApi() throws Exception{
		ProjectVO vo = new ProjectVO();
		ProjectAssembler projectAssembler = new ProjectAssembler(getClass(), ProjectModel.class);		
		return ResponseEntity.ok(projectAssembler.toModel(vo));
	}
	
	@GetMapping("/api/project/list")
	public ResponseEntity<CollectionModel<EntityModel<ProjectVO>>> selectProjectList(ProjectVO vo) throws Exception{
		List<EntityModel<ProjectVO>> list = 
				projectService.selectProjectList(vo)
					.stream()
					.map((project) -> 
						EntityModel.of(project)
					).collect(Collectors.toList());
															
		
		if(list.size()<1) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity
					.status(HttpStatus.OK)
					.body(
						CollectionModel.of(list)
							.add(linkTo(methodOn(ProjectController.class).selectProjectList(vo)).withSelfRel().withType("GET").withMedia(MediaType.APPLICATION_JSON.getType()))
							.add(linkTo(methodOn(ProjectController.class).save(vo)).withRel("save").withType("POST").withMedia(MediaType.APPLICATION_FORM_URLENCODED.getType())));
	}
	
	@GetMapping("/api/procject/{projectCode}")
	public ResponseEntity<EntityModel<ResponseMessage>> get(@RequestBody ProjectVO vo) throws Exception{
		int result = projectService.save(vo);
		if(result <= 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.created(null).build();
	}
	
	@PostMapping("/api/procject")
	public ResponseEntity<EntityModel<ResponseMessage>> save(@RequestBody ProjectVO vo) throws Exception{
		int result = projectService.save(vo);
		if(result <= 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.created(null).build();
	}
}
