package com.example.desafio.utils.get.fields.user;


import com.example.desafio.model.user.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetFieldsUser{
    private static GetFieldsUser instance;
    private final List<String> fieldsUserClassName;

    private GetFieldsUser(){
      this.fieldsUserClassName=getFieldsAndAddInListFieldsUserClassName();
    }

    public static GetFieldsUser getInstanceGetFieldUser(){
        if (instance==null){
            log.debug("✅ The instance of the GetFieldsUser class has just been generated, since the class was not instantiated until now.");
            return new GetFieldsUser();
        }
        log.debug("✅ Simply returning the instance of the Get Fields User class, since the class has already been instantiated previously.");
        return instance;
    }

    private List<String> getFieldsAndAddInListFieldsUserClassName(){
      List<String> fieldsName=new ArrayList<>();
      for(Field field: User.class.getDeclaredFields()){
          fieldsName.add(field.getName());
      }
      return fieldsName;
    }

    public List<String> getFieldsUserClassName(){
        return this.fieldsUserClassName;
    }
}
