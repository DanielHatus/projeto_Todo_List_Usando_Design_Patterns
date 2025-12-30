
<h1 style="text-align:center">1-Arquitetura Do Software</h1>
<div style="
  background-color: #0d1117;
  color: #c9d1d9;
  padding: 20px;
  border-radius: 8px;
  overflow-x: auto;
  font-family: 'Fira Code', 'JetBrains Mono', monospace;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #30363d;
">
<h2>Esta é a Arvore Arquitetural do Software, uma Lembrete que a nomeclatura das pastas não importam e nem fazem uma diferença real. o que importa é a forma de organizar o software e principalmente como o fluxo das informações trafegam entre as camadas.</h2>
<pre>
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---example
|   |   |           \---desafio
|   |   |               |   DesafioApplication.java
|   |   |               |
|   |   |               +---cfg
|   |   |               |   \---admin
|   |   |               |       \---initialzer
|   |   |               |           \---account
|   |   |               |                   InitializerAdminAccount.java
|   |   |               |
|   |   |               +---controller
|   |   |               |   +---authentication
|   |   |               |   |       AuthUserController.java
|   |   |               |   |
|   |   |               |   +---crud
|   |   |               |   |   +---project
|   |   |               |   |   |       ProjectCrudController.java
|   |   |               |   |   |
|   |   |               |   |   +---task
|   |   |               |   |   |       TaskCrudController.java
|   |   |               |   |   |
|   |   |               |   |   \---user
|   |   |               |   |           UserCrudController.java
|   |   |               |   |
|   |   |               |   +---received
|   |   |               |   |   \---token
|   |   |               |   |       \---password
|   |   |               |   |           +---project
|   |   |               |   |           |       ReceivedPasswordProjectController.java
|   |   |               |   |           |
|   |   |               |   |           \---user
|   |   |               |   |                   ReceivedPasswordUserUserController.java
|   |   |               |   |
|   |   |               |   \---validate
|   |   |               |       \---token
|   |   |               |           \---password
|   |   |               |               +---project
|   |   |               |               |       ValidatePasswordProjectController.java
|   |   |               |               |
|   |   |               |               \---user
|   |   |               |                       ValidationTokenPasswordUserUserController.java
|   |   |               |
|   |   |               +---docs
|   |   |               |   +---authentication
|   |   |               |   |       AuthUserDoc.java
|   |   |               |   |
|   |   |               |   +---crud
|   |   |               |   |   +---project
|   |   |               |   |   |       ProjectCrudDoc.java
|   |   |               |   |   |
|   |   |               |   |   +---task
|   |   |               |   |   |       TaskCrudDoc.java
|   |   |               |   |   |
|   |   |               |   |   \---user
|   |   |               |   |           UserCrudDoc.java
|   |   |               |   |
|   |   |               |   +---received
|   |   |               |   |   \---token
|   |   |               |   |       \---password
|   |   |               |   |           +---project
|   |   |               |   |           |       ReceivedPasswordProjectDoc.java
|   |   |               |   |           |
|   |   |               |   |           \---user
|   |   |               |   |                   ReceivedPasswordUserDoc.java
|   |   |               |   |
|   |   |               |   \---validate
|   |   |               |       \---token
|   |   |               |           \---password
|   |   |               |               +---project
|   |   |               |               |       ValidationTokenPasswordProjectDoc.java
|   |   |               |               |
|   |   |               |               \---user
|   |   |               |                       ValidationTokenPasswordUserDoc.java
|   |   |               |
|   |   |               +---dto
|   |   |               |   +---request
|   |   |               |   |   +---authentication
|   |   |               |   |   |       UserLoginDto.java
|   |   |               |   |   |       UserRegisterDto.java
|   |   |               |   |   |
|   |   |               |   |   +---crud
|   |   |               |   |   |   +---project
|   |   |               |   |   |   |   +---post
|   |   |               |   |   |   |   |       ProjectPostDto.java
|   |   |               |   |   |   |   |
|   |   |               |   |   |   |   \---put
|   |   |               |   |   |   |           ProjectPutDto.java
|   |   |               |   |   |   |
|   |   |               |   |   |   +---task
|   |   |               |   |   |   |   +---post
|   |   |               |   |   |   |   |       TaskPostDto.java
|   |   |               |   |   |   |   |
|   |   |               |   |   |   |   \---put
|   |   |               |   |   |   |           TaskPutDto.java
|   |   |               |   |   |   |
|   |   |               |   |   |   \---user
|   |   |               |   |   |       +---patch
|   |   |               |   |   |       |       UserPatchDto.java
|   |   |               |   |   |       |
|   |   |               |   |   |       \---put
|   |   |               |   |   |           +---complete
|   |   |               |   |   |           |       UserPutDtoDataComplete.java
|   |   |               |   |   |           |
|   |   |               |   |   |           \---simple
|   |   |               |   |   |                   UserPutDtoDataSimple.java
|   |   |               |   |   |
|   |   |               |   |   +---received
|   |   |               |   |   |   \---token
|   |   |               |   |   |       \---password
|   |   |               |   |   |           +---project
|   |   |               |   |   |           |       IdProjectTokenPasswordRecoveryDto.java
|   |   |               |   |   |           |
|   |   |               |   |   |           \---user
|   |   |               |   |   |                   EmailUserRecoveryDto.java
|   |   |               |   |   |
|   |   |               |   |   \---validation
|   |   |               |   |       \---token
|   |   |               |   |           \---password
|   |   |               |   |                   ValidationTokenFromResetPasswordDto.java
|   |   |               |   |
|   |   |               |   \---response
|   |   |               |       +---authentication
|   |   |               |       |       ResponseDtoTokens.java
|   |   |               |       |
|   |   |               |       +---crud
|   |   |               |       |   +---project
|   |   |               |       |   |       ResponseProjectDataDto.java
|   |   |               |       |   |
|   |   |               |       |   +---task
|   |   |               |       |   |       ResponseTaskDataDto.java
|   |   |               |       |   |
|   |   |               |       |   \---user
|   |   |               |       |           ResponseUserDataDto.java
|   |   |               |       |
|   |   |               |       +---received
|   |   |               |       |   \---token
|   |   |               |       |       \---password
|   |   |               |       |               ResponseReceivedPassword.java
|   |   |               |       |
|   |   |               |       \---validation
|   |   |               |           \---token
|   |   |               |               \---password
|   |   |               |                       ResponseValidationPassword.java
|   |   |               |
|   |   |               +---enums
|   |   |               |       Priority.java
|   |   |               |       Role.java
|   |   |               |       Status.java
|   |   |               |
|   |   |               +---exceptions
|   |   |               |   +---handler
|   |   |               |   |       BadRequestHandler.java
|   |   |               |   |       DateParseHandler.java
|   |   |               |   |       ForbiddenHandler.java
|   |   |               |   |       HttpMessageNotReadableExceptionHandler.java
|   |   |               |   |       InternalServerErrorHandler.java
|   |   |               |   |       MethodArgumentNotValidExceptionHandler.java
|   |   |               |   |       NotFoundHandler.java
|   |   |               |   |
|   |   |               |   +---responsegeneric
|   |   |               |   |       ResponseGeneric.java
|   |   |               |   |
|   |   |               |   \---typo
|   |   |               |       +---runtime
|   |   |               |       |   +---badrequest
|   |   |               |       |   |       BadRequestException.java
|   |   |               |       |   |
|   |   |               |       |   +---dateparse
|   |   |               |       |   |       DateParseException.java
|   |   |               |       |   |
|   |   |               |       |   +---forbidden
|   |   |               |       |   |       ForbiddenException.java
|   |   |               |       |   |
|   |   |               |       |   +---internalservererror
|   |   |               |       |   |       InternalServerErrorException.java
|   |   |               |       |   |
|   |   |               |       |   \---notfound
|   |   |               |       |           NotFoundException.java
|   |   |               |       |
|   |   |               |       \---security
|   |   |               |           \---filter
|   |   |               |               +---response
|   |   |               |               |       AuthenticationExceptionEntry.java
|   |   |               |               |
|   |   |               |               \---typo
|   |   |               |                   +---enabled
|   |   |               |                   |       AccountIsDisabledException.java
|   |   |               |                   |
|   |   |               |                   \---token
|   |   |               |                       +---invalid
|   |   |               |                       |       TokenInvalidException.java
|   |   |               |                       |
|   |   |               |                       \---notfound
|   |   |               |                               TokenNotFoundException.java
|   |   |               |
|   |   |               +---facade
|   |   |               |   +---login
|   |   |               |   |   \---user
|   |   |               |   |           LoginAndGenerateTokensFacade.java
|   |   |               |   |
|   |   |               |   +---received
|   |   |               |   |   \---token
|   |   |               |   |       \---password
|   |   |               |   |           +---project
|   |   |               |   |           |   +---execute
|   |   |               |   |           |   |   \---send
|   |   |               |   |           |   |       \---mail
|   |   |               |   |           |   |           \---and
|   |   |               |   |           |   |               \---register
|   |   |               |   |           |   |                   \---data
|   |   |               |   |           |   |                           SendGmailAndSaveProjectRegisterDataFacade.java
|   |   |               |   |           |   |
|   |   |               |   |           |   \---get
|   |   |               |   |           |       +---entity
|   |   |               |   |           |       |   \---by
|   |   |               |   |           |       |       \---id
|   |   |               |   |           |       |               GetEntityProjectByIdOrThrow.java
|   |   |               |   |           |       |
|   |   |               |   |           |       \---token
|   |   |               |   |           |           \---unique
|   |   |               |   |           |                   GetTokenProjectPasswordUnique.java
|   |   |               |   |           |
|   |   |               |   |           \---user
|   |   |               |   |               +---execute
|   |   |               |   |               |   \---send
|   |   |               |   |               |       \---mail
|   |   |               |   |               |           \---and
|   |   |               |   |               |               \---register
|   |   |               |   |               |                   \---data
|   |   |               |   |               |                           SendGmailAndSaveUserRegisterDataFacade.java
|   |   |               |   |               |
|   |   |               |   |               \---get
|   |   |               |   |                       GetTokenUserPasswordUnique.java
|   |   |               |   |
|   |   |               |   +---register
|   |   |               |   |   +---project
|   |   |               |   |   |       RegisterNewProjectFacade.java
|   |   |               |   |   |
|   |   |               |   |   +---task
|   |   |               |   |   |       RegisterNewTaskFacade.java
|   |   |               |   |   |
|   |   |               |   |   \---user
|   |   |               |   |           RegisterNewUserAndGenerateTokensAuth.java
|   |   |               |   |
|   |   |               |   \---validation
|   |   |               |       \---token
|   |   |               |           \---password
|   |   |               |               +---project
|   |   |               |               |       ValidationTokenPasswordFromResetPasswordProjectFacade.java
|   |   |               |               |
|   |   |               |               \---user
|   |   |               |                       ValidationTokenPasswordFromResetPasswordUserFacade.java
|   |   |               |
|   |   |               +---mapper
|   |   |               |   +---project
|   |   |               |   |       ProjectMapperCore.java
|   |   |               |   |
|   |   |               |   +---task
|   |   |               |   |       TaskMapperCore.java
|   |   |               |   |
|   |   |               |   \---user
|   |   |               |           UserMapperCore.java
|   |   |               |
|   |   |               +---model
|   |   |               |   +---project
|   |   |               |   |       Project.java
|   |   |               |   |
|   |   |               |   +---register
|   |   |               |   |   \---token
|   |   |               |   |       \---password
|   |   |               |   |           +---project
|   |   |               |   |           |       RegisterPasswordProject.java
|   |   |               |   |           |
|   |   |               |   |           \---user
|   |   |               |   |                   RegisterPasswordUser.java
|   |   |               |   |
|   |   |               |   +---task
|   |   |               |   |       Task.java
|   |   |               |   |
|   |   |               |   \---user
|   |   |               |           User.java
|   |   |               |
|   |   |               +---repository
|   |   |               |   +---project
|   |   |               |   |       ProjectRepository.java
|   |   |               |   |
|   |   |               |   +---register
|   |   |               |   |   \---password
|   |   |               |   |       +---project
|   |   |               |   |       |       RegisterProjectPasswordRepository.java
|   |   |               |   |       |
|   |   |               |   |       \---user
|   |   |               |   |               RegisterUserPasswordRepository.java
|   |   |               |   |
|   |   |               |   +---task
|   |   |               |   |       TaskRepository.java
|   |   |               |   |
|   |   |               |   \---user
|   |   |               |           UserRepository.java
|   |   |               |
|   |   |               +---security
|   |   |               |   +---cfg
|   |   |               |   |       SecurityFilterConfiguration.java
|   |   |               |   |
|   |   |               |   +---filter
|   |   |               |   |       JwtFilter.java
|   |   |               |   |
|   |   |               |   \---token
|   |   |               |       +---generate
|   |   |               |       |   +---key
|   |   |               |       |   |       GenerateSecretKeyConverted.java
|   |   |               |       |   |
|   |   |               |       |   \---token
|   |   |               |       |       +---access
|   |   |               |       |       |       GenerateTokenAccess.java
|   |   |               |       |       |
|   |   |               |       |       \---refresh
|   |   |               |       |               GenerateTokenRefresh.java
|   |   |               |       |
|   |   |               |       +---get
|   |   |               |       |   \---email
|   |   |               |       |           GetEmailByPayload.java
|   |   |               |       |
|   |   |               |       \---validation
|   |   |               |           \---token
|   |   |               |                   TokenIsValid.java
|   |   |               |
|   |   |               +---send
|   |   |               |   \---email
|   |   |               |           SendEmail.java
|   |   |               |
|   |   |               +---service
|   |   |               |   +---authentication
|   |   |               |   |   +---implementations
|   |   |               |   |   |       CustomUserDetails.java
|   |   |               |   |   |       CustomUserDetailsService.java
|   |   |               |   |   |
|   |   |               |   |   +---login
|   |   |               |   |   |   +---token
|   |   |               |   |   |   |       GenerateTokensLoginService.java
|   |   |               |   |   |   |
|   |   |               |   |   |   \---user
|   |   |               |   |   |           LoginUserService.java
|   |   |               |   |   |
|   |   |               |   |   \---register
|   |   |               |   |       +---token
|   |   |               |   |       |       GenerateTokensRegisterService.java
|   |   |               |   |       |
|   |   |               |   |       +---user
|   |   |               |   |       |       UserRegisterService.java
|   |   |               |   |       |
|   |   |               |   |       \---validation
|   |   |               |   |               EmailAndUsernameIsUnique.java
|   |   |               |   |
|   |   |               |   +---crud
|   |   |               |   |   +---project
|   |   |               |   |   |       ProjectCrudService.java
|   |   |               |   |   |
|   |   |               |   |   +---task
|   |   |               |   |   |       TaskCrudService.java
|   |   |               |   |   |
|   |   |               |   |   \---user
|   |   |               |   |           UserCrudService.java
|   |   |               |   |
|   |   |               |   +---received
|   |   |               |   |   \---token
|   |   |               |   |       \---password
|   |   |               |   |           +---project
|   |   |               |   |           |   \---save
|   |   |               |   |           |       \---register
|   |   |               |   |           |               SaveRegisterProjectPasswordService.java
|   |   |               |   |           |
|   |   |               |   |           \---user
|   |   |               |   |               +---save
|   |   |               |   |               |   \---register
|   |   |               |   |               |           SaveRegisterUserPasswordService.java
|   |   |               |   |               |
|   |   |               |   |               \---validation
|   |   |               |   |                       ValidationEmailUserExistsInEntityInDb.java
|   |   |               |   |
|   |   |               |   \---validation
|   |   |               |       \---token
|   |   |               |           \---password
|   |   |               |               +---project
|   |   |               |               |   +---get
|   |   |               |               |   |   \---register
|   |   |               |               |   |       \---by
|   |   |               |               |   |           \---token
|   |   |               |               |   |                   GetRegisterProjectByToken.java
|   |   |               |               |   |
|   |   |               |               |   +---invalidate
|   |   |               |               |   |   \---register
|   |   |               |               |   |           InvalidateRegisterProjectAfterTokenUsed.java
|   |   |               |               |   |
|   |   |               |               |   \---reset
|   |   |               |               |       \---password
|   |   |               |               |               ResetPasswordProjectAndInvalidateTokenUsed.java
|   |   |               |               |
|   |   |               |               \---user
|   |   |               |                   +---get
|   |   |               |                   |   \---register
|   |   |               |                   |       \---by
|   |   |               |                   |           \---token
|   |   |               |                   |                   GetRegisterUserByToken.java
|   |   |               |                   |
|   |   |               |                   +---invalidate
|   |   |               |                   |   \---register
|   |   |               |                   |           InvalidateRegisterUserAfterTokenUsed.java
|   |   |               |                   |
|   |   |               |                   \---reset
|   |   |               |                       \---password
|   |   |               |                               ResetPasswordUserAndInvalidateTokenUsed.java
|   |   |               |
|   |   |               \---utils
|   |   |                   +---build
|   |   |                   |   \---message
|   |   |                   |       \---email
|   |   |                   |           \---html
|   |   |                   |                   BuildMessageInHtml.java
|   |   |                   |
|   |   |                   +---encryptedpassword
|   |   |                   |   |   EncryptedPassword.java
|   |   |                   |   |
|   |   |                   |   \---cfg
|   |   |                   |           PasswordConfiguration.java
|   |   |                   |
|   |   |                   +---generate
|   |   |                   |   +---token
|   |   |                   |   |   \---password
|   |   |                   |   |       \---recovery
|   |   |                   |   |               GenerateTokenPasswordRecovery.java
|   |   |                   |   |
|   |   |                   |   \---uri
|   |   |                   |           GenerateUri.java
|   |   |                   |
|   |   |                   +---get
|   |   |                   |   \---username
|   |   |                   |       \---by
|   |   |                   |           \---context
|   |   |                   |               \---security
|   |   |                   |                       GetUsernameByContextHolder.java
|   |   |                   |
|   |   |                   +---pageable
|   |   |                   |   +---factory
|   |   |                   |   |       PageableFactoryByClassReceived.java
|   |   |                   |   |
|   |   |                   |   +---fields
|   |   |                   |   |   \---name
|   |   |                   |   |       \---get
|   |   |                   |   |           \---use
|   |   |                   |   |               \---reflect
|   |   |                   |   |                   +---project
|   |   |                   |   |                   |       FieldsNameProject.java
|   |   |                   |   |                   |
|   |   |                   |   |                   \---user
|   |   |                   |   |                           FieldsNameUser.java
|   |   |                   |   |
|   |   |                   |   +---implementations
|   |   |                   |   |   +---project
|   |   |                   |   |   |       ImplementPageableProject.java
|   |   |                   |   |   |
|   |   |                   |   |   \---user
|   |   |                   |   |           ImplementPageableUser.java
|   |   |                   |   |
|   |   |                   |   +---interfaces
|   |   |                   |   |       PageableGenerator.java
|   |   |                   |   |
|   |   |                   |   \---logic
|   |   |                   |       \---default_
|   |   |                   |               PageableLogicDefault.java
|   |   |                   |
|   |   |                   +---parse
|   |   |                   |   \---data
|   |   |                   |       \---from
|   |   |                   |           \---iso
|   |   |                   |               \---american
|   |   |                   |                   |   ParseDataFromIsoAmerican.java
|   |   |                   |                   |
|   |   |                   |                   \---validation
|   |   |                   |                           ValidationIfDatePassedInRequestIsAfterDayNow.java
|   |   |                   |
|   |   |                   \---validation
|   |   |                       +---end
|   |   |                       |   \---date
|   |   |                       |       \---project
|   |   |                       |           \---not
|   |   |                       |               \---passed
|   |   |                       |                   \---limit
|   |   |                       |                       \---data
|   |   |                       |                               EndDateLimitProjectIsPassed.java
|   |   |                       |
|   |   |                       +---password
|   |   |                       |   \---bycrypt
|   |   |                       |       \---hash
|   |   |                       |           \---is
|   |   |                       |               \---equals
|   |   |                       |                       ValidationIfPasswordHashByCryptIsEquals.java
|   |   |                       |
|   |   |                       +---token
|   |   |                       |   \---from
|   |   |                       |       \---reset
|   |   |                       |           \---password
|   |   |                       |               \---is
|   |   |                       |                   +---expired
|   |   |                       |                   |       TokenPasswordIsExpired.java
|   |   |                       |                   |
|   |   |                       |                   \---used
|   |   |                       |                           TokenPasswordIsUsed.java
|   |   |                       |
|   |   |                       \---user
|   |   |                           +---is
|   |   |                           |   +---creator
|   |   |                           |   |   +---project
|   |   |                           |   |   |       UserRequestIsCreatorProject.java
|   |   |                           |   |   |
|   |   |                           |   |   \---task
|   |   |                           |   |           UserRequestIsCreatorTask.java
|   |   |                           |   |
|   |   |                           |   \---role
|   |   |                           |       \---admin
|   |   |                           |               ValidationIfUserIsRoleAdmin.java
|   |   |                           |
|   |   |                           \---put
|   |   |                               \---them
|   |   |                                   \---selves
|   |   |                                           ValidationIfUserEffectPutThemSelves.java
|   |   |
|   |   \---resources
|   |       |   application.yaml
|   |       |
|   |       +---db
|   |       |   \---migration
|   |       |           V1__Create_Table_Users.sql
|   |       |           V2__Create_Table_Register_Password_Users.sql
|   |       |           V3__Create_Table_Projects.sql
|   |       |           V4__Create_Table_Register_Password_Projects.sql
|   |       |           V5__Create_Table_Tasks.sql
|   |       |
|   |       +---static
|   |       \---templates
|   \---test
|       +---java
|       |   \---com
|       |       \---example
|       |           \---desafio
|       |               |   DesafioApplicationTests.java
|       |               |
|       |               \---service
|       |                   +---authentication
|       |                   |   +---login
|       |                   |   |   \---user
|       |                   |   |           LoginUserServiceTest.java
|       |                   |   |
|       |                   |   \---register
|       |                   |       \---user
|       |                   |               UserRegisterServiceTest.java
|       |                   |
|       |                   \---crud
|       |                       +---project
|       |                       |       ProjectCrudServiceTest.java
|       |                       |
|       |                       +---task
|       |                       |       TaskCrudServiceTest.java
|       |                       |
|       |                       \---user
|       |                               UserCrudServiceTest.java
|       |
|       \---resources
|               application-test-.yml
|
\---target
    |   desafio-0.0.1-SNAPSHOT.jar
    |   desafio-0.0.1-SNAPSHOT.jar.original
    |
    +---classes
    |   |   application.yaml
    |   |
    |   +---com
    |   |   \---example
    |   |       \---desafio
    |   |           |   DesafioApplication.class
    |   |           |
    |   |           +---cfg
    |   |           |   \---admin
    |   |           |       \---initialzer
    |   |           |           \---account
    |   |           |                   InitializerAdminAccount.class
    |   |           |
    |   |           +---controller
    |   |           |   +---authentication
    |   |           |   |       AuthUserController.class
    |   |           |   |
    |   |           |   +---crud
    |   |           |   |   +---project
    |   |           |   |   |       ProjectCrudController.class
    |   |           |   |   |
    |   |           |   |   +---task
    |   |           |   |   |       TaskCrudController.class
    |   |           |   |   |
    |   |           |   |   \---user
    |   |           |   |           UserCrudController.class
    |   |           |   |
    |   |           |   +---received
    |   |           |   |   \---token
    |   |           |   |       \---password
    |   |           |   |           +---project
    |   |           |   |           |       ReceivedPasswordProjectController.class
    |   |           |   |           |
    |   |           |   |           \---user
    |   |           |   |                   ReceivedPasswordUserUserController.class
    |   |           |   |
    |   |           |   \---validate
    |   |           |       \---token
    |   |           |           \---password
    |   |           |               +---project
    |   |           |               |       ValidatePasswordProjectController.class
    |   |           |               |
    |   |           |               \---user
    |   |           |                       ValidationTokenPasswordUserUserController.class
    |   |           |
    |   |           +---docs
    |   |           |   +---authentication
    |   |           |   |       AuthUserDoc.class
    |   |           |   |
    |   |           |   +---crud
    |   |           |   |   +---project
    |   |           |   |   |       ProjectCrudDoc.class
    |   |           |   |   |
    |   |           |   |   +---task
    |   |           |   |   |       TaskCrudDoc.class
    |   |           |   |   |
    |   |           |   |   \---user
    |   |           |   |           UserCrudDoc.class
    |   |           |   |
    |   |           |   +---received
    |   |           |   |   \---token
    |   |           |   |       \---password
    |   |           |   |           +---project
    |   |           |   |           |       ReceivedPasswordProjectDoc.class
    |   |           |   |           |
    |   |           |   |           \---user
    |   |           |   |                   ReceivedPasswordUserDoc.class
    |   |           |   |
    |   |           |   \---validate
    |   |           |       \---token
    |   |           |           \---password
    |   |           |               +---project
    |   |           |               |       ValidationTokenPasswordProjectDoc.class
    |   |           |               |
    |   |           |               \---user
    |   |           |                       ValidationTokenPasswordUserDoc.class
    |   |           |
    |   |           +---dto
    |   |           |   +---request
    |   |           |   |   +---authentication
    |   |           |   |   |       UserLoginDto.class
    |   |           |   |   |       UserRegisterDto.class
    |   |           |   |   |
    |   |           |   |   +---crud
    |   |           |   |   |   +---project
    |   |           |   |   |   |   +---post
    |   |           |   |   |   |   |       ProjectPostDto.class
    |   |           |   |   |   |   |
    |   |           |   |   |   |   \---put
    |   |           |   |   |   |           ProjectPutDto.class
    |   |           |   |   |   |
    |   |           |   |   |   +---task
    |   |           |   |   |   |   +---post
    |   |           |   |   |   |   |       TaskPostDto.class
    |   |           |   |   |   |   |
    |   |           |   |   |   |   \---put
    |   |           |   |   |   |           TaskPutDto.class
    |   |           |   |   |   |
    |   |           |   |   |   \---user
    |   |           |   |   |       +---patch
    |   |           |   |   |       |       UserPatchDto.class
    |   |           |   |   |       |
    |   |           |   |   |       \---put
    |   |           |   |   |           +---complete
    |   |           |   |   |           |       UserPutDtoDataComplete.class
    |   |           |   |   |           |
    |   |           |   |   |           \---simple
    |   |           |   |   |                   UserPutDtoDataSimple.class
    |   |           |   |   |
    |   |           |   |   +---received
    |   |           |   |   |   \---token
    |   |           |   |   |       \---password
    |   |           |   |   |           +---project
    |   |           |   |   |           |       IdProjectTokenPasswordRecoveryDto.class
    |   |           |   |   |           |
    |   |           |   |   |           \---user
    |   |           |   |   |                   EmailUserRecoveryDto.class
    |   |           |   |   |
    |   |           |   |   \---validation
    |   |           |   |       \---token
    |   |           |   |           \---password
    |   |           |   |                   ValidationTokenFromResetPasswordDto.class
    |   |           |   |
    |   |           |   \---response
    |   |           |       +---authentication
    |   |           |       |       ResponseDtoTokens.class
    |   |           |       |
    |   |           |       +---crud
    |   |           |       |   +---project
    |   |           |       |   |       ResponseProjectDataDto.class
    |   |           |       |   |
    |   |           |       |   +---task
    |   |           |       |   |       ResponseTaskDataDto.class
    |   |           |       |   |
    |   |           |       |   \---user
    |   |           |       |           ResponseUserDataDto.class
    |   |           |       |
    |   |           |       +---received
    |   |           |       |   \---token
    |   |           |       |       \---password
    |   |           |       |               ResponseReceivedPassword.class
    |   |           |       |
    |   |           |       \---validation
    |   |           |           \---token
    |   |           |               \---password
    |   |           |                       ResponseValidationPassword.class
    |   |           |
    |   |           +---enums
    |   |           |       Priority.class
    |   |           |       Role.class
    |   |           |       Status.class
    |   |           |
    |   |           +---exceptions
    |   |           |   +---handler
    |   |           |   |       BadRequestHandler.class
    |   |           |   |       DateParseHandler.class
    |   |           |   |       ForbiddenHandler.class
    |   |           |   |       HttpMessageNotReadableExceptionHandler.class
    |   |           |   |       InternalServerErrorHandler.class
    |   |           |   |       MethodArgumentNotValidExceptionHandler.class
    |   |           |   |       NotFoundHandler.class
    |   |           |   |
    |   |           |   +---responsegeneric
    |   |           |   |       ResponseGeneric.class
    |   |           |   |
    |   |           |   \---typo
    |   |           |       +---runtime
    |   |           |       |   +---badrequest
    |   |           |       |   |       BadRequestException.class
    |   |           |       |   |
    |   |           |       |   +---dateparse
    |   |           |       |   |       DateParseException.class
    |   |           |       |   |
    |   |           |       |   +---forbidden
    |   |           |       |   |       ForbiddenException.class
    |   |           |       |   |
    |   |           |       |   +---internalservererror
    |   |           |       |   |       InternalServerErrorException.class
    |   |           |       |   |
    |   |           |       |   \---notfound
    |   |           |       |           NotFoundException.class
    |   |           |       |
    |   |           |       \---security
    |   |           |           \---filter
    |   |           |               +---response
    |   |           |               |       AuthenticationExceptionEntry.class
    |   |           |               |
    |   |           |               \---typo
    |   |           |                   +---enabled
    |   |           |                   |       AccountIsDisabledException.class
    |   |           |                   |
    |   |           |                   \---token
    |   |           |                       +---invalid
    |   |           |                       |       TokenInvalidException.class
    |   |           |                       |
    |   |           |                       \---notfound
    |   |           |                               TokenNotFoundException.class
    |   |           |
    |   |           +---facade
    |   |           |   +---login
    |   |           |   |   \---user
    |   |           |   |           LoginAndGenerateTokensFacade.class
    |   |           |   |
    |   |           |   +---received
    |   |           |   |   \---token
    |   |           |   |       \---password
    |   |           |   |           +---project
    |   |           |   |           |   +---execute
    |   |           |   |           |   |   \---send
    |   |           |   |           |   |       \---mail
    |   |           |   |           |   |           \---and
    |   |           |   |           |   |               \---register
    |   |           |   |           |   |                   \---data
    |   |           |   |           |   |                           SendGmailAndSaveProjectRegisterDataFacade.class
    |   |           |   |           |   |
    |   |           |   |           |   \---get
    |   |           |   |           |       +---entity
    |   |           |   |           |       |   \---by
    |   |           |   |           |       |       \---id
    |   |           |   |           |       |               GetEntityProjectByIdOrThrow.class
    |   |           |   |           |       |
    |   |           |   |           |       \---token
    |   |           |   |           |           \---unique
    |   |           |   |           |                   GetTokenProjectPasswordUnique.class
    |   |           |   |           |
    |   |           |   |           \---user
    |   |           |   |               +---execute
    |   |           |   |               |   \---send
    |   |           |   |               |       \---mail
    |   |           |   |               |           \---and
    |   |           |   |               |               \---register
    |   |           |   |               |                   \---data
    |   |           |   |               |                           SendGmailAndSaveUserRegisterDataFacade.class
    |   |           |   |               |
    |   |           |   |               \---get
    |   |           |   |                       GetTokenUserPasswordUnique.class
    |   |           |   |
    |   |           |   +---register
    |   |           |   |   +---project
    |   |           |   |   |       RegisterNewProjectFacade.class
    |   |           |   |   |
    |   |           |   |   +---task
    |   |           |   |   |       RegisterNewTaskFacade.class
    |   |           |   |   |
    |   |           |   |   \---user
    |   |           |   |           RegisterNewUserAndGenerateTokensAuth.class
    |   |           |   |
    |   |           |   \---validation
    |   |           |       \---token
    |   |           |           \---password
    |   |           |               +---project
    |   |           |               |       ValidationTokenPasswordFromResetPasswordProjectFacade.class
    |   |           |               |
    |   |           |               \---user
    |   |           |                       ValidationTokenPasswordFromResetPasswordUserFacade.class
    |   |           |
    |   |           +---mapper
    |   |           |   +---project
    |   |           |   |       ProjectMapperCore.class
    |   |           |   |       ProjectMapperCoreImpl.class
    |   |           |   |
    |   |           |   +---task
    |   |           |   |       TaskMapperCore.class
    |   |           |   |       TaskMapperCoreImpl.class
    |   |           |   |
    |   |           |   \---user
    |   |           |           UserMapperCore.class
    |   |           |           UserMapperCoreImpl.class
    |   |           |
    |   |           +---model
    |   |           |   +---project
    |   |           |   |       Project.class
    |   |           |   |
    |   |           |   +---register
    |   |           |   |   \---token
    |   |           |   |       \---password
    |   |           |   |           +---project
    |   |           |   |           |       RegisterPasswordProject.class
    |   |           |   |           |
    |   |           |   |           \---user
    |   |           |   |                   RegisterPasswordUser.class
    |   |           |   |
    |   |           |   +---task
    |   |           |   |       Task.class
    |   |           |   |
    |   |           |   \---user
    |   |           |           User.class
    |   |           |
    |   |           +---repository
    |   |           |   +---project
    |   |           |   |       ProjectRepository.class
    |   |           |   |
    |   |           |   +---register
    |   |           |   |   \---password
    |   |           |   |       +---project
    |   |           |   |       |       RegisterProjectPasswordRepository.class
    |   |           |   |       |
    |   |           |   |       \---user
    |   |           |   |               RegisterUserPasswordRepository.class
    |   |           |   |
    |   |           |   +---task
    |   |           |   |       TaskRepository.class
    |   |           |   |
    |   |           |   \---user
    |   |           |           UserRepository.class
    |   |           |
    |   |           +---security
    |   |           |   +---cfg
    |   |           |   |       SecurityFilterConfiguration.class
    |   |           |   |
    |   |           |   +---filter
    |   |           |   |       JwtFilter.class
    |   |           |   |
    |   |           |   \---token
    |   |           |       +---generate
    |   |           |       |   +---key
    |   |           |       |   |       GenerateSecretKeyConverted.class
    |   |           |       |   |
    |   |           |       |   \---token
    |   |           |       |       +---access
    |   |           |       |       |       GenerateTokenAccess.class
    |   |           |       |       |
    |   |           |       |       \---refresh
    |   |           |       |               GenerateTokenRefresh.class
    |   |           |       |
    |   |           |       +---get
    |   |           |       |   \---email
    |   |           |       |           GetEmailByPayload.class
    |   |           |       |
    |   |           |       \---validation
    |   |           |           \---token
    |   |           |                   TokenIsValid.class
    |   |           |
    |   |           +---send
    |   |           |   \---email
    |   |           |           SendEmail.class
    |   |           |
    |   |           +---service
    |   |           |   +---authentication
    |   |           |   |   +---implementations
    |   |           |   |   |       CustomUserDetails.class
    |   |           |   |   |       CustomUserDetailsService.class
    |   |           |   |   |
    |   |           |   |   +---login
    |   |           |   |   |   +---token
    |   |           |   |   |   |       GenerateTokensLoginService.class
    |   |           |   |   |   |
    |   |           |   |   |   \---user
    |   |           |   |   |           LoginUserService.class
    |   |           |   |   |
    |   |           |   |   \---register
    |   |           |   |       +---token
    |   |           |   |       |       GenerateTokensRegisterService.class
    |   |           |   |       |
    |   |           |   |       +---user
    |   |           |   |       |       UserRegisterService.class
    |   |           |   |       |
    |   |           |   |       \---validation
    |   |           |   |               EmailAndUsernameIsUnique.class
    |   |           |   |
    |   |           |   +---crud
    |   |           |   |   +---project
    |   |           |   |   |       ProjectCrudService.class
    |   |           |   |   |
    |   |           |   |   +---task
    |   |           |   |   |       TaskCrudService.class
    |   |           |   |   |
    |   |           |   |   \---user
    |   |           |   |           UserCrudService.class
    |   |           |   |
    |   |           |   +---received
    |   |           |   |   \---token
    |   |           |   |       \---password
    |   |           |   |           +---project
    |   |           |   |           |   \---save
    |   |           |   |           |       \---register
    |   |           |   |           |               SaveRegisterProjectPasswordService.class
    |   |           |   |           |
    |   |           |   |           \---user
    |   |           |   |               +---save
    |   |           |   |               |   \---register
    |   |           |   |               |           SaveRegisterUserPasswordService.class
    |   |           |   |               |
    |   |           |   |               \---validation
    |   |           |   |                       ValidationEmailUserExistsInEntityInDb.class
    |   |           |   |
    |   |           |   \---validation
    |   |           |       \---token
    |   |           |           \---password
    |   |           |               +---project
    |   |           |               |   +---get
    |   |           |               |   |   \---register
    |   |           |               |   |       \---by
    |   |           |               |   |           \---token
    |   |           |               |   |                   GetRegisterProjectByToken.class
    |   |           |               |   |
    |   |           |               |   +---invalidate
    |   |           |               |   |   \---register
    |   |           |               |   |           InvalidateRegisterProjectAfterTokenUsed.class
    |   |           |               |   |
    |   |           |               |   \---reset
    |   |           |               |       \---password
    |   |           |               |               ResetPasswordProjectAndInvalidateTokenUsed.class
    |   |           |               |
    |   |           |               \---user
    |   |           |                   +---get
    |   |           |                   |   \---register
    |   |           |                   |       \---by
    |   |           |                   |           \---token
    |   |           |                   |                   GetRegisterUserByToken.class
    |   |           |                   |
    |   |           |                   +---invalidate
    |   |           |                   |   \---register
    |   |           |                   |           InvalidateRegisterUserAfterTokenUsed.class
    |   |           |                   |
    |   |           |                   \---reset
    |   |           |                       \---password
    |   |           |                               ResetPasswordUserAndInvalidateTokenUsed.class
    |   |           |
    |   |           \---utils
    |   |               +---build
    |   |               |   \---message
    |   |               |       \---email
    |   |               |           \---html
    |   |               |                   BuildMessageInHtml.class
    |   |               |
    |   |               +---encryptedpassword
    |   |               |   |   EncryptedPassword.class
    |   |               |   |
    |   |               |   \---cfg
    |   |               |           PasswordConfiguration.class
    |   |               |
    |   |               +---generate
    |   |               |   +---token
    |   |               |   |   \---password
    |   |               |   |       \---recovery
    |   |               |   |               GenerateTokenPasswordRecovery.class
    |   |               |   |
    |   |               |   \---uri
    |   |               |           GenerateUri.class
    |   |               |
    |   |               +---get
    |   |               |   \---username
    |   |               |       \---by
    |   |               |           \---context
    |   |               |               \---security
    |   |               |                       GetUsernameByContextHolder.class
    |   |               |
    |   |               +---pageable
    |   |               |   +---factory
    |   |               |   |       PageableFactoryByClassReceived.class
    |   |               |   |
    |   |               |   +---fields
    |   |               |   |   \---name
    |   |               |   |       \---get
    |   |               |   |           \---use
    |   |               |   |               \---reflect
    |   |               |   |                   +---project
    |   |               |   |                   |       FieldsNameProject.class
    |   |               |   |                   |
    |   |               |   |                   \---user
    |   |               |   |                           FieldsNameUser.class
    |   |               |   |
    |   |               |   +---implementations
    |   |               |   |   +---project
    |   |               |   |   |       ImplementPageableProject.class
    |   |               |   |   |
    |   |               |   |   \---user
    |   |               |   |           ImplementPageableUser.class
    |   |               |   |
    |   |               |   +---interfaces
    |   |               |   |       PageableGenerator.class
    |   |               |   |
    |   |               |   \---logic
    |   |               |       \---default_
    |   |               |               PageableLogicDefault.class
    |   |               |
    |   |               +---parse
    |   |               |   \---data
    |   |               |       \---from
    |   |               |           \---iso
    |   |               |               \---american
    |   |               |                   |   ParseDataFromIsoAmerican.class
    |   |               |                   |
    |   |               |                   \---validation
    |   |               |                           ValidationIfDatePassedInRequestIsAfterDayNow.class
    |   |               |
    |   |               \---validation
    |   |                   +---end
    |   |                   |   \---date
    |   |                   |       \---project
    |   |                   |           \---not
    |   |                   |               \---passed
    |   |                   |                   \---limit
    |   |                   |                       \---data
    |   |                   |                               EndDateLimitProjectIsPassed.class
    |   |                   |
    |   |                   +---password
    |   |                   |   \---bycrypt
    |   |                   |       \---hash
    |   |                   |           \---is
    |   |                   |               \---equals
    |   |                   |                       ValidationIfPasswordHashByCryptIsEquals.class
    |   |                   |
    |   |                   +---token
    |   |                   |   \---from
    |   |                   |       \---reset
    |   |                   |           \---password
    |   |                   |               \---is
    |   |                   |                   +---expired
    |   |                   |                   |       TokenPasswordIsExpired.class
    |   |                   |                   |
    |   |                   |                   \---used
    |   |                   |                           TokenPasswordIsUsed.class
    |   |                   |
    |   |                   \---user
    |   |                       +---is
    |   |                       |   +---creator
    |   |                       |   |   +---project
    |   |                       |   |   |       UserRequestIsCreatorProject.class
    |   |                       |   |   |
    |   |                       |   |   \---task
    |   |                       |   |           UserRequestIsCreatorTask.class
    |   |                       |   |
    |   |                       |   \---role
    |   |                       |       \---admin
    |   |                       |               ValidationIfUserIsRoleAdmin.class
    |   |                       |
    |   |                       \---put
    |   |                           \---them
    |   |                               \---selves
    |   |                                       ValidationIfUserEffectPutThemSelves.class
    |   |
    |   \---db
    |       \---migration
    |               V1__Create_Table_Users.sql
    |               V2__Create_Table_Register_Password_Users.sql
    |               V3__Create_Table_Projects.sql
    |               V4__Create_Table_Register_Password_Projects.sql
    |               V5__Create_Table_Tasks.sql
    |
    \---test-classes
        |   application-test-.yml
        |
        \---com
            \---example
                \---desafio
                    |   DesafioApplicationTests.class
                    |
                    \---service
                        +---authentication
                        |   +---login
                        |   |   \---user
                        |   |           LoginUserServiceTest.class
                        |   |
                        |   \---register
                        |       \---user
                        |               UserRegisterServiceTest.class
                        |
                        \---crud
                            +---project
                            |       ProjectCrudServiceTest.class
                            |
                            +---task
                            |       TaskCrudServiceTest.class
                            |
                            \---user
                                    UserCrudServiceTest.class
