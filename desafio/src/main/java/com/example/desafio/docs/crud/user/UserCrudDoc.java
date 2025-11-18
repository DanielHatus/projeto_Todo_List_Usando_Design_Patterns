package com.example.desafio.docs.crud.user;

import com.example.desafio.dto.request.crud.user.patch.UserPatchDto;
import com.example.desafio.dto.request.crud.user.put.UserPutDto;
import com.example.desafio.dto.response.crud.user.ResponseUserDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name="Crud User Controller",
description = "This controller is responsible for executing all CRUD operations on the User entity " +
        "(except POST, since there's no need for authentication to register and log in), provided authentication is present.")
public interface UserCrudDoc {

    @Operation(summary = "It is responsible for retrieving users through pagination based on the client's specific needs.",
    description = "The client will pass the size, page, order, and et as request parameters, " +
            "and when the response DTO is returned, the paginated list will be in accordance with this " +
            "specification passed by the client in the request parameters.",
    responses = {
        @ApiResponse(responseCode = "200")}
    )
    public ResponseEntity<Page<ResponseUserDataDto>> getUsersByOrder(Integer page, Integer size, String order, String direction);


    @Operation(summary = "The client passes the ID as a parameter and receives the entity corresponding to that ID, if it exists.",
     description = "The client will pass the user ID in the request body. If an ID exists in the database, " +
             "a DTO in the response body will return the user's data, except for the password, " +
             "which is extremely sensitive data and should not be exposed.",
     responses = {
            @ApiResponse(responseCode = "200",description = "Registered user data except for the password."),
             @ApiResponse(responseCode = "404",description = "The user with the passed ID was not found on the server.")
     })
    public ResponseEntity<ResponseUserDataDto> getUserById(Long id);


    @Operation(summary = "The user will enter all their data: first name, last name, username, and email. " +
            "If this data is correct (provided there are no null or empty values, and it is within the character limits), " +
            "it will replace the user's current data.",
    description = "The user will pass all the user's data: first name, last name, username, and email. " +
            "If this data is correct (if there are no null or empty values, and it is within the character delimiter), " +
            "it will replace the user's current data. A reminder is that the user must pass the ID of the database " +
            "containing the data they want to replace as a path variable. If the passed ID does not exist, " +
            "a 404 not found exception will be returned.",
    responses = {
            @ApiResponse(responseCode = "200",description = "All user data, including data updated by the user, except for the password."),
            @ApiResponse(responseCode = "404",description = "The user with the passed ID was not found on the server."),
            @ApiResponse(responseCode = "400",description = "The data (or representation of the data passed incorrectly in the request) " +
                    "was passed incorrectly in the request.")


    })
    public ResponseEntity<ResponseUserDataDto> updateUserCompletedPut(Long id, UserPutDto userPutDto);



    @Operation(summary = "The user will provide the data they want to update, keeping in mind that the patch only provides specific data needed to perform the update.",
    responses = {
            @ApiResponse(responseCode = "200",description = "Entity data, excluding password, including updated user data, " +
                    "is displayed in the response body."),
            @ApiResponse(responseCode = "404",description = "The user with the passed ID was not found on the server."),
            @ApiResponse(responseCode = "400",description = "The data (or representation of the data passed incorrectly in the request)")
    })
    public ResponseEntity<ResponseUserDataDto> updateUserPartial(Long id, UserPatchDto userPatchDto);



    @Operation(summary = "The client will pass the ID of the user they want to delete from the database as a @pathvariable.",
    responses = {
            @ApiResponse(responseCode = "204",description = "response body empty"),
            @ApiResponse(responseCode = "404",description = "The user with the passed ID was not found on the server."),
    })
    public ResponseEntity<Void> deleUserById(Long id);
}
