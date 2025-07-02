package com.example.checkpoint.dto;


import com.example.checkpoint.dto.microServicesDto.LocationReponseDto;
import com.example.checkpoint.dto.microServicesDto.PackageResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckpointResponseDto {
    private Long id;
    private LocalDateTime checkpointDate;
    private PackageResponseDto packageDto;
    private LocationReponseDto locationDto;

}
