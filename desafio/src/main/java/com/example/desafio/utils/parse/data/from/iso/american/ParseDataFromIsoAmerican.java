package com.example.desafio.utils.parse.data.from.iso.american;

import com.example.desafio.exceptions.typo.runtime.DateParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@Component
@Slf4j
public class ParseDataFromIsoAmerican{
    private DateTimeFormatter formatBrazilianIso;

    public ParseDataFromIsoAmerican(){
        this.formatBrazilianIso=DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    }
    public LocalDate parseDataFormatBrazilianImAmerican(String dateRequest){
        try{
            LocalDate formatIsoAmerican=LocalDate.parse(dateRequest,this.formatBrazilianIso);
            log.debug("✅ The date received in the client request was indeed in Brazilian format. " +
                    "The formatting to American format was successfully completed, returning the formatted date.");
            return formatIsoAmerican;
        }
        catch (DateTimeParseException e){
            log.error("❌ The date provided by the customer was invalid or incorrect. " +
                    "It was not possible to format the date to the American format.");
            throw new DateParseException("date received from request is invalid");
        }
    }

}
