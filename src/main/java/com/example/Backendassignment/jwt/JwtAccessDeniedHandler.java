package com.example.Backendassignment.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final PropertiesErrorConfig propertiesErrorConfig;

    @Autowired
    public JwtAccessDeniedHandler(PropertiesErrorConfig propertiesErrorConfig) {
        this.propertiesErrorConfig = propertiesErrorConfig;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {
        ResponseModel response = new ResponseModel(ResponseTypeEnum.ACCESS_DENIED, propertiesErrorConfig.getConfigValue("access_denied"));
        httpServletResponse.setContentType("application/json");
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
    }



}