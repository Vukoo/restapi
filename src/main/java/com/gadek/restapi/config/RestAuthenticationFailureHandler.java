package com.gadek.restapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gadek.restapi.response.ApiResponse;
import com.gadek.restapi.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        super.onAuthenticationFailure(request, response, exception);
//        String unauthorisedResponse = new ObjectMapper().writeValueAsString(BaseResponse.unauthorisedResponse());
//        ApiResponse apiResponse = BaseResponse.unauthorisedResponse();
//        Map<String, Object> data = new HashMap<>();
//        data.put("localDateTime", apiResponse.getLocalDateTime());
//        data.put("message", apiResponse.getMessage());
//        data.put("code", apiResponse.getCode());
//        response.getOutputStream().println(objectMapper.writeValueAsString(data));

    }
}