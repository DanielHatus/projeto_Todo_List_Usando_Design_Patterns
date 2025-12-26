package com.example.desafio.service.crud.task;

import com.example.desafio.dto.request.crud.task.put.TaskPutDto;
import com.example.desafio.dto.response.crud.task.ResponseTaskDataDto;
import com.example.desafio.enums.Priority;
import com.example.desafio.enums.Role;
import com.example.desafio.enums.Status;
import com.example.desafio.exceptions.typo.runtime.forbidden.ForbiddenException;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.task.TaskMapperCore;
import com.example.desafio.model.project.Project;
import com.example.desafio.model.task.Task;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.repository.task.TaskRepository;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import com.example.desafio.utils.validation.user.is.creator.task.UserRequestIsCreatorTask;
import com.example.desafio.utils.validation.user.is.role.admin.ValidationIfUserIsRoleAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskCrudServiceTest {

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  ProjectRepository projectRepository;

    @Mock
    private  TaskMapperCore taskMapperCore;

    @Mock
    private  UserRequestIsCreatorTask userRequestIsCreatorTask;

    @Mock
    private  GetUsernameByContextHolder getUsernameByContextHolder;

    @Mock
    private  ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin;

    @Mock
    private  ParseDataFromIsoAmerican parseDataFromIsoAmerican;

    @InjectMocks
    private TaskCrudService taskCrudService;

    @BeforeEach
    void startUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTasksByOrderSuccess() {
        Priority priorityRequest=Priority.MEDIUM;
        Status statusRequest=Status.TODO;
        Long idProjectRequest=1L;

        User user= new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);
        user.setUsername("username");
        user.setPassword("passwordEncrypted");

        Project project= new Project();
        project.setUser(user);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setId(1L);
        project.setEndDate(LocalDate.now().plusDays(1L));

        Task task= new Task();
        task.setUser(user);
        task.setProject(project);
        task.setId(1L);
        task.setStatus(Status.TODO);
        task.setPriority(Priority.MEDIUM);
        task.setTitleTask("titleTask");
        task.setDescription("description");
        task.setDueDate(LocalDate.now().plusDays(1L));

        List<Task> taskByFilters=List.of(task);

        when(projectRepository.existsById(any(Long.class))).thenReturn(true);

        when(taskRepository.findByStatusAndPriorityAndProjectId(any(Status.class),any(Priority.class),any(Long.class))).thenReturn(taskByFilters);

        doAnswer(invocation->{
            List<Task> taskParam=invocation.getArgument(0);
            ResponseTaskDataDto responseTaskDto= new ResponseTaskDataDto();
            responseTaskDto.setDescription(taskParam.get(0).getDescription());
            responseTaskDto.setTitleTask(taskParam.get(0).getTitleTask());
            responseTaskDto.setId(taskParam.get(0).getId());
            responseTaskDto.setPriority(taskParam.get(0).getPriority());
            responseTaskDto.setStatus(taskParam.get(0).getStatus());
            responseTaskDto.setDueDate(taskParam.get(0).getDueDate());
            responseTaskDto.setStartDate(taskParam.get(0).getStartDate());
            return List.of(responseTaskDto);
        }).when(taskMapperCore).listEntityTasksInResponseTaskDataDto(any(List.class));

        List<ResponseTaskDataDto> result=this.taskCrudService.getTasksByOrder(statusRequest,priorityRequest,idProjectRequest);

        assertEquals(task.getDescription(), result.get(0).getDescription());
        verify(taskRepository,times(1)).findByStatusAndPriorityAndProjectId(any(Status.class),any(Priority.class),any(Long.class));
    }

    @Test
    void getTaskByIdSuccess(){
        Long idRequest=1L;
        User user= new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);
        user.setUsername("username");
        user.setPassword("passwordEncrypted");

        Project project= new Project();
        project.setUser(user);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setId(1L);
        project.setEndDate(LocalDate.now().plusDays(1L));

        Task task= new Task();
        task.setUser(user);
        task.setProject(project);
        task.setId(1L);
        task.setStatus(Status.TODO);
        task.setPriority(Priority.MEDIUM);
        task.setTitleTask("titleTask");
        task.setDescription("description");
        task.setDueDate(LocalDate.now().plusDays(1L));

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(task));

        ResponseTaskDataDto responseDto= new ResponseTaskDataDto();
        responseDto.setStartDate(task.getStartDate());
        responseDto.setId(task.getId());
        responseDto.setDescription(task.getDescription());
        responseDto.setTitleTask(task.getTitleTask());
        responseDto.setStatus(task.getStatus());
        responseDto.setPriority(task.getPriority());
        responseDto.setDueDate(task.getDueDate());

        when(taskMapperCore.entityTaskInResponseTaskDataDto(any(Task.class))).thenReturn(responseDto);

        ResponseTaskDataDto result=this.taskCrudService.getTaskById(idRequest);

        assertEquals(responseDto.getTitleTask(),result.getTitleTask());
        verify(taskRepository,times(1)).findById(any(Long.class));
    }

    @Test
    void getTaskByIdFailerPerIdNotFound(){
        Long idRequest=1L;

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.empty()).thenThrow(new NotFoundException("id not found in database"));

        assertThrows(NotFoundException.class,()-> this.taskCrudService.getTaskById(idRequest));

        verify(taskRepository,times(1)).findById(any(Long.class));
    }

    @Test
    void createTaskAndReturnTaskInDto() {
        User userAccountRequest= new User();
        userAccountRequest.setId(1L);
        userAccountRequest.setFirstName("firstName");
        userAccountRequest.setLastName("lastName");
        userAccountRequest.setEmail("email");
        userAccountRequest.setEnabled(true);
        userAccountRequest.setRole(Role.ROLE_USER);
        userAccountRequest.setUsername("username");
        userAccountRequest.setPassword("passwordEncrypted");

        Project project= new Project();
        project.setUser(userAccountRequest);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setId(1L);
        project.setEndDate(LocalDate.now().plusDays(1L));

        Task taskRequestFromCreated= new Task();
        taskRequestFromCreated.setUser(userAccountRequest);
        taskRequestFromCreated.setProject(project);
        taskRequestFromCreated.setId(1L);
        taskRequestFromCreated.setStatus(Status.TODO);
        taskRequestFromCreated.setPriority(Priority.MEDIUM);
        taskRequestFromCreated.setTitleTask("titleTask");
        taskRequestFromCreated.setDescription("description");
        taskRequestFromCreated.setDueDate(LocalDate.now().plusDays(1L));

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation->{
            return invocation.getArgument(0);
        });

        ResponseTaskDataDto responseDto= new ResponseTaskDataDto();
        responseDto.setStartDate(taskRequestFromCreated.getStartDate());
        responseDto.setId(taskRequestFromCreated.getId());
        responseDto.setDescription(taskRequestFromCreated.getDescription());
        responseDto.setTitleTask(taskRequestFromCreated.getTitleTask());
        responseDto.setStatus(taskRequestFromCreated.getStatus());
        responseDto.setPriority(taskRequestFromCreated.getPriority());
        responseDto.setDueDate(taskRequestFromCreated.getDueDate());

        when(taskMapperCore.entityTaskInResponseTaskDataDto(any(Task.class))).thenReturn(responseDto);

        ResponseTaskDataDto result=this.taskCrudService.createTaskAndReturnTaskInDto(taskRequestFromCreated);

        assertEquals(responseDto.getTitleTask(),result.getTitleTask());
        verify(taskRepository,times(1)).save(any(Task.class));

    }

    @Test
    void updateTaskSuccessPerClientIsCreatorTask() {

        Long idRequest = 1L;

        User userAccountRequest = new User();
        userAccountRequest.setId(1L);
        userAccountRequest.setFirstName("firstName");
        userAccountRequest.setLastName("lastName");
        userAccountRequest.setEmail("email");
        userAccountRequest.setEnabled(true);
        userAccountRequest.setRole(Role.ROLE_USER);
        userAccountRequest.setUsername("username");
        userAccountRequest.setPassword("passwordEncrypted");

        Project project = new Project();
        project.setId(1L);
        project.setUser(userAccountRequest);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setEndDate(LocalDate.now().plusDays(1));

        Task taskCreated = new Task();
        taskCreated.setId(1L);
        taskCreated.setUser(userAccountRequest);
        taskCreated.setProject(project);
        taskCreated.setStatus(Status.TODO);
        taskCreated.setPriority(Priority.MEDIUM);
        taskCreated.setTitleTask("titulo antigo");
        taskCreated.setDescription("descricao antiga");
        taskCreated.setDueDate(LocalDate.now().plusDays(1));

        TaskPutDto taskRequestFromUpdate = new TaskPutDto();
        taskRequestFromUpdate.setStatus(Status.TODO);
        taskRequestFromUpdate.setPriority(Priority.HIGH);
        taskRequestFromUpdate.setTitleTask("titulo atualizado");
        taskRequestFromUpdate.setDescription("descricao atualizada");
        taskRequestFromUpdate.setDueDate("2025-01-01");

        when(taskRepository.findById(idRequest))
                .thenReturn(Optional.of(taskCreated));

        when(getUsernameByContextHolder.execute())
                .thenReturn(userAccountRequest.getUsername());

        when(validationIfUserIsRoleAdmin.userIsAdmin(Role.ROLE_USER))
                .thenReturn(false);

        doNothing().when(userRequestIsCreatorTask)
                .throwIfUserRequestNotCreatorTask(anyString(), anyString());

        doAnswer(invocation -> {
            Task entity = invocation.getArgument(0);
            TaskPutDto dto = invocation.getArgument(1);
            entity.setStatus(dto.getStatus());
            entity.setPriority(dto.getPriority());
            entity.setTitleTask(dto.getTitleTask());
            entity.setDescription(dto.getDescription());
            return null;
        }).when(taskMapperCore).updatePutTaskData(any(Task.class), any(TaskPutDto.class));

        when(parseDataFromIsoAmerican.parseDataFormatBrazilianImAmerican(anyString()))
                .thenReturn(LocalDate.of(2025, 1, 1));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(taskMapperCore.entityTaskInResponseTaskDataDto(any(Task.class)))
                .thenAnswer(invocation -> {
                    Task entity = invocation.getArgument(0);
                    ResponseTaskDataDto dto = new ResponseTaskDataDto();
                    dto.setStatus(entity.getStatus());
                    dto.setPriority(entity.getPriority());
                    dto.setTitleTask(entity.getTitleTask());
                    dto.setDescription(entity.getDescription());
                    dto.setDueDate(entity.getDueDate());
                    return dto;
                });


        ResponseTaskDataDto result =
                taskCrudService.updateTask(idRequest, taskRequestFromUpdate);

        assertEquals("titulo atualizado", result.getTitleTask());
        assertEquals("descricao atualizada", result.getDescription());
        assertEquals(Status.TODO, result.getStatus());
        assertEquals(Priority.HIGH, result.getPriority());

        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskMapperCore, times(1))
                .updatePutTaskData(any(Task.class), any(TaskPutDto.class));
    }

    @Test
    void updateTaskSuccessPerClientIsRoleAdmin(){
        Long idRequest = 1L;

        User userAccountRequest = new User();
        userAccountRequest.setId(1L);
        userAccountRequest.setFirstName("firstName");
        userAccountRequest.setLastName("lastName");
        userAccountRequest.setEmail("email");
        userAccountRequest.setEnabled(true);
        userAccountRequest.setRole(Role.ROLE_USER);
        userAccountRequest.setUsername("username");
        userAccountRequest.setPassword("passwordEncrypted");

        Project project = new Project();
        project.setId(1L);
        project.setUser(userAccountRequest);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setEndDate(LocalDate.now().plusDays(1));

        Task taskCreated = new Task();
        taskCreated.setId(1L);
        taskCreated.setUser(userAccountRequest);
        taskCreated.setProject(project);
        taskCreated.setStatus(Status.TODO);
        taskCreated.setPriority(Priority.MEDIUM);
        taskCreated.setTitleTask("titulo antigo");
        taskCreated.setDescription("descricao antiga");
        taskCreated.setDueDate(LocalDate.now().plusDays(1));

        TaskPutDto taskRequestFromUpdate = new TaskPutDto();
        taskRequestFromUpdate.setStatus(Status.TODO);
        taskRequestFromUpdate.setPriority(Priority.HIGH);
        taskRequestFromUpdate.setTitleTask("titulo atualizado");
        taskRequestFromUpdate.setDescription("descricao atualizada");
        taskRequestFromUpdate.setDueDate("2025-01-01");

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(taskCreated));

        when(getUsernameByContextHolder.execute()).thenReturn(userAccountRequest.getUsername());

        when(validationIfUserIsRoleAdmin.userIsAdmin(any(Role.class))).thenReturn(true);

        doAnswer(invocation -> {
            Task entity = invocation.getArgument(0);
            TaskPutDto dto = invocation.getArgument(1);
            entity.setStatus(dto.getStatus());
            entity.setPriority(dto.getPriority());
            entity.setTitleTask(dto.getTitleTask());
            entity.setDescription(dto.getDescription());
            return null;
        }).when(taskMapperCore).updatePutTaskData(any(Task.class), any(TaskPutDto.class));

        when(parseDataFromIsoAmerican.parseDataFormatBrazilianImAmerican(anyString()))
                .thenReturn(LocalDate.of(2025, 1, 1));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(taskMapperCore.entityTaskInResponseTaskDataDto(any(Task.class)))
                .thenAnswer(invocation -> {
                    Task entity = invocation.getArgument(0);
                    ResponseTaskDataDto dto = new ResponseTaskDataDto();
                    dto.setStatus(entity.getStatus());
                    dto.setPriority(entity.getPriority());
                    dto.setTitleTask(entity.getTitleTask());
                    dto.setDescription(entity.getDescription());
                    dto.setDueDate(entity.getDueDate());
                    return dto;
                });


        ResponseTaskDataDto result =
                taskCrudService.updateTask(idRequest, taskRequestFromUpdate);

        assertEquals("titulo atualizado", result.getTitleTask());
        assertEquals("descricao atualizada", result.getDescription());
        assertEquals(Status.TODO, result.getStatus());
        assertEquals(Priority.HIGH, result.getPriority());

        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskMapperCore, times(1))
                .updatePutTaskData(any(Task.class), any(TaskPutDto.class));
    }

    @Test
    void updateTaskFailedPerIdFromTaskNotFound(){

        Long idRequest=2L;


        TaskPutDto taskRequestFromUpdate = new TaskPutDto();
        taskRequestFromUpdate.setStatus(Status.TODO);
        taskRequestFromUpdate.setPriority(Priority.HIGH);
        taskRequestFromUpdate.setTitleTask("titulo atualizado");
        taskRequestFromUpdate.setDescription("descricao atualizada");
        taskRequestFromUpdate.setDueDate("2025-01-01");


        User userAccountRequest = new User();
        userAccountRequest.setId(1L);
        userAccountRequest.setFirstName("firstName");
        userAccountRequest.setLastName("lastName");
        userAccountRequest.setEmail("email");
        userAccountRequest.setEnabled(true);
        userAccountRequest.setRole(Role.ROLE_USER);
        userAccountRequest.setUsername("username");
        userAccountRequest.setPassword("passwordEncrypted");

        Project project = new Project();
        project.setId(1L);
        project.setUser(userAccountRequest);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setEndDate(LocalDate.now().plusDays(1));

        Task taskCreated = new Task();
        taskCreated.setId(1L);
        taskCreated.setUser(userAccountRequest);
        taskCreated.setProject(project);
        taskCreated.setStatus(Status.TODO);
        taskCreated.setPriority(Priority.MEDIUM);
        taskCreated.setTitleTask("titulo antigo");
        taskCreated.setDescription("descricao antiga");
        taskCreated.setDueDate(LocalDate.now().plusDays(1));

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.empty()).thenThrow(new NotFoundException("id not found in database"));

        assertThrows(NotFoundException.class,()-> this.taskCrudService.updateTask(idRequest,taskRequestFromUpdate));
    }

    @Test
    void updateTaskFailedPerUserNotRoleAdminAndNotCreatorTask(){


        Long idRequest = 1L;

        User userAccountRequest = new User();
        userAccountRequest.setId(1L);
        userAccountRequest.setFirstName("firstName");
        userAccountRequest.setLastName("lastName");
        userAccountRequest.setEmail("email");
        userAccountRequest.setEnabled(true);
        userAccountRequest.setRole(Role.ROLE_USER);
        userAccountRequest.setUsername("username");
        userAccountRequest.setPassword("passwordEncrypted");

        Project project = new Project();
        project.setId(1L);
        project.setUser(userAccountRequest);
        project.setPasswordAccess("passwordAccess");
        project.setDescription("description");
        project.setNameProject("nameProject");
        project.setEndDate(LocalDate.now().plusDays(1));

        Task taskCreated = new Task();
        taskCreated.setId(1L);
        taskCreated.setUser(userAccountRequest);
        taskCreated.setProject(project);
        taskCreated.setStatus(Status.TODO);
        taskCreated.setPriority(Priority.MEDIUM);
        taskCreated.setTitleTask("titulo antigo");
        taskCreated.setDescription("descricao antiga");
        taskCreated.setDueDate(LocalDate.now().plusDays(1));

        TaskPutDto taskRequestFromUpdate = new TaskPutDto();
        taskRequestFromUpdate.setStatus(Status.TODO);
        taskRequestFromUpdate.setPriority(Priority.HIGH);
        taskRequestFromUpdate.setTitleTask("titulo atualizado");
        taskRequestFromUpdate.setDescription("descricao atualizada");
        taskRequestFromUpdate.setDueDate("2025-01-01");

        when(taskRepository.findById(idRequest))
                .thenReturn(Optional.of(taskCreated));

        when(getUsernameByContextHolder.execute())
                .thenReturn(userAccountRequest.getUsername());

        when(validationIfUserIsRoleAdmin.userIsAdmin(Role.ROLE_USER))
                .thenReturn(false);

        doThrow(new ForbiddenException("You cannot update this task because you are neither an administrator nor the creator of the task."))
                .when(userRequestIsCreatorTask)
                .throwIfUserRequestNotCreatorTask(any(String.class),any(String.class));

        assertThrows(ForbiddenException.class,()-> this.taskCrudService.updateTask(idRequest,taskRequestFromUpdate));
    }


    @Test
    void deleteTaskSuccess(){
        Long idTaskRequestFromDeleteTask=1L;

        doNothing().when(taskRepository).deleteById(any(Long.class));

        this.taskCrudService.deleteTask(idTaskRequestFromDeleteTask);
        verify(taskRepository,times(1)).deleteById(any(Long.class));
    }

    @Test
    void deleteTaskFailerPerIdTaskNotFound(){
        Long idRequestFromDeleteTask=1L;

        doThrow(new NotFoundException("id not found in database"))
                .when(taskRepository)
                .deleteById(any(Long.class));

        assertThrows(NotFoundException.class,()-> this.taskCrudService.deleteTask(idRequestFromDeleteTask));
    }
}