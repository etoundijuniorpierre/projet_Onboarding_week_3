package com.example.checkpoint.controller;

import com.example.checkpoint.dto.CheckpointRequestDto;
import com.example.checkpoint.dto.CheckpointResponseDto;
import com.example.checkpoint.entity.CheckpointEntity;
import com.example.checkpoint.mapper.CheckpointMapper;
import com.example.checkpoint.service.CheckpointService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Checkpoint")
public class CheckpointController {


    private final CheckpointService checkpointService;


    private final CheckpointMapper checkpointMapper;

    public CheckpointController(CheckpointService checkpointService, CheckpointMapper checkpointMapper) {
        this.checkpointService = checkpointService;
        this.checkpointMapper = checkpointMapper;
    }

    @PostMapping
    public ResponseEntity<CheckpointResponseDto> createCheckpoint(@RequestBody CheckpointRequestDto checkpointRequestDto) {
        CheckpointEntity checkpointEntity = checkpointMapper.toEntity(checkpointRequestDto);
        CheckpointEntity createdCheckpoint = checkpointService.createCheckpoint(checkpointEntity);
        CheckpointResponseDto responseDto = checkpointMapper.toDto(createdCheckpoint);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CheckpointResponseDto>> getAllCheckpoints() {
        List<CheckpointEntity> checkpointEntities = checkpointService.getAllCheckpoints();
        return ResponseEntity.ok(checkpointMapper.toDtoList(checkpointEntities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckpointResponseDto> getCheckpointById(@PathVariable Long id) {
        CheckpointEntity checkpointEntity = checkpointService.getCheckpointById(id);
        return ResponseEntity.ok(checkpointMapper.toDto(checkpointEntity));
    }
}