</pre>
</div>

<h2 style="text-align:center">Como Rodar A Aplicação</h2>

<p>Este projeto utiliza Spring Boot + PostgreSQL e é configurado para rodar facilmente usando Docker Compose.

O Docker Compose vai:

Subir o PostgreSQL com banco, usuário e senha definidos no .env

Subir a API conectada no banco e pronta para enviar emails

Garantir que todas as variáveis de ambiente necessárias estejam disponíveis dentro dos containers</p>

<h2>Pré-Requisitos:</h2>

<p>Antes de rodar o projeto, você precisa ter instalado:

Docker

Docker Compose

Java 17+
 (para build da API, se for necessário)

Maven ou Gradle, se for build manual</p>

<h2>Configurando As Variavéis De Ambiente:</h2>

<p>O projeto fornece um arquivo de exemplo .env.example com todas as variáveis necessárias já preenchidas para teste, incluindo:

Credenciais do banco (DATABASE_USERNAME, DATABASE_PASSWORD)

Email do projeto e senha de app (EMAIL_USERNAME, EMAIL_PASSWORD_APP)

Chave secreta (SECRET_KEY_IN_BASE_64)

Não é necessário alterar nada no .env.example. Basta copiar para .env e tudo vai funcionar corretamente.</p>


<div style="
  background-color: #0d1117;
  color: #c9d1d9;
  border-radius: 8px;
  overflow-x: auto;
  font-family: 'Fira Code', 'JetBrains Mono', monospace;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #30363d;
 
