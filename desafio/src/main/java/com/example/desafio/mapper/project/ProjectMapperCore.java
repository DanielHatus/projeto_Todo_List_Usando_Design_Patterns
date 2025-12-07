package com.example.desafio.mapper.project;

import com.example.desafio.dto.request.crud.project.post.ProjectPostDto;
import com.example.desafio.dto.request.crud.project.put.ProjectPutDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.model.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapperCore{
    @Mapping(target = "endDate",ignore = true)
    @Mapping(target = "passwordAccess",ignore = true)
    Project toEntity(ProjectPostDto projectPostDto);

    @Mapping(target = "endDate",ignore = true)
    @Mapping(target = "passwordAccess",ignore = true)
    Project updateEntityPut(@MappingTarget Project entity, ProjectPutDto projectPutDto);

    ResponseProjectDataDto toResponseProjectDataDto(Project project);

    default Page<ResponseProjectDataDto> toPageResponseProjectDataDto(Page<Project> projectsOrigins){
        Page<ResponseProjectDataDto> pageReturned=projectsOrigins.map(project->{
          ResponseProjectDataDto projectDto=toResponseProjectDataDto(project);
          return projectDto;
        });
        return pageReturned;
    }

}
