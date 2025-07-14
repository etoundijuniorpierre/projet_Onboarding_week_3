package com.example.checkpoint.dto.microServicesDto;


import lombok.Data;

@Data
public class PackageResponseDto {
    private Long id;
    private String description;
    private Integer weight;
    private boolean fragile;
    private String status;
}