<pre>
DATABASE_USERNAME=my_username
DATABASE_PASSWORD=my_password
EMAIL_USERNAME=matheuslfdesafio@gmail.com
EMAIL_PASSWORD_APP=ubwbgymltehghhwa
SECRET_KEY_IN_BASE_64=cUFZeW5zcVRSam5CWXJhdHByOUpha2pCcGRIRm0wZUE0NzBHUFByN2tyWU5FMzRXdE5zTFNab0F1YkNXYURzTkZqTEVlT0paZk9QemkxQ1lLWTdCMlY
</pre>
  </div>

<h2>Rodando O Docker Compose:</h2>

<h3 style="text-align:center">Powershell,terminal padrao do Linux Ou Mac:</h3>
<div style="
  background-color: #0d1117;
  color: #c9d1d9;
  border-radius: 8px;
  overflow-x: auto;
  font-family: 'Fira Code', 'JetBrains Mono', monospace;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #30363d;">

  <pre>cp .env.example .env
</pre>
  </div>

  <h3 style="text-align:center">Cmd:</h3>

  <div style="
  background-color: #0d1117;
  color: #c9d1d9;
  border-radius: 8px;
  overflow-x: auto;
  font-family: 'Fira Code', 'JetBrains Mono', monospace;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #30363d;">

  <pre>copy .env.example .env

