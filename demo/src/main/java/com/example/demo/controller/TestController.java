package com.example.demo.controller;

import com.example.demo.service.BackendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/controller")
public class TestController {

    private BackendService backendService;

    public TestController(BackendService backendCService) {
        this.backendService = backendCService;
    }

    @GetMapping("/success")
    public String success() {
        return backendService.success();
    }

    @GetMapping("/failure")
    public String failure() throws Exception {
        return backendService.failure();
    }

    @GetMapping("/failureWithFallback")
    public String failureWithFallback() throws Exception {
        return backendService.failureWithFallback();
    }
}
