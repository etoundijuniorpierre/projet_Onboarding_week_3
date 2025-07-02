package com.example.checkpoint.controller;

import com.example.checkpoint.service.CheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Checkpoint")
public class CheckpointController {

    @Autowired
    private CheckpointService checkpointService;
}
