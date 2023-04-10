package com.ranga.exception;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmchase.core.common.validation.BaseException;
import com.jpmchase.core.common.validation.ConstraintValidationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.*;

@ControllerAdvice
public class ApiExceptionMapper {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionMapper.class);

    // Catch All Exception
    @ExceptionHandler({
            Exception.class
    })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Fault internalError(Exception exception) {
        logger.error("Internal Error: {} ", exception.getLocalizedMessage(),exception);
        String code = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        return new Fault(exception.getLocalizedMessage(),code);
    }

    @ExceptionHandler({
            ConstraintValidationException.class,
            BaseException.class
    })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Fault commonsInternalError(BaseException exception) throws IOException {
       return logAndCreateFault(exception);
    }

    private Fault logAndCreateFault(BaseException baseException) throws IOException {
        final Optional<Fault> cve=cve(baseException);
        if(cve.isPresent()){
          return  cve.get();
        }else{
            String code = baseException.getCode();
            Map<String, Object> argsMap = baseException.getArgsMap();
            String refId = fetchRefId();
            Fault fault = new Fault(refId, baseException.getLocalizedMessage(), code, argsMap);
            logger.error("ErrorCode = {}, ErrorMessage = {},  Exception = {} ",
                    code, baseException.getLocalizedMessage(), baseException);
            return fault;
        }
    }

    private Optional<Fault> cve(BaseException baseException) throws IOException {
       if(baseException instanceof ConstraintValidationException){
           ConstraintValidationException cve=(ConstraintValidationException)baseException;
           final List<Map<String,Object>> violations= (List<Map<String, Object>>) cve.getArgsMap().get("violations");
           if(!CollectionUtils.isEmpty(violations)){
               Map<String,Object> violation=violations.get(0);
               final String messageCode=getMessageCode(violation);
               if(StringUtils.isNotEmpty(messageCode)&&StringUtils.isNotEmpty(cve.getMessage())&&cve.getMessage().contains(":")){
                   final String message=cve.getMessage().substring(cve.getMessage().indexOf(":")+2);
                   Map<String,Object> argsMap=baseException.getArgsMap();
                   String refId=fetchRefId();
                   return Optional.of(new Fault(refId,message,messageCode));
               }
           }
       }
       return Optional.empty();
    }

    private String getMessageCode(Map<String, Object> violation) throws IOException {
        //1. For javax validation parse error code from the messageTemplate
        //2. For condor validation parse error code from path
        String messageCode = null;
        if (violation.get("messageTemplate") != null) {
            final String messageTemplate = String.valueOf(violation.get("messageTemplate"));
            if (StringUtils.isNotEmpty(messageTemplate)) {
                //parse javax message code
                if (messageTemplate.startsWith("{") && messageTemplate.endsWith("}")) {
                    messageCode = messageTemplate.substring(1, messageTemplate.length() - 1);
                } else {
                    //parse condor message code from path
                    final List<?> paths = (List<?>) violation.get("path");
                    if (!CollectionUtils.isEmpty(paths)) {
                        for (Object path : paths) {
                            Map<String, Object> parsed = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(path), Map.class);
                            if (!parsed.isEmpty() && "Property".equals(parsed.get("type"))) {
                                if (parsed.get("info") != null) {
                                    String sInfo = String.valueOf(parsed.get("info"));
                                    if (sInfo.startsWith("{") && sInfo.endsWith("}")) {
                                        messageCode = sInfo.substring(1, sInfo.length() - 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return messageCode;
    }

    private Fault logAndCreateGenericHttpFault(Exception exception, HttpStatus httpStatus) {
        String code = httpStatus.getReasonPhrase();
        String refId = fetchRefId();
        Fault fault = new Fault(refId, exception.getLocalizedMessage(), code);
        logger.error("ErrorCode = {}, ErrorMessage = {} Exception = {}", code,exception.getLocalizedMessage(), exception);
        return fault;
    }

    private String fetchRefId() {
        String refId = "";

        return UUID.randomUUID().toString();
    }
}
