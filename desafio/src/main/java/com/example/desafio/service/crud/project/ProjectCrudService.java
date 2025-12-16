package com.example.desafio.service.crud.project;

import com.example.desafio.dto.request.crud.project.put.ProjectPutDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.project.ProjectMapperCore;
import com.example.desafio.model.project.Project;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.pageable.factory.PageableFactoryByClassReceived;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import com.example.desafio.utils.validation.user.is.creator.project.UserRequestIsCreatorProject;
import com.example.desafio.utils.validation.user.is.role.admin.ValidationIfUserIsRoleAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProjectCrudService{

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapperCore mapperCore;
    private final PageableFactoryByClassReceived pageableFactoryByClassReceived;
    private final ParseDataFromIsoAmerican fromIsoAmerican;
    private final ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin;
    private final GetUsernameByContextHolder getUsernameByContextHolder;
    private final UserRequestIsCreatorProject userRequestIsCreatorProject;

    public ProjectCrudService(
            ProjectRepository projectRepository,
            UserRepository userRepository,
            ProjectMapperCore mapperCore,
            PageableFactoryByClassReceived pageableFactoryByClassReceived,
            ParseDataFromIsoAmerican fromIsoAmerican,
            ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin,
            GetUsernameByContextHolder getUsernameByContextHolder,
            UserRequestIsCreatorProject userRequestIsCreatorProject) {

        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.mapperCore = mapperCore;
        this.pageableFactoryByClassReceived = pageableFactoryByClassReceived;
        this.fromIsoAmerican = fromIsoAmerican;
        this.validationIfUserIsRoleAdmin = validationIfUserIsRoleAdmin;
        this.getUsernameByContextHolder = getUsernameByContextHolder;
        this.userRequestIsCreatorProject = userRequestIsCreatorProject;
    }

    public Page<ResponseProjectDataDto> getProjectByPageOrder(Integer page, Integer size, String order, String direction){
        Pageable pageable=pageableFactoryByClassReceived.pageableFactory(Project.class,page,size,order,direction);
        return mapperCore.toPageResponseProjectDataDto(projectRepository.findAll(pageable));
    }

    public ResponseProjectDataDto getById(Long id){
      return mapperCore.toResponseProjectDataDto(getEntityByIdOrThrow(id));
    }

    public ResponseProjectDataDto saveProjectInDbAndReturnEntityDtoMapped(Project entity){
        projectRepository.save(entity);
        log.debug("✅ Everything went correctly and the project was successfully saved to the database, " +
                "returning the DTO in the response body.");

        return mapperCore.toResponseProjectDataDto(entity);
    }

    @Transactional
    public ResponseProjectDataDto updateDataProject(Long id,ProjectPutDto projectPutDto){
      Project entityProject=getEntityByIdOrThrow(id);

      String usernameRequestAccount=getUsernameByContextHolder.execute();

      User entityUser=userRepository.findByUsername(usernameRequestAccount).get();
      log.debug("✅ The entity was successfully retrieved by the username in the request.");

      if (!validationIfUserIsRoleAdmin.userIsAdmin(entityUser.getRole())){
          log.debug("✅ The user is not an admin; check if they are the project creator.");
          userRequestIsCreatorProject.throwIfUserRequestNotCreatorProject(usernameRequestAccount,entityProject.getProjectCreator());
       }
      log.debug("✅ The user has been successfully validated; they can now update the project data.");

      mapperCore.updateEntityPut(entityProject,projectPutDto);
      log.debug("✅ The project entity was successfully mapped using mapstruct.");

      entityProject.setEndDate(fromIsoAmerican.parseDataFormatBrazilianImAmerican(projectPutDto.getEndDate()));

      projectRepository.save(entityProject);
      log.debug("✅ The entity data update was successful. The DTO was returned to the response body.");

      return mapperCore.toResponseProjectDataDto(entityProject);
    }

    public void deleteProject(Long id){
        try{
            projectRepository.deleteById(id);
            log.debug("✅ project successfully deleted.");
        }
        catch (EmptyResultDataAccessException e){
            log.error("❌ No project registered with ID {} was found on the server.",id);
            throw new NotFoundException("id not found in database");
        }
    }

    private Project getEntityByIdOrThrow(Long id){
        Project entity=projectRepository.findById(id)
                .orElseThrow(()-> {
                    log.error("❌ No project registered with ID {} was found on the server.",id);
                    return new NotFoundException("id not found in database");
                });
        log.debug("✅ project {} was found on the server with ID {}.",entity.getProjectCreator(),id);
        return entity;
    }

}
