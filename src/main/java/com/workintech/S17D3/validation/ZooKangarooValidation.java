package com.workintech.S17D3.validation;

import com.workintech.S17D3.exceptions.ZooException;
import org.springframework.http.HttpStatus;

public class ZooKangarooValidation {
    public static void isValid(Integer id){
        if(id == null || id < 0){
            throw new ZooException("Id is not valid! " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
