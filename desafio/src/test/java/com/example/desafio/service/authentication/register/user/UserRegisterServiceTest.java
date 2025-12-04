package com.example.desafio.service.authentication.register.user;

import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.mapper.user.UserMapperCore;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRegisterServiceTest {
  @Mock
  private UserRepository repository;

  @Mock
  private UserMapperCore mapperCore;

  @Mock
  private EncryptedPassword encryptedPassword;


  private UserRegisterService userRegisterService;

  @BeforeEach
  void setUp(){
      MockitoAnnotations.openMocks(this);
      this.userRegisterService=new UserRegisterService(repository,mapperCore,encryptedPassword);
  }



    @Test
    void registerUserSuccess(){
        UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setEmail("teste@gmail.com");
        userRegisterDto.setPassword("password123");
        userRegisterDto.setFirstName("teste");
        userRegisterDto.setLastName("teste");
        userRegisterDto.setUsername("teste");


        User user=new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setUsername(userRegisterDto.getUsername());

      when(repository.existsByEmail(userRegisterDto.getEmail())).thenReturn(false);
      when(repository.existsByUsername(userRegisterDto.getUsername())).thenReturn(false);
      when(mapperCore.toEntity(userRegisterDto)).thenReturn(user);
      when(encryptedPassword.encrypted(user.getPassword())).thenReturn("password123Decoded");

      User result=userRegisterService.registerUser(userRegisterDto);

      assertNotNull(result);
      assertEquals(result.getPassword(),"password123Decoded");
      verify(repository,times(1)).save(user);

    }

    @Test
    void registerUserFailedPerEmailExistsInDb(){
        UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setEmail("teste@gmail.com");
        userRegisterDto.setPassword("password123");
        userRegisterDto.setFirstName("teste");
        userRegisterDto.setLastName("teste");
        userRegisterDto.setUsername("teste");


        when(repository.existsByEmail(userRegisterDto.getEmail())).thenReturn(true)
                .thenThrow(new BadRequestException("email exists in database"));

        assertThrows(BadRequestException.class,()-> userRegisterService.registerUser(userRegisterDto));
        verify(repository,times(1)).existsByEmail(userRegisterDto.getEmail());

    }

    @Test
    void registerUserFailedPerUsernameExistsInDb(){
        UserRegisterDto userRegisterDto=new UserRegisterDto();
        userRegisterDto.setEmail("teste@gmail.com");
        userRegisterDto.setPassword("password123");
        userRegisterDto.setFirstName("teste");
        userRegisterDto.setLastName("teste");
        userRegisterDto.setUsername("teste");

        when(repository.existsByUsername(userRegisterDto.getUsername())).thenReturn(true)
                .thenThrow(new BadRequestException("username exists in database"));

        assertThrows(BadRequestException.class,()-> userRegisterService.registerUser(userRegisterDto));
        verify(repository,times(1)).existsByUsername(userRegisterDto.getUsername());
    }
}