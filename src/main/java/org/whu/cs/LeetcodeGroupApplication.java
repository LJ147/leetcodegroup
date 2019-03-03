package org.whu.cs;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.hibernate.LazyInitializationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whu.cs.bean.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class LeetcodeGroupApplication {


    public static void main(String[] args) {
        SpringApplication.run(LeetcodeGroupApplication.class, args);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadRequests(HttpServletRequest req, Exception e) throws Exception {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = e.getMessage();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ResponseBody
    @ExceptionHandler({NullPointerException.class, LazyInitializationException.class})
    public ResponseEntity<ErrorResponse> handleInteralError(HttpServletRequest request, Exception e) throws
            Exception {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = e.getMessage();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest req, Exception e) throws Exception {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = e.getMessage();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}

