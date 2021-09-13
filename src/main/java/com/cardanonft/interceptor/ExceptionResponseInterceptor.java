package com.cardanonft.interceptor;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.response.EntityDefaultResponse;
import com.cardanonft.validate.constraints.AvailableDigits;
import com.cardanonft.validate.constraints.Emoji;
import com.cardanonft.validate.constraints.MacAddr;
import com.cardanonft.validate.constraints.NoneAvailableDigits;
import com.google.gson.Gson;
import com.cardanonft.api.response.DefaultResponse;
import com.cardanonft.api.response.EntitiesDefaultResponse;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ControllerAdvice
public class ExceptionResponseInterceptor extends ResponseEntityExceptionHandler implements ResponseBodyAdvice<Object> {

    private static Logger logger = LoggerFactory.getLogger(ExceptionResponseInterceptor.class);

    @ExceptionHandler({CustomBadRequestException.class})
    public ResponseEntity<DefaultResponse> handleAccessDeniedException(CustomBadRequestException e, WebRequest request) {
        logger.info(e.getMessage());
        return new ResponseEntity<DefaultResponse>(new DefaultResponse(e), HttpStatus.OK);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<DefaultResponse> handleAccessDeniedException(ResourceNotFoundException e, WebRequest request) {
        return new ResponseEntity<DefaultResponse>(new DefaultResponse(RETURN_CODE.NO_DATA), HttpStatus.OK);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<DefaultResponse> handleAccessDeniedException(Exception e, WebRequest request) {
        logger.error(new Gson().toJson(request.getParameterMap()), e);
        return new ResponseEntity<DefaultResponse>(new DefaultResponse(RETURN_CODE.ERROR, e), HttpStatus.OK);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().equals(ResponseEntity.class) ||
                returnType.getParameterType().equals(Resources.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof PersistentEntityResource) {
            EntityDefaultResponse entityDefaultResponse = new EntityDefaultResponse(RETURN_CODE.SUCCESS);
            entityDefaultResponse.setEntity(((PersistentEntityResource) body).getContent());
            return entityDefaultResponse;
        } else if (body instanceof Resources) {
            EntitiesDefaultResponse entitiesDefaultResponse = new EntitiesDefaultResponse(RETURN_CODE.SUCCESS);
            entitiesDefaultResponse.setEntities(((Resources) body).getContent());
            return entitiesDefaultResponse;
        }

        return body;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        logger.warn(
                String.format("bad parameter %s user_id:%s error:%s", request.getDescription(false), request.getHeader("user_id"), ex.getMessage()));

        if (ex.getMessage().indexOf(NoneAvailableDigits.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(AvailableDigits.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST_PARAM_TYPE), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(NotBlank.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(Emoji.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.NOT_USERNM_EMOJI), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(Length.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST,
                    ex.getBindingResult().getFieldError().getField() + " " + RETURN_CODE.BAD_REQUEST_NOT_LENGTH.getReturnMessage()
            ), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(MacAddr.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.NOT_MACADDR), HttpStatus.OK);
        }

        if (ex.getMessage().indexOf(Min.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST_PARAM_TYPE), HttpStatus.OK);
        }
        if (ex.getMessage().indexOf(Max.class.getSimpleName()) != -1) {
            return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST_PARAM_TYPE), HttpStatus.OK);
        }

        return new ResponseEntity(new DefaultResponse(RETURN_CODE.BAD_REQUEST), HttpStatus.OK);
    }
}
