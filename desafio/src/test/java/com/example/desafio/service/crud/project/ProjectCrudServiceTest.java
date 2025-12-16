package com.example.desafio.service.crud.project;

import com.example.desafio.dto.request.crud.project.put.ProjectPutDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.enums.Role;
import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.project.ProjectMapperCore;
import com.example.desafio.model.project.Project;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.pageable.factory.PageableFactoryByClassReceived;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import com.example.desafio.utils.validation.user.is.creator.project.UserRequestIsCreatorProject;
import com.example.desafio.utils.validation.user.is.role.admin.ValidationIfUserIsRoleAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectCrudServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  ProjectMapperCore mapperCore;

    @Mock
    private  PageableFactoryByClassReceived pageableFactoryByClassReceived;

    @Mock
    private  ParseDataFromIsoAmerican fromIsoAmerican;

    @Mock
    private  ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin;

    @Mock
    private  GetUsernameByContextHolder getUsernameByContextHolder;

    @Mock
    private  UserRequestIsCreatorProject userRequestIsCreatorProject;

    @InjectMocks
    private ProjectCrudService projectCrudService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);}

    @Test
    void getProjectByPageOrderSuccess(){
        Integer page=0;
        Integer size=1;
        String order="id";
        String direction="asc";

        Project project1=new Project();
        project1.setProjectCreator("creator");
        project1.setEndDate(LocalDate.of(2025,1,1));
        project1.setNameProject("nameProject");
        project1.setId(1L);
        project1.setDescription("description");
        project1.setPasswordAccess("password");
        project1.setStartDate(LocalDate.of(2025,1,1));

        Project project2=new Project();
        project2.setProjectCreator("creator");
        project2.setEndDate(LocalDate.of(2025,1,1));
        project2.setNameProject("nameProject");
        project2.setId(2L);
        project2.setDescription("description");
        project2.setPasswordAccess("password");
        project2.setStartDate(LocalDate.of(2025,1,1));

        Pageable pageable= PageRequest.of(page,size, Sort.by(order,direction));
        List<Project> projectList=List.of(project1,project2);
        Page<Project> pageProjects=new PageImpl<>(projectList,pageable,projectList.size());


        ResponseProjectDataDto responseProjectDataDto1= new ResponseProjectDataDto();
        responseProjectDataDto1.setProjectCreator(project1.getProjectCreator());
        responseProjectDataDto1.setDescription(project1.getDescription());
        responseProjectDataDto1.setNameProject(project1.getNameProject());
        responseProjectDataDto1.setStartDate(project1.getStartDate());
        responseProjectDataDto1.setId(project1.getId());

        ResponseProjectDataDto responseProjectDataDto2=new ResponseProjectDataDto();
        responseProjectDataDto2.setProjectCreator(project2.getProjectCreator());
        responseProjectDataDto2.setDescription(project2.getDescription());
        responseProjectDataDto2.setNameProject(project2.getNameProject());
        responseProjectDataDto2.setStartDate(project2.getStartDate());
        responseProjectDataDto2.setId(project2.getId());

        List<ResponseProjectDataDto> listProjectsDto=List.of(responseProjectDataDto1,responseProjectDataDto2);
        Page<ResponseProjectDataDto>  pageProjectsDto=new PageImpl<>(listProjectsDto,pageable,listProjectsDto.size());

        when(pageableFactoryByClassReceived.pageableFactory(Project.class,page,size,order,direction)).thenReturn(pageable);
        when(projectRepository.findAll(pageable)).thenReturn(pageProjects);
        when(mapperCore.toPageResponseProjectDataDto(pageProjects)).thenReturn(pageProjectsDto);

        Page<ResponseProjectDataDto> result=this.projectCrudService.getProjectByPageOrder(page,size,order,direction);
        assertEquals(pageProjectsDto.getPageable(),result.getPageable());
        assertEquals(pageProjectsDto.getSize(),result.getSize());
    }

    @Test
    void getByIdSuccess(){
        Long idRequest=1L;

        Project project1=new Project();
        project1.setProjectCreator("creator");
        project1.setEndDate(LocalDate.of(2025,1,1));
        project1.setNameProject("nameProject");
        project1.setId(1L);
        project1.setDescription("description");
        project1.setPasswordAccess("password");
        project1.setStartDate(LocalDate.of(2025,1,1));

        ResponseProjectDataDto responseProjectDataDto1= new ResponseProjectDataDto();
        responseProjectDataDto1.setProjectCreator(project1.getProjectCreator());
        responseProjectDataDto1.setDescription(project1.getDescription());
        responseProjectDataDto1.setNameProject(project1.getNameProject());
        responseProjectDataDto1.setStartDate(project1.getStartDate());
        responseProjectDataDto1.setId(project1.getId());

        when(projectRepository.findById(idRequest)).thenReturn(Optional.of(project1));
        when(mapperCore.toResponseProjectDataDto(project1)).thenReturn(responseProjectDataDto1);

        ResponseProjectDataDto result=this.projectCrudService.getById(idRequest);

        assertEquals(responseProjectDataDto1.getId(),result.getId());
        assertEquals(responseProjectDataDto1.getNameProject(),result.getNameProject());
        verify(projectRepository,times(1)).findById(idRequest);
    }

    @Test
    void getByIdFailerPerIdNotFoundInDb(){
        Long idRequest=2L;

        Project project1=new Project();
        project1.setProjectCreator("creator");
        project1.setEndDate(LocalDate.of(2025,1,1));
        project1.setNameProject("nameProject");
        project1.setId(1L);
        project1.setDescription("description");
        project1.setPasswordAccess("password");
        project1.setStartDate(LocalDate.of(2025,1,1));

        ResponseProjectDataDto responseProjectDataDto1= new ResponseProjectDataDto();
        responseProjectDataDto1.setProjectCreator(project1.getProjectCreator());
        responseProjectDataDto1.setDescription(project1.getDescription());
        responseProjectDataDto1.setNameProject(project1.getNameProject());
        responseProjectDataDto1.setStartDate(project1.getStartDate());
        responseProjectDataDto1.setId(project1.getId());

        when(projectRepository.findById(idRequest)).thenReturn(Optional.empty()).thenThrow(new NotFoundException("id not found"));

        assertThrows(NotFoundException.class,()-> this.projectCrudService.getById(idRequest));
        verify(projectRepository,times(1)).findById(idRequest);
    }

    @Test
    void addNewProjectSuccess() {
        Project entity=new Project();
        entity.setProjectCreator("creator");
        entity.setEndDate(LocalDate.of(2025,1,1));
        entity.setNameProject("nameProject");
        entity.setDescription("description");
        entity.setPasswordAccess("password");
        entity.setStartDate(LocalDate.of(2025,1,1));

        Project entitySaved=new Project();
        entitySaved.setId(1L);
        entitySaved.setProjectCreator("creator");
        entitySaved.setEndDate(LocalDate.of(2025,1,1));
        entitySaved.setNameProject("nameProject");
        entitySaved.setDescription("description");
        entitySaved.setPasswordAccess("password");
        entitySaved.setStartDate(LocalDate.of(2025,1,1));

        ResponseProjectDataDto responseProjectDataDto= new ResponseProjectDataDto();
        responseProjectDataDto.setProjectCreator(entitySaved.getProjectCreator());
        responseProjectDataDto.setDescription(entitySaved.getDescription());
        responseProjectDataDto.setNameProject(entitySaved.getNameProject());
        responseProjectDataDto.setStartDate(entitySaved.getStartDate());
        responseProjectDataDto.setId(entitySaved.getId());

       when(projectRepository.save(any(Project.class))).thenReturn(entitySaved);
       when(mapperCore.toResponseProjectDataDto(any(Project.class))).thenReturn(responseProjectDataDto);
       ResponseProjectDataDto result=this.projectCrudService.saveProjectInDbAndReturnEntityDtoMapped(entity);

       assertEquals(responseProjectDataDto.getNameProject(),result.getNameProject());
       assertEquals(responseProjectDataDto.getProjectCreator(),result.getProjectCreator());
       verify(projectRepository,times(1)).save(entity);
    }

    @Test
    void updateDataProjectSuccessWithAdminUser(){
        Long idRequest=1L;
        String usernameRequest="usernameRequest";

        ProjectPutDto entityRequest=new ProjectPutDto();
        entityRequest.setEndDate("01/01/2025");
        entityRequest.setNameProject("nameProject");
        entityRequest.setDescription("description");

        Project entityOrigin=new Project();
        entityOrigin.setId(1L);
        entityOrigin.setProjectCreator("creator");
        entityOrigin.setEndDate(LocalDate.of(2025,1,1));
        entityOrigin.setNameProject("nameProject");
        entityOrigin.setDescription("description");
        entityOrigin.setPasswordAccess("password");

        User user1=new User();
        user1.setId(1L);
        user1.setEmail("teste@gmail.com");
        user1.setPassword("testeDecoded");
        user1.setFirstName("teste");
        user1.setLastName("teste");
        user1.setUsername("teste");

       when(projectRepository.findById(idRequest)).thenReturn(Optional.of(entityOrigin));

       when(getUsernameByContextHolder.execute()).thenReturn(usernameRequest);

       when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user1));

       when(validationIfUserIsRoleAdmin.userIsAdmin(any(Role.class))).thenReturn(true);

       doAnswer(invocation->{

       Project entityArgument=invocation.getArgument(0);
       ProjectPutDto dtoArgument=invocation.getArgument(1);
       entityArgument.setId(1L);
       entityArgument.setNameProject(dtoArgument.getNameProject());
       entityArgument.setEndDate(LocalDate.of(2025,1,1));
       entityArgument.setDescription(dtoArgument.getDescription());
       return null;
       }).when(mapperCore).updateEntityPut(any(Project.class),any(ProjectPutDto.class));

       when(projectRepository.save(any(Project.class))).thenAnswer(invocation->{
         return invocation.getArgument(0);
       });

       when(mapperCore.toResponseProjectDataDto(any(Project.class))).thenAnswer(invocation->{
           ResponseProjectDataDto responseProjectDataDto=new ResponseProjectDataDto();
           Project entity=invocation.getArgument(0);
           responseProjectDataDto.setId(entity.getId());
           responseProjectDataDto.setProjectCreator(entity.getProjectCreator());
           responseProjectDataDto.setNameProject(entity.getNameProject());
           responseProjectDataDto.setDescription(entity.getDescription());
           responseProjectDataDto.setStartDate(entity.getStartDate());
           responseProjectDataDto.setEndDate(entity.getEndDate());
           return responseProjectDataDto;
       });

       ResponseProjectDataDto result=this.projectCrudService.updateDataProject(1L,entityRequest);

       assertEquals(entityRequest.getNameProject(),result.getNameProject());
    }

    @Test
    void updateDataProjectSuccessWithUserIsCreatorProject(){
        Long idRequest=1L;
        String usernameRequest="usernameRequest";

        ProjectPutDto entityRequest=new ProjectPutDto();
        entityRequest.setEndDate("01/01/2025");
        entityRequest.setNameProject("nameProject");
        entityRequest.setDescription("description");

        Project entityOrigin=new Project();
        entityOrigin.setId(1L);
        entityOrigin.setProjectCreator("creator");
        entityOrigin.setEndDate(LocalDate.of(2025,1,1));
        entityOrigin.setNameProject("nameProject");
        entityOrigin.setDescription("description");
        entityOrigin.setPasswordAccess("password");

        User user1=new User();
        user1.setId(1L);
        user1.setEmail("teste@gmail.com");
        user1.setPassword("testeDecoded");
        user1.setFirstName("teste");
        user1.setLastName("teste");
        user1.setUsername("usernameRequest");

        when(projectRepository.findById(idRequest)).thenReturn(Optional.of(entityOrigin));

        when(getUsernameByContextHolder.execute()).thenReturn(usernameRequest);

        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user1));

        when(validationIfUserIsRoleAdmin.userIsAdmin(any(Role.class))).thenReturn(false);

        doNothing().when(userRequestIsCreatorProject).throwIfUserRequestNotCreatorProject(any(String.class),any(String.class));

        doAnswer(invocation->{
            Project entityArgument=invocation.getArgument(0);
            ProjectPutDto dtoArgument=invocation.getArgument(1);
            entityArgument.setId(1L);
            entityArgument.setNameProject(dtoArgument.getNameProject());
            entityArgument.setEndDate(LocalDate.of(2025,1,1));
            entityArgument.setDescription(dtoArgument.getDescription());
            return null;
        }).when(mapperCore).updateEntityPut(any(Project.class),any(ProjectPutDto.class));

        when(projectRepository.save(any(Project.class))).thenAnswer(invocation->{
            return invocation.getArgument(0);
        });

        when(mapperCore.toResponseProjectDataDto(any(Project.class))).thenAnswer(invocation->{
            ResponseProjectDataDto responseProjectDataDto=new ResponseProjectDataDto();
            Project entity=invocation.getArgument(0);
            responseProjectDataDto.setId(entity.getId());
            responseProjectDataDto.setProjectCreator(entity.getProjectCreator());
            responseProjectDataDto.setNameProject(entity.getNameProject());
            responseProjectDataDto.setDescription(entity.getDescription());
            responseProjectDataDto.setStartDate(entity.getStartDate());
            responseProjectDataDto.setEndDate(entity.getEndDate());
            return responseProjectDataDto;
        });

        ResponseProjectDataDto result=this.projectCrudService.updateDataProject(1L,entityRequest);

        assertEquals(entityRequest.getNameProject(),result.getNameProject());
    }

    @Test
    void updateDataProjectFailedPerIdNotFoundInDb(){
        Long idRequest=2L;

        ProjectPutDto entityRequest=new ProjectPutDto();
        entityRequest.setEndDate("01/01/2025");
        entityRequest.setNameProject("nameProject");
        entityRequest.setDescription("description");

        Project entityOrigin=new Project();
        entityOrigin.setId(1L);
        entityOrigin.setProjectCreator("creator");
        entityOrigin.setEndDate(LocalDate.of(2025,1,1));
        entityOrigin.setStartDate(LocalDate.of(2025,1,1));
        entityOrigin.setNameProject("nameProject");
        entityOrigin.setDescription("description");
        entityOrigin.setPasswordAccess("password");

        when(projectRepository.findById(idRequest)).thenReturn(Optional.empty()).thenThrow(new NotFoundException("id not found"));
        assertThrows(NotFoundException.class,()->this.projectCrudService.updateDataProject(idRequest,entityRequest));
        verify(projectRepository,times(1)).findById(idRequest);
    }

    @Test
    void updateDataProjectFailedPerUserNotAdminAndNotCreatorProject(){
        Long idRequest=1L;
        String usernameRequest="usernameRequest";

        ProjectPutDto entityDtoRequest=new ProjectPutDto();
        entityDtoRequest.setEndDate("01/01/2025");
        entityDtoRequest.setNameProject("nameProject");
        entityDtoRequest.setDescription("description");

        Project entityOrigin=new Project();
        entityOrigin.setId(1L);
        entityOrigin.setProjectCreator("creator");
        entityOrigin.setEndDate(LocalDate.of(2025,1,1));
        entityOrigin.setNameProject("nameProject");
        entityOrigin.setDescription("description");
        entityOrigin.setPasswordAccess("password");

        User user1=new User();
        user1.setId(1L);
        user1.setEmail("teste@gmail.com");
        user1.setPassword("testeDecoded");
        user1.setFirstName("teste");
        user1.setLastName("teste");
        user1.setUsername("teste");

        when(projectRepository.findById(idRequest)).thenReturn(Optional.of(entityOrigin));

        when(getUsernameByContextHolder.execute()).thenReturn(usernameRequest);

        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user1));

        when(validationIfUserIsRoleAdmin.userIsAdmin(any(Role.class))).thenReturn(false);

        doThrow(new BadRequestException("You are not the project creator and therefore cannot update the data.")).when(userRequestIsCreatorProject)
                        .throwIfUserRequestNotCreatorProject(any(String.class),any(String.class));

        assertThrows(BadRequestException.class,()-> this.projectCrudService.updateDataProject(idRequest,entityDtoRequest));
    }

    @Test
    void deleteProjectSuccess() {
        Long idRequest=1L;
        doNothing().when(projectRepository).deleteById(idRequest);
        this.projectCrudService.deleteProject(idRequest);
        verify(projectRepository,times(1)).deleteById(idRequest);
    }

    @Test
    void deleteProjectFailedPerIdNotFoundInDb(){
        Long idRequest=1L;
        doThrow(new EmptyResultDataAccessException(1)).when(projectRepository).deleteById(idRequest);
        assertThrows(NotFoundException.class,()->this.projectCrudService.deleteProject(idRequest));
        verify(projectRepository,times(1)).deleteById(idRequest);
    }
}