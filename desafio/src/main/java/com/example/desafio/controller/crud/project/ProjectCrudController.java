package com.example.desafio.controller.crud.project;

import com.example.desafio.docs.crud.project.ProjectCrudDoc;
import com.example.desafio.dto.request.crud.project.post.ProjectPostDto;
import com.example.desafio.dto.request.crud.project.put.ProjectPutDto;
import com.example.desafio.dto.response.crud.project.ResponseProjectDataDto;
import com.example.desafio.facade.register.project.RegisterNewProjectFacade;
import com.example.desafio.service.crud.project.ProjectCrudService;
import com.example.desafio.utils.generate.uri.GenerateUri;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crud/project")
public class ProjectCrudController implements ProjectCrudDoc{

    private final RegisterNewProjectFacade registerNewProjectFacade;
    private final ProjectCrudService projectCrudService;
    private final GenerateUri generateUri;

    public ProjectCrudController(
        RegisterNewProjectFacade registerNewProjectFacade,
        ProjectCrudService projectCrudService,
        GenerateUri generateUri) {

        this.registerNewProjectFacade = registerNewProjectFacade;
        this.projectCrudService = projectCrudService;
        this.generateUri = generateUri;
    }

    @Override

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ResponseProjectDataDto>> getProjectByOrder(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String order,
            @RequestParam String direction) {
        return ResponseEntity.ok(projectCrudService.getProjectByPageOrder(page,size,order,direction));
    }

    @Override
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProjectDataDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectCrudService.getById(id));
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProjectDataDto> createNewProject(@RequestBody @Valid ProjectPostDto projectPostDto) {
        ResponseProjectDataDto entityDto=registerNewProjectFacade.execute(projectPostDto);
        return ResponseEntity.created(generateUri.build(entityDto.getId())).body(entityDto);
    }

    @Override
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProjectDataDto> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectPutDto projectPutDto) {
        return ResponseEntity.ok(projectCrudService.updateProjectPut(id,projectPutDto));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id){
        projectCrudService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
