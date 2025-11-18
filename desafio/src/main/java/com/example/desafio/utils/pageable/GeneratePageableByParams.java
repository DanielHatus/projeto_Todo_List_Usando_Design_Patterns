package com.example.desafio.utils.pageable;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.utils.get.fields.user.GetFieldsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GeneratePageableByParams{
    private final GetFieldsUser getFieldsUser=GetFieldsUser.getInstanceGetFieldUser();
    private final List<String> fieldsName;

    public GeneratePageableByParams(){
        this.fieldsName=getFieldsUser.getFieldsUserClassName();
        deletePasswordFieldFromList();
    }

    public Pageable generate(Integer page,Integer size,String order,String direction){
        int pageCorrect=validationPageParamAndReturnPageOrThrowIfPageIsIncorrect(page);
        int sizeCorrect=validationSizeParamAndReturnPageOrThrowIfSizeIsIncorrect(size);
        String orderCorrect=validationOrderAndReturnOrderOrThrowIfOrderIsIncorrect(order);
        Sort.Direction directionCorrect= validationDirectionAndReturnDirectionOrThrowIfDirectionIsIncorrect(direction);

        log.debug("✅ The pageable was successfully generated. It's ready to be used in the service and generate the paginated list" +
                " according to the client's preferences.");

        return PageRequest.of(pageCorrect,sizeCorrect,Sort.by(directionCorrect,orderCorrect));
    }

    private Integer validationPageParamAndReturnPageOrThrowIfPageIsIncorrect(Integer page){
        if (page==null||page<=0){
            log.error("❌ The value passed to the page was null or incorrect. Returning an exception.");

            throw new BadRequestException("The page value cannot be null or less than 0.");
        }
           log.debug("✅ The value passed to the page was successfully validated, returning the page to be used in the page request.");

           return page-1;
    }

    private Integer validationSizeParamAndReturnPageOrThrowIfSizeIsIncorrect(Integer size){

        if (size==null||size<=0){
            log.error(" ❌ The value passed in the pageable's size was null or less than 1, returning an exception.");

            throw new BadRequestException("The value passed in the size cannot be null or less than 1.");
        }

            log.debug("✅ The value passed to the size was successfully validated, returning the size to be used in the page request.");

            return size;
    }

    private void deletePasswordFieldFromList(){
        this.fieldsName.remove("password");
        log.debug("✅ The password field was successfully deleted from the list.");
    }

    private String validationOrderAndReturnOrderOrThrowIfOrderIsIncorrect(String order){
        if (order==null){
            log.error("❌ The value passed in the order was null. Returning an exception.");
            throw new BadRequestException("The value passed in the order was null. Please pass only valid values.");

        }

        boolean orderIsCorrect=false;

        for(String name:this.fieldsName){
            if (order.equals(name)){
                orderIsCorrect=true;
                break;
            }
        }

        if (!orderIsCorrect){
            log.error("❌ The value passed in the order was incorrect. Returning an exception.");
            throw new BadRequestException("The value passed in the order was incorrect. Please pass only valid values.");
        }


        log.debug("✅ The value passed in the order of the page was successfully validated. " +
                "Return the value from the order to be used in the pageable.");

        return order;
    }

    private Sort.Direction validationDirectionAndReturnDirectionOrThrowIfDirectionIsIncorrect(String direction) {
        if (direction == null || !direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            log.error("❌ The value passed to the direction was incorrect. An exception was returned.");

            throw new BadRequestException("The value passed in the request direction parameter is null or incorrect. " +
                    "Please pass only asc (ascending) or desc (descending).");
        }
        return (direction.equalsIgnoreCase("asc")? Sort.Direction.ASC: Sort.Direction.DESC);
    }

}
