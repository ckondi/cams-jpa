package com.cams.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusResource {


    @GetMapping("/status")
    public Map getStatus(){
        Map<String, String> status = new HashMap<>();
        status.put("application_status","GREEN");
        return Collections.unmodifiableMap(status);
    }
}
