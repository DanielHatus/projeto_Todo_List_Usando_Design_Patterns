package com.example.desafio.facade.register.project;

import com.example.desafio.dto.request.crud.project.post.ProjectPostDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.mapper.project.ProjectMapperCore;
import com.example.desafio.model.project.Project;
import com.example.desafio.service.crud.project.ProjectCrudService;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import org.springframework.stereotype.Component;

@Component
public class RegisterNewProjectFacade{
    private final GetUsernameByContextHolder usernameByContextHolder;
    private final ProjectMapperCore mapperCore;
    private final ParseDataFromIsoAmerican fromIsoAmerican;
    private final ProjectCrudService projectCrudService;

    public RegisterNewProjectFacade(
            GetUsernameByContextHolder usernameByContextHolder,
            ProjectMapperCore mapperCore,
            ParseDataFromIsoAmerican fromIsoAmerican,
            ProjectCrudService projectCrudService) {

        this.usernameByContextHolder = usernameByContextHolder;
        this.mapperCore = mapperCore;
        this.fromIsoAmerican = fromIsoAmerican;
        this.projectCrudService=projectCrudService;
    }

    public ResponseProjectDataDto execute(ProjectPostDto projectPostDto){
        Project entity=mapperCore.toEntity(projectPostDto);
        entity.setProjectCreator(usernameByContextHolder.execute());
        entity.setEndDate(fromIsoAmerican.parseDataFormatBrazilianImAmerican(projectPostDto.getEndDate()));
        return  projectCrudService.addNewProject(entity);
    }
}
