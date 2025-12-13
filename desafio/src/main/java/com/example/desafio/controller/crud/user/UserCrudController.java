package com.example.desafio.controller.crud.user;

import com.example.desafio.docs.crud.user.UserCrudDoc;
import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.complete.UserPutDtoDataComplete;
import com.example.desafio.dto.request.crud.user.put.simple.UserPutDtoDataSimple;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import com.example.desafio.service.crud.user.UserCrudService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/crud/user")
public class UserCrudController implements UserCrudDoc {
private final UserCrudService service;


    public UserCrudController(UserCrudService service) {
        this.service = service;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<ResponseUserDataDto>> getUsersByOrder(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false)  Integer size,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String direction){

        return ResponseEntity.ok(service.getUserByPageOrder(page,size,order,direction));
    }

    @Override
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseUserDataDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @Override
    @PutMapping(value = "/{id}",consumes =MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Void> updateUserDataSimple(@PathVariable("id") Long id, @RequestBody @Valid UserPutDtoDataSimple userPutDtoDataSimple) {
       this.service.updateUserDataSimple(id,userPutDtoDataSimple);
       return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/complete/{id}",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUserDataDto> updateUserDataComplete(@PathVariable("id") Long id, @Valid @RequestBody UserPutDtoDataComplete userPutDtoDataComplete){
        return ResponseEntity.ok(this.service.updateUserDataComplete(id,userPutDtoDataComplete));
    }

    @Override
    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseUserDataDto> updateUserDataPatch(@PathVariable("id") Long id, @RequestBody @Valid UserPatchDto userPatchDto) {
        return ResponseEntity.ok(service.updateUserPatch(id,userPatchDto));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleUserById(@PathVariable("id") Long id){
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
