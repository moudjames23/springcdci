package com.moudjames23.sms.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class HttpResponse {

   public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data)
   {
       Map<String, Object> map = new HashMap<>();
       map.put("code", status);
       map.put("message", message);
       map.put("data", data);

       return new ResponseEntity<Object>(map,status);
   }
}
