/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataSourceConfigErrorException extends RuntimeException{

    public DataSourceConfigErrorException(String message) {
        super(message);
    }
    
}
