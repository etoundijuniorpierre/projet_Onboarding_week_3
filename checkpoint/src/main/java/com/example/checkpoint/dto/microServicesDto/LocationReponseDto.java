package com.example.checkpoint.dto.microServicesDto;

import lombok.Data;

@Data
public class LocationReponseDto {
    private Long id;
    private String city;
    private String zone;
    private boolean checkpointAvailable;
}
