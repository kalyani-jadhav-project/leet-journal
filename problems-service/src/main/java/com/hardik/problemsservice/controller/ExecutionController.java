package com.hardik.problemsservice.controller;

import com.hardik.problemsservice.service.LocalExecutorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutionController {

    private final LocalExecutorService localExecutorService;

    public ExecutionController(LocalExecutorService localExecutorService) {
        this.localExecutorService = localExecutorService;
    }

    @GetMapping("/run")
    public String run() {
        return localExecutorService.executeJava();
    }
}