</pre>
  </div>

<h2 style="text-alig:center">Observaçoes Sobre o Uso Do Software<h2>

<h2>Testes Desenvolvidos Do Software</h2>

<p>Optei Por fazer Somente Os Testes Unitários do service, as Classes da camada Facade que Acoplam uma complexidade maior, optei por não fazer os testes unitários por conta do tempo, basicamente estou viajando enquanto desenvolvo os testes(o software em sí foi terminando 1 dia antes da viagem), consigo usar 1h30 por dia da viagem(hora de desanso pós almoço) para desenvolver os testes unitários, por isso os testes funcionam mas não estão em seu melhor estado de coesão,legibilidade e manutenabilidade igual ao restante do software em sí, e por este mesmo motivo(festas de fim de ano/viagem) nao desenvolvi os Testes De Integração dos controllers.</p>

<h2>Documentação dos end-points<h2>

<p>Utilizei o Swagger para documentar os end-points da api. nela conté todos os detalhes de cada end-point.basta copiar a url abaixo e cola-la no navegador quando rodar a aplicação.</p>

 <div style="
  background-color: #0d1117;
  color: #c9d1d9;
  border-radius: 8px;
  overflow-x: auto;
  font-family: 'Fira Code', 'JetBrains Mono', monospace;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #30363d;">

  <pre>
   http://localhost:8080/swagger-ui/index.html#/
