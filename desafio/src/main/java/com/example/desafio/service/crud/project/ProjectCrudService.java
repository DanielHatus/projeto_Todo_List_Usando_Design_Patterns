package com.example.desafio.service.crud.project;

import com.example.desafio.dto.request.crud.project.put.ProjectPutDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.project.ProjectMapperCore;
import com.example.desafio.model.project.Project;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.utils.pageable.factory.PageableFactoryByClassReceived;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProjectCrudService{

    private final ProjectRepository repository;
    private final ProjectMapperCore mapperCore;
    private final PageableFactoryByClassReceived pageableFactoryByClassReceived;
    private final ParseDataFromIsoAmerican fromIsoAmerican;

    public ProjectCrudService(
            ProjectRepository repository,
            ProjectMapperCore mapperCore,
            PageableFactoryByClassReceived pageableFactoryByClassReceived,
            ParseDataFromIsoAmerican fromIsoAmerican) {

        this.repository = repository;
        this.mapperCore = mapperCore;
        this.pageableFactoryByClassReceived = pageableFactoryByClassReceived;
        this.fromIsoAmerican=fromIsoAmerican;
    }

    public Page<ResponseProjectDataDto> getProjectByPageOrder(Integer page,Integer size,String order,String direction){
        Pageable pageable=pageableFactoryByClassReceived.pageableFactory(Project.class,page,size,order,direction);
        return mapperCore.toPageResponseProjectDataDto(repository.findAll(pageable));
    }

    public ResponseProjectDataDto getById(Long id){
      return mapperCore.toResponseProjectDataDto(getEntityByIdOrThrow(id));
    }

    public ResponseProjectDataDto addNewProject(Project entity){
        repository.save(entity);
        log.debug("✅ Everything went correctly and the project was successfully saved to the database, " +
                "returning the DTO in the response body.");

        return mapperCore.toResponseProjectDataDto(entity);
    }

    @Transactional
    public ResponseProjectDataDto updateProjectPut(Long id,ProjectPutDto projectPutDto){
      Project entity=getEntityByIdOrThrow(id);

      mapperCore.updateEntityPut(entity,projectPutDto);
      log.debug("✅ The project entity was successfully mapped using mapstruct.");

      entity.setEndDate(fromIsoAmerican.parseDataFormatBrazilianImAmerican(projectPutDto.getEndDate()));

      repository.save(entity);
      log.debug("The entity data update was successful. The DTO was returned to the response body.");
      return mapperCore.toResponseProjectDataDto(entity);
    }

    public void deleteProject(Long id){
        try{
            repository.deleteById(id);
            log.debug("✅ project successfully deleted.");
        }
        catch (EmptyResultDataAccessException e){
            log.error("❌ No project registered with ID {} was found on the server.",id);
            throw new NotFoundException("id not found in database");
        }
    }

    private Project getEntityByIdOrThrow(Long id){
        Project entity=repository.findById(id)
                .orElseThrow(()-> {
                    log.error("❌ No project registered with ID {} was found on the server.",id);
                    return new NotFoundException("id not found in database");
                });
        log.debug("✅ project {} was found on the server with ID {}.",entity.getProjectCreator(),id);
        return entity;
    }

}
