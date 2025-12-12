package com.example.desafio.service.crud.user;

import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.complete.UserPutDtoDataComplete;
import com.example.desafio.dto.request.crud.user.put.simple.UserPutDtoDataSimple;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import com.example.desafio.enums.Role;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.mapper.user.UserMapperCore;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.service.crud.user.validation.put.ValidationIfUserEffectPutThemSelves;
import com.example.desafio.utils.pageable.factory.PageableFactoryByClassReceived;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCrudServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapperCore mapperCore;

    @Mock
    private PageableFactoryByClassReceived pageableFactoryByClassReceived;

    @Mock
    ValidationIfUserEffectPutThemSelves validationIfUserEffectPutThemSelves;

    @InjectMocks
    private UserCrudService userCrudService;

     @BeforeEach
     void startUp(){
         MockitoAnnotations.openMocks(this);
     }

     @Test
     void getUserByPageOrderSuccess(){

         Integer page=0;
         Integer size=1;
         String order="id";
         String direction="asc";

        User user1=new User();
        user1.setId(1L);
        user1.setEmail("teste@gmail.com");
        user1.setPassword("testeDecoded");
        user1.setFirstName("teste");
        user1.setLastName("teste");
        user1.setUsername("teste");

        User user2=new User();
        user2.setId(1L);
        user2.setEmail("teste@gmail.com");
        user2.setPassword("testeDecoded");
        user2.setFirstName("teste");
        user2.setLastName("teste");
        user2.setUsername("teste");


        Pageable pageable=PageRequest.of(page,size, Sort.by(order,direction));
        List<User> users=List.of(user1,user2);
        Page<User> pageUsers=new PageImpl<>(users,pageable,users.size());

        when(repository.findAll(pageable)).thenReturn(pageUsers);

        ResponseUserDataDto responseUserDataDto1=new ResponseUserDataDto();
        responseUserDataDto1.setEmail(user1.getEmail());
        responseUserDataDto1.setEnabled(user1.getEnabled());
        responseUserDataDto1.setRole(user1.getRole());
        responseUserDataDto1.setUsername(user1.getUsername());
        responseUserDataDto1.setFirstName(user1.getFirstName());
        responseUserDataDto1.setLastName(user1.getLastName());
        responseUserDataDto1.setDateRegister(user1.getDateRegister());
        responseUserDataDto1.setLastUpdate(user1.getLastUpdate());


        ResponseUserDataDto responseUserDataDto2=new ResponseUserDataDto();
        responseUserDataDto2.setEmail(user2.getEmail());
        responseUserDataDto2.setEnabled(user2.getEnabled());
        responseUserDataDto2.setRole(user2.getRole());
        responseUserDataDto2.setUsername(user2.getUsername());
        responseUserDataDto2.setFirstName(user2.getFirstName());
        responseUserDataDto2.setLastName(user2.getLastName());
        responseUserDataDto2.setDateRegister(user2.getDateRegister());
        responseUserDataDto2.setLastUpdate(user2.getLastUpdate());

        List<ResponseUserDataDto> usersDto=List.of(responseUserDataDto1,responseUserDataDto2);
        Page<ResponseUserDataDto> pageDto=new PageImpl<>(usersDto,pageable,usersDto.size());

        when(pageableFactoryByClassReceived.pageableFactory(User.class,page,size,order,direction)).thenReturn(pageable);
        when(mapperCore.toPageResponseUserDataDto(pageUsers)).thenReturn(pageDto);

        Page<ResponseUserDataDto> result=this.userCrudService.getUserByPageOrder(page,size,order,direction);
        assertEquals(pageDto.getSize(),result.getSize());
        verify(repository,times(1)).findAll(pageable);
    }

     @Test
     void getUserByIdSuccess(){

        User user=new User();
        user.setId(1L);
        user.setEmail("teste@gmail.com");
        user.setPassword("testeDecoded");
        user.setFirstName("teste");
        user.setLastName("teste");
        user.setUsername("teste");

        ResponseUserDataDto responseUserDataDto=new ResponseUserDataDto();
        responseUserDataDto.setEmail(user.getEmail());
        responseUserDataDto.setEnabled(user.getEnabled());
        responseUserDataDto.setRole(user.getRole());
        responseUserDataDto.setUsername(user.getUsername());
        responseUserDataDto.setFirstName(user.getFirstName());
        responseUserDataDto.setLastName(user.getLastName());
        responseUserDataDto.setDateRegister(user.getDateRegister());
        responseUserDataDto.setLastUpdate(user.getLastUpdate());

        Long idRequest=1L;

        when(repository.findById(idRequest)).thenReturn(Optional.of(user));
        when(mapperCore.toResponseUserDataDto(user)).thenReturn(responseUserDataDto);

        ResponseUserDataDto result=this.userCrudService.getUserById(idRequest);

        assertEquals(result.getEmail(),responseUserDataDto.getEmail());
        assertEquals(result.getRole(),responseUserDataDto.getRole());
        verify(repository,times(1)).findById(idRequest);
    }

     @Test
     void getUserByIdFailedPerIdNotFoundInDb(){
        User user=new User();
        user.setId(1L);
        user.setEmail("teste@gmail.com");
        user.setPassword("testeDecoded");
        user.setFirstName("teste");
        user.setLastName("teste");
        user.setUsername("teste");

        Long idRequest=2L;

        when(repository.findById(idRequest)).thenReturn(Optional.empty())
                .thenThrow(new NotFoundException("id not found in database"));
        assertThrows(NotFoundException.class,()-> this.userCrudService.getUserById(idRequest));
        verify(repository,times(1)).findById(idRequest);
    }

     @Test
     void updateUserPutDataSimpleSuccess() {
        Long idRequest = 1L;

        UserPutDtoDataSimple userPutDtoDataSimple = new UserPutDtoDataSimple();

        userPutDtoDataSimple.setUsername("newUsername");
        userPutDtoDataSimple.setFirstName("newFirstName");
        userPutDtoDataSimple.setLastName("newLastName");

        User entityOrigin = new User();
        entityOrigin.setId(1L);
        entityOrigin.setEmail("teste@gmail.com");
        entityOrigin.setPassword("testeDecoded");
        entityOrigin.setFirstName("teste");
        entityOrigin.setLastName("teste");
        entityOrigin.setUsername("teste");

        when(repository.findById(idRequest)).thenReturn(Optional.of(entityOrigin));

        doNothing().when(validationIfUserEffectPutThemSelves).validate(any(String.class));

        doAnswer(invocation -> {
            User entityArgument = invocation.getArgument(0);
            UserPutDtoDataSimple dtoArgument = invocation.getArgument(1);

            entityArgument.setUsername(dtoArgument.getUsername());
            entityArgument.setFirstName(dtoArgument.getFirstName());
            entityArgument.setLastName(dtoArgument.getLastName());

            return null;
        }).when(mapperCore).updateUserPutSimple(any(User.class), any(UserPutDtoDataSimple.class));

        this.userCrudService.updateUserDataSimple(idRequest,userPutDtoDataSimple);

        verify(repository,times(1)).save(any(User.class));
    }

     @Test
     void updateUserPutDataSimpleFailedPerIdNotFoundInDb(){
        Long idRequest=1L;

        UserPutDtoDataSimple userPutDtoDataSimple = new UserPutDtoDataSimple();

        userPutDtoDataSimple.setUsername("newUsername");
        userPutDtoDataSimple.setFirstName("newFirstName");
        userPutDtoDataSimple.setLastName("newLastName");

        User entityOrigin = new User();
        entityOrigin.setId(2L);
        entityOrigin.setEmail("teste@gmail.com");
        entityOrigin.setPassword("testeDecoded");
        entityOrigin.setFirstName("teste");
        entityOrigin.setLastName("teste");
        entityOrigin.setUsername("teste");

        when(repository.findById(idRequest)).thenThrow(new NotFoundException("id not found in database"));

        assertThrows(NotFoundException.class,()-> this.userCrudService.updateUserDataSimple(idRequest, userPutDtoDataSimple));
        verify(repository,times(1)).findById(any(Long.class));

    }

     @Test
     void updateUserPutDataCompleteSuccess(){
         Long idRequest=1L;

         UserPutDtoDataComplete dtoDataComplete=new UserPutDtoDataComplete();
         dtoDataComplete.setFirstName("firstName");
         dtoDataComplete.setLastName("lastName");
         dtoDataComplete.setUsername("username");
         dtoDataComplete.setRole(Role.ROLE_USER);
         dtoDataComplete.setEnabled(true);

         User entity=new User();
         entity.setId(1L);
         entity.setEmail("teste@gmail.com");
         entity.setPassword("testeDecoded");
         entity.setFirstName("teste");
         entity.setLastName("teste");
         entity.setUsername("teste");

         when(repository.findById(idRequest)).thenReturn(Optional.of(entity));

         doAnswer(invocation->{
             User entityOrigin=invocation.getArgument(0);
             UserPutDtoDataComplete dto=invocation.getArgument(1);
             entityOrigin.setUsername(dto.getUsername());
             entityOrigin.setFirstName(dto.getFirstName());
             entityOrigin.setLastName(dto.getLastName());
             entityOrigin.setRole(dto.getRole());
             entityOrigin.setEnabled(dto.getEnabled());
             return null;
         }).when(mapperCore).updateUserPutComplete(any(User.class),any(UserPutDtoDataComplete.class));


        doAnswer(invocation->{
            User entityPassed=invocation.getArgument(0);
            return entityPassed;
        }).when(repository).save(any(User.class));

        doAnswer(invocation->{
            User entityParam=invocation.getArgument(0);
            ResponseUserDataDto responseUserDataDto=new ResponseUserDataDto();
            responseUserDataDto.setFirstName(entityParam.getFirstName());
            responseUserDataDto.setLastName(entityParam.getLastName());
            responseUserDataDto.setUsername(entityParam.getUsername());
            responseUserDataDto.setEmail(entityParam.getEmail());
            responseUserDataDto.setRole(entityParam.getRole());
            responseUserDataDto.setDateRegister(entityParam.getDateRegister());
            responseUserDataDto.setEnabled(entityParam.getEnabled());
            responseUserDataDto.setLastUpdate(entityParam.getLastUpdate());
            return responseUserDataDto;
        }).when(mapperCore).toResponseUserDataDto(any(User.class));

        ResponseUserDataDto result=this.userCrudService.updateUserDataComplete(idRequest,dtoDataComplete);
        verify(repository,times(1)).save(any(User.class));
        assertEquals(dtoDataComplete.getUsername(),result.getUsername());
        assertEquals(dtoDataComplete.getRole(),result.getRole());
     }

     @Test
     void updateUserPatchSuccess() {

        Long idRequest=1L;

        UserPatchDto userPatchDto=new UserPatchDto();
        userPatchDto.setUsername("testeNewUsername");

        User entityOrigin = new User();
        entityOrigin.setId(1L);
        entityOrigin.setEmail("teste@gmail.com");
        entityOrigin.setPassword("testeDecoded");
        entityOrigin.setFirstName("teste");
        entityOrigin.setLastName("teste");
        entityOrigin.setUsername("teste");

        when(repository.findById(idRequest)).thenReturn(Optional.of(entityOrigin));

        doAnswer(invocation->{
           User entityInvocated=invocation.getArgument(0);
           UserPatchDto dtoInvocated=invocation.getArgument(1);
           entityInvocated.setUsername(dtoInvocated.getUsername());
           return null;
        }).when(mapperCore).updateUserPatch(any(User.class),any(UserPatchDto.class));

        when(repository.save(any(User.class))).thenReturn(entityOrigin);

        when(mapperCore.toResponseUserDataDto(any(User.class))).thenAnswer(invocation->{
            User entityInvocated=invocation.getArgument(0);
            ResponseUserDataDto dto = new ResponseUserDataDto();
            dto.setEmail(entityInvocated.getEmail());
            dto.setFirstName(entityInvocated.getFirstName());
            dto.setLastName(entityInvocated.getLastName());
            dto.setRole(entityInvocated.getRole());
            dto.setUsername(entityInvocated.getUsername());
            dto.setEnabled(entityInvocated.getEnabled());
            dto.setDateRegister(entityInvocated.getDateRegister());
            dto.setLastUpdate(entityInvocated.getLastUpdate());

            return dto;
        });

        ResponseUserDataDto result=this.userCrudService.updateUserPatch(idRequest,userPatchDto);
        assertEquals(userPatchDto.getUsername(),result.getUsername());
        verify(repository,times(1)).findById(idRequest);
    }

     @Test
     void updatePatchFailedPerIdNotFoundInDb(){
        Long idRequest=2L;

        UserPatchDto userPatchDto=new UserPatchDto();
        userPatchDto.setUsername("testeNewUsername");

        User entityOrigin = new User();
        entityOrigin.setId(1L);
        entityOrigin.setEmail("teste@gmail.com");
        entityOrigin.setPassword("testeDecoded");
        entityOrigin.setFirstName("teste");
        entityOrigin.setLastName("teste");
        entityOrigin.setUsername("teste");

        when(repository.findById(idRequest)).thenThrow(new NotFoundException("id not found in database"));
        assertThrows(NotFoundException.class,()-> this.userCrudService.updateUserPatch(idRequest,userPatchDto));
        verify(repository,times(1)).findById(idRequest);

    }

     @Test
     void deleteUserByIdSuccess(){
       Long idRequest=1L;
       doNothing().when(repository).deleteById(idRequest);
       this.userCrudService.deleteUserById(idRequest);
       verify(repository,times(1)).deleteById(idRequest);
     }

     @Test
     void deleteUserByIdFailedPerIdNotFoundInDb(){
      Long idRequest=2L;
      doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(idRequest);
      assertThrows(NotFoundException.class,()-> this.userCrudService.deleteUserById(idRequest));
      verify(repository,times(1)).deleteById(idRequest);

    }
}