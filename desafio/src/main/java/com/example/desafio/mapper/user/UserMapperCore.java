package com.example.desafio.mapper.user;

import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.complete.UserPutDtoDataComplete;
import com.example.desafio.dto.request.crud.user.put.simple.UserPutDtoDataSimple;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import com.example.desafio.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapperCore{

    User toEntity(UserRegisterDto registerDto);

    ResponseUserDataDto toResponseUserDataDto(User user);

    void updateUserPutSimple(@MappingTarget User entity, UserPutDtoDataSimple userPutDtoDataSimple);

    void updateUserPutComplete(@MappingTarget User entity, UserPutDtoDataComplete userPutDtoDataComplete);

    void updateUserPatch(@MappingTarget User entity, UserPatchDto userPatchDto);

    default Page<ResponseUserDataDto> toPageResponseUserDataDto(Page<User> users){
        Page<ResponseUserDataDto> pageDto=users.map(user->{
         ResponseUserDataDto dto=toResponseUserDataDto(user);
         return dto;
        });
        return pageDto;
    }
}
