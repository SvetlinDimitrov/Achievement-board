package com.example.achievementboard.domain.constants;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;

public class DataChecker {

    public static void check(BindingResult result , LocalDate begin , LocalDate end , String objectName , String fieldName){
        if(begin != null && end !=null && begin.isAfter(end)){
            result.addError(new FieldError(objectName , fieldName, "incorrect dates"));
        }
    }
}
