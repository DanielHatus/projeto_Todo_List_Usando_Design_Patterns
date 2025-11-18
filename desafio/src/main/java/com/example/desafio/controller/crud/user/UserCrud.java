package com.example.desafio.controller.crud.user;

import com.example.desafio.docs.crud.user.UserCrudDoc;
import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.UserPutDto;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import com.example.desafio.service.crud.user.UserCrudService;
import com.example.desafio.utils.pageable.GeneratePageableByParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/crud")
public class UserCrud implements UserCrudDoc {
private final UserCrudService service;
private final GeneratePageableByParams pageableByParams;


    public UserCrud(UserCrudService service, GeneratePageableByParams pageableByParams) {
        this.service = service;
        this.pageableByParams = pageableByParams;
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ResponseUserDataDto>> getUsersByOrder(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false)  Integer size,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String direction){

        Pageable pageable=pageableByParams.generate(page,size,order,direction);
        return ResponseEntity.ok(service.getUserByPageOrder(pageable));
    }

    @Override
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseUserDataDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @Override
    @PutMapping(value = "/{id}",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseUserDataDto> updateUserCompletedPut(@PathVariable("id") Long id, @RequestBody UserPutDto userPutDto) {
        return ResponseEntity.ok(service.updateUserPut(id,userPutDto));
    }

    @Override
    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseUserDataDto> updateUserPartial(@PathVariable("id") Long id, @RequestBody UserPatchDto userPatchDto) {
        return ResponseEntity.ok(service.updateUserPatch(id,userPatchDto));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleUserById(@PathVariable("id") Long id){
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
