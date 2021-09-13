package com.cardanonft.api.response;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.interfaces.CustomException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

@NoArgsConstructor
@Data
public class EntityDefaultResponse {

    private int returnCode;
    private String returnMessage;

    private Object entity;

    public EntityDefaultResponse(HttpRequestMethodNotSupportedException e) {
        returnCode = HttpStatus.METHOD_NOT_ALLOWED.value();
        returnMessage = e.getMessage();
    }

    public EntityDefaultResponse(MissingServletRequestParameterException e) {
        returnCode = HttpStatus.BAD_REQUEST.value();
        returnMessage = e.getMessage();
    }

    public EntityDefaultResponse(CustomException e) {
        returnCode = e.getReturnCode();
        returnMessage = e.getReturnMessage();
    }

    public EntityDefaultResponse(RETURN_CODE return_code) {
        returnCode = return_code.getReturnCode();
        returnMessage = return_code.getReturnMessage();
    }

    public EntityDefaultResponse(RETURN_CODE return_code, Exception e) {
        returnCode = return_code.getReturnCode();
        returnMessage = e.getMessage();
    }

    public EntityDefaultResponse(RETURN_CODE return_code, Object o) {
        returnCode = return_code.getReturnCode();
        entity = o;
    }

    public EntityDefaultResponse(Object entity) {
        this.entity = entity;
        this.returnCode = HttpStatus.OK.value();
    }
}
