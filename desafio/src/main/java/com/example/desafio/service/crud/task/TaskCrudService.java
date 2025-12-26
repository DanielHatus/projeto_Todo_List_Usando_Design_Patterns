package com.example.desafio.service.crud.task;

import com.example.desafio.dto.request.crud.task.put.TaskPutDto;
import com.example.desafio.dto.response.crud.task.ResponseTaskDataDto;
import com.example.desafio.enums.Priority;
import com.example.desafio.enums.Status;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.task.TaskMapperCore;
import com.example.desafio.model.task.Task;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.repository.task.TaskRepository;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.parse.data.from.iso.american.ParseDataFromIsoAmerican;
import com.example.desafio.utils.validation.user.is.creator.task.UserRequestIsCreatorTask;
import com.example.desafio.utils.validation.user.is.role.admin.ValidationIfUserIsRoleAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskCrudService{

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapperCore taskMapperCore;
    private final UserRequestIsCreatorTask userRequestIsCreatorTask;
    private final GetUsernameByContextHolder getUsernameByContextHolder;
    private final ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin;
    private final ParseDataFromIsoAmerican parseDataFromIsoAmerican;

    public TaskCrudService(
            TaskRepository taskRepository,
            TaskMapperCore taskMapperCore,
            ProjectRepository projectRepository,
            UserRequestIsCreatorTask userRequestIsCreatorTask,
            GetUsernameByContextHolder getUsernameByContextHolder,
            ValidationIfUserIsRoleAdmin validationIfUserIsRoleAdmin,
            ParseDataFromIsoAmerican parseDataFromIsoAmerican){

        this.taskRepository = taskRepository;
        this.taskMapperCore = taskMapperCore;
        this.projectRepository=projectRepository;
        this.userRequestIsCreatorTask=userRequestIsCreatorTask;
        this.getUsernameByContextHolder=getUsernameByContextHolder;
        this.validationIfUserIsRoleAdmin=validationIfUserIsRoleAdmin;
        this.parseDataFromIsoAmerican=parseDataFromIsoAmerican;

    }


    public List<ResponseTaskDataDto> getTasksByOrder(Status status, Priority priority, Long idProject){

        if (!projectRepository.existsById(idProject)){
            log.error("❌ The ID {} of the project passed in the filter does not exist. Returning a NotFoundException.",idProject);
            throw new NotFoundException("idProject not found in database");
        }
        log.debug("✅ The ID {} passed in one of the filters was successfully validated. The flow will now receive all tasks according to the" +
                " passed filters.",idProject);

        log.debug("✅ Returning the list of task entities according to the filters passed in the request now.");
        return taskMapperCore.listEntityTasksInResponseTaskDataDto(taskRepository.findByStatusAndPriorityAndProjectId(status, priority, idProject));
    }

    public ResponseTaskDataDto getTaskById(Long id){
        return taskMapperCore.entityTaskInResponseTaskDataDto(getEntityTaskOrThrow(id));
    }

    public ResponseTaskDataDto createTaskAndReturnTaskInDto(Task entity){
        log.debug("✅ The entire creation and validation process was successful. " +
                "Saving the entity and returning a DTO with the saved data.");
       return taskMapperCore.entityTaskInResponseTaskDataDto(taskRepository.save(entity));
    }

    public ResponseTaskDataDto updateTask(Long id,TaskPutDto taskPutDto){

     Task entityTask=getEntityTaskOrThrow(id);
     log.debug("✅ The entity task with id {} was successfully found.",id);

     String userUsernameRequest=getUsernameByContextHolder.execute();

     if (!validationIfUserIsRoleAdmin.userIsAdmin(entityTask.getUser().getRole())){
         userRequestIsCreatorTask.throwIfUserRequestNotCreatorTask(userUsernameRequest,entityTask.getUser().getUsername());
     }
     log.debug("✅ The user was successfully validated. They have permission to successfully update the task data.");

     taskMapperCore.updatePutTaskData(entityTask,taskPutDto);
     log.debug("✅ The request data was successfully copied to the entity.");

     entityTask.setDueDate(parseDataFromIsoAmerican.parseDataFormatBrazilianImAmerican(taskPutDto.getDueDate()));

     log.debug("✅ All validations and processing were successful. " +
             "Saving the entity with the new data and returning the DTO with the new saved data.");
     return taskMapperCore.entityTaskInResponseTaskDataDto(taskRepository.save(entityTask));
    }

    public void deleteTask(Long id){
        try{
            taskRepository.deleteById(id);
            log.debug("✅ task successfully deleted.");
        }
        catch (EmptyResultDataAccessException e){
            log.error("❌ No task registered with ID {} was found on the server.",id);
            throw new NotFoundException("id not found in database");
        }
    }

    private Task getEntityTaskOrThrow(Long id){
        Optional<Task> possibleTask=taskRepository.findById(id);
        if (possibleTask.isPresent()){
            log.debug("The task entity with ID {} was successfully found.",id);
            return possibleTask.get();
        }
        log.error("❌ No task entity with ID {} was found.",id);
        throw new NotFoundException("id not found in database");
    }
}
