package com.gen.script.api.project.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.gen.script.api.project.controller.ProjectController;
import com.gen.script.api.project.domain.ProjectModel;
import com.gen.script.api.project.domain.ProjectVO;

import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectAssembler extends RepresentationModelAssemblerSupport<ProjectVO, ProjectModel>{
	public ProjectAssembler(Class<?> controllerClass, Class<ProjectModel> resourceType) {
		super(controllerClass, resourceType);
	}
	
	@Override
    public ProjectModel toModel(ProjectVO entity){

		ProjectModel model = instantiateModel(entity);
        try {
			model.add(linkTo(methodOn(ProjectController.class).getApi()).withRel("api").withType(HttpMethod.GET.name()))
					.add(linkTo(methodOn(ProjectController.class).save(entity)).withRel("save").withType(HttpMethod.POST.name()))
			        .add(linkTo(methodOn(ProjectController.class).get(entity)).withSelfRel().withType(HttpMethod.GET.name()))
			        .add(linkTo(methodOn(ProjectController.class).selectProjectList(entity)).withRel("list").withType(HttpMethod.GET.name()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

        model.setProjectCode(entity.getProjectCode());
        model.setPackages(entity.getPackages());
        model.setProjectName(entity.getProjectName());
        model.setProjectComment(entity.getProjectComment());
        model.setSortSeq(entity.getSortSeq());
        return model;
    }
}