</pre>
  </div>

  <h2 syle="text-align:center">Observações</h2>


  <h2>Quais EndPoints Não Necessitam de Autenticação?<h2>

  <p>Os Endpoints de Autenticação e Os EndPoints para receberem o token e validarem o token para o reset da senha nao necessitam de Autenticação. </p>

  <h2>Sobre A Autenticação:</h2>
  <p>utilize o auth/register para criar uma nova conta e um auth/login para logar na conta(leia todos os detalhes na doc do Swagger). quando for recebido os tokens pegue-o um deles e escreva Authroization no headaer, e no value de Authroization faça ->Bearer<- (de um espaço) ->cole o Token aqui<-.
  assim você estará autenticado para usar os endpoints que necessitam de autenticação.</p>

  <h2>Exemplo</h2>
  <img src="imgs/img-authorization-como-usaar.png">

  <h2>Como Resetar A senha De Acesso A Conta De Usuario e ou senha de Acesso ao Projeto</h2>

  <p>Primeiro Insira O Email que Voce usou para criar a sua conta. caso passe um email nao cadatrado falhará e você receberá uma exceção.</p>

  <p>Caso queira resetar a senha que pe usada como permissao para que sej possivel criar tasks em um projeto especifico, passe o id do projeto ao invés do email, após isso automaticamente o criador do projeto receberá o token para resetar a senha de acesso ao projeto. somente o criador do projeto pode fazer este reset da senha de acesso.</p>

  <h2>Email Nao Cadastrado<h2>

  <img src="imgs/img-email-nao-cadastrado.png">


  <h2>Email Cadastrado</h2>

  <img src="imgs/img-email-cadastrado.png">

  <p>Quando Inserido O Email Corretamente Chegará no Gmail o token que será utilizado para Efetuar o reset da Senha do usario.</p>

  <img src="imgs/img-email-recebido.png">

  <p>Apos isso pegue este token e insira-o ,e também coloque a nova senha.</p>

  <img src="imgs/img-inserir-token.png">

  <p>Se o Token Estiver Correto será recebido um status 200 ok com uma mensagem informando que a senha foi resetada com sucesso.</p>

  <img src="imgs/img-senha-resetada.png">


  <p>Um Lembrete, o token enviado expira em 5 minutos. se passar de 5 minutos não conseguirá resetar a senha e terá que refazer o processo. e após usado o token é invalidado nao podendo ser utilizado novamente. e cada token é unico.</p>

  <img src="imgs/img-expiração-senha.png">



<h2>Sobre O preenchimento Da Data Limite do Projeto e da Task</h2>

<p>Utilize sempre o formato da Iso Brasileira de dd(dia),mm(mes),yyyy(ano) exemplo: 01/01/2026(nao esqueça de usar as barras como separador das datas), e nao insira datas anteriores ao dia atual, caso uma dessas condiçoes nao seja cumprida, será recebido uma exceção.</p>

<p>Caso Haja Alguma Dúvida, ou caso queiram entrar em contato comigo enviem um email para<strong> danielhatusviana@gmail.com</strong></p>





  


