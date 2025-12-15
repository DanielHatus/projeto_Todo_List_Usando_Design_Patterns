package com.example.desafio.service.crud.user;

import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.complete.UserPutDtoDataComplete;
import com.example.desafio.dto.request.crud.user.put.simple.UserPutDtoDataSimple;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.user.UserMapperCore;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import com.example.desafio.utils.pageable.factory.PageableFactoryByClassReceived;
import com.example.desafio.utils.validation.user.put.them.selves.ValidationIfUserEffectPutThemSelves;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class UserCrudService{
    private final UserRepository repository;
    private  final UserMapperCore mapperCore;
    private final EncryptedPassword encryptedPassword;
    private final PageableFactoryByClassReceived pageableFactoryByClassReceived;
    private final ValidationIfUserEffectPutThemSelves validationIfUserEffectPutThemSelves;
    private final GetUsernameByContextHolder getUsernameByContextHolder;


    public UserCrudService(
            UserRepository repository,
            UserMapperCore mapperCore,
            EncryptedPassword encryptedPassword,
            PageableFactoryByClassReceived pageableFactoryByClassReceived,
            ValidationIfUserEffectPutThemSelves validationIfUserEffectPutThemSelves,
            GetUsernameByContextHolder getUsernameByContextHolder) {

            this.repository = repository;
            this.mapperCore = mapperCore;
            this.encryptedPassword = encryptedPassword;
            this.pageableFactoryByClassReceived=pageableFactoryByClassReceived;
            this.validationIfUserEffectPutThemSelves=validationIfUserEffectPutThemSelves;
            this.getUsernameByContextHolder=getUsernameByContextHolder;
    }

    public Page<ResponseUserDataDto> getUserByPageOrder(Integer page,Integer size,String order,String direction){

        Pageable pageable=pageableFactoryByClassReceived.pageableFactory(User.class,page,size,order,direction);

        log.debug("✅ The users were successfully captured according to the request pagination.");
        return mapperCore.toPageResponseUserDataDto(repository.findAll(pageable));
    }

    public ResponseUserDataDto getUserById(Long id){
        User entity=getEntityByIdOrThrow(id);
        return mapperCore.toResponseUserDataDto(entity);
    }

    @Transactional
    public void updateUserDataSimple(Long id, UserPutDtoDataSimple userPutDtoDataSimple){
      User entity=getEntityByIdOrThrow(id);

      String userUsernameAccount=getUsernameByContextHolder.execute();

      validationIfUserEffectPutThemSelves.throwIfUserRequestIsDifferentEntityUser(userUsernameAccount,entity.getUsername());

      mapperCore.updateUserPutSimple(entity, userPutDtoDataSimple);
      log.debug("✅ The data from the PUT simple request was successfully inserted into the entity.");

      log.debug("✅ The updated user data was saved successfully in server.");
      repository.save(entity);
    }

    @Transactional
    public ResponseUserDataDto updateUserDataComplete(Long id, UserPutDtoDataComplete userPutDtoDataComplete){
        User entity=getEntityByIdOrThrow(id);

        mapperCore.updateUserPutComplete(entity, userPutDtoDataComplete);
        log.debug("✅ The data from the PUT complete request was successfully inserted into the entity.");


        log.debug("✅ The data passed in the request was successfully updated in the entity, returning the response data.");
        return mapperCore.toResponseUserDataDto(repository.save(entity));
    }

    @Transactional
    public ResponseUserDataDto updateUserPatch(Long id, UserPatchDto userPatchDto){
        User entity=getEntityByIdOrThrow(id);

        mapperCore.updateUserPatch(entity,userPatchDto);
        log.debug("✅ The data from the PATCH request was successfully inserted into the entity.");


        log.debug("✅ The updated user was saved on the server and returned as a successful Dto.");
        return mapperCore.toResponseUserDataDto(repository.save(entity));
    }

    public void deleteUserById(Long id){
        try{
        repository.deleteById(id);
        log.debug("✅ User successfully deleted.");
        }
        catch (EmptyResultDataAccessException e){
            log.error("❌ No user registered with ID {} was found on the server.",id);
            throw new NotFoundException("id not found in database");
        }
    }

    private User getEntityByIdOrThrow(Long id){
        User entity=repository.findById(id)
                .orElseThrow(()-> {
                    log.error("❌ No user registered with ID {} was found on the server.",id);
                    return new NotFoundException("id not found in database");
                });
        log.debug("✅ User {} was found on the server with ID {}.",entity.getUsername(),id);
        return entity;
    }
}
