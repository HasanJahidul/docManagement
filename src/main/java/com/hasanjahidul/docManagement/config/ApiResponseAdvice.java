package com.hasanjahidul.docManagement.config;

import com.hasanjahidul.docManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        //Ignore Image media content types
        if (selectedContentType.getType().equals("image")) {
            return body;
        }

        // List of classes that should just return body

        //Create a list of classes that should just return body
        List<Class<?>> classesToReturnBody = new ArrayList<>(List.of(ResponseEntity.class, CreateSuccessModel.class, DeleteSuccessModel.class,
                NotFoundModel.class, UpdateSuccessModel.class, InvalidRequestModel.class,
                APIResponseModel.class));

        // Check if body is an instance of the classes in the list
        for (Class<?> cls : classesToReturnBody) {
            if (cls.isInstance(body)) {
                if((body instanceof CreateSuccessModel)){
                    response.setStatusCode(HttpStatus.CREATED);
                }
                if((body instanceof UpdateSuccessModel)){
                    response.setStatusCode(HttpStatus.OK);
                }
                if((body instanceof DeleteSuccessModel)){
                    response.setStatusCode(HttpStatus.OK);
                }
                if((body instanceof NotFoundModel)){
                    response.setStatusCode(HttpStatus.NOT_FOUND);
                }
                if((body instanceof APIResponseModel)){
                    response.setStatusCode(HttpStatus.OK);
                }if ((body instanceof InvalidRequestModel)){
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                }

                return body;
            }
        }
        System.out.println("The Response Body Object now is ---->");
        String className = body.getClass().getName();
        String simpleClassName = body.getClass().getSimpleName();
        System.out.println("The Response Body Class Name is: " + className);
        System.out.println("The Response Body Simple Class Name is: " + simpleClassName);

        //If Exceptions are found during pre- and post-processing return body
        LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
        if (body instanceof ProblemDetail) {
            responseMap.put("message", ((ProblemDetail) body).getDetail());
            responseMap.put("status", ((ProblemDetail) body).getStatus());
            System.out.println("Error: The Response Body is: " + responseMap.toString());
            System.out.println("Error: The Response Body is: " + responseMap.toString());
            // Set status to 400 for InvalidRequestModel

            return responseMap;
        } else if (body instanceof LinkedHashMap<?, ?>) {
            try {
                LinkedHashMap<String, Object> bodyMap = (LinkedHashMap<String, Object>) body;
                if (bodyMap.containsKey("error")) {
                    return body;
                }
            } catch (Exception e) {
                System.out.println("Error in casting body to LinkedHashMap: " + e.getMessage());
                System.out.println("Error in casting body to LinkedHashMap: "+ e);
            }
        }

        // Create your wrapper object
        return new APIResponseModel<>(body);
    }
}
