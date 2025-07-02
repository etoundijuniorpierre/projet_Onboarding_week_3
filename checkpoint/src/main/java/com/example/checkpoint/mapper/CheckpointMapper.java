package com.example.checkpoint.mapper;

import com.example.checkpoint.dto.CheckpointRequestDto;
import com.example.checkpoint.dto.CheckpointResponseDto;
import com.example.checkpoint.dto.microServicesDto.LocationReponseDto;
import com.example.checkpoint.dto.microServicesDto.PackageResponseDto;
import com.example.checkpoint.entity.CheckpointEntity;
import com.example.checkpoint.feign.LocationClient;
import com.example.checkpoint.feign.PackageClient;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CheckpointMapper {
    
    @Mapping(target = "locationEntityId", source = "packageEntity")
    @Mapping(target = "packageEntityId", source = "packageEntity")
    CheckpointEntity toEntity(CheckpointRequestDto checkpointRequestDto);

    @Mapping(target = "id", source = "checkpointEntity.id")
    @Mapping(target = "locationDto", source = "locationEntityId", qualifiedByName = "checkpointLocation")
    @Mapping(target = "packageDto", source = "packageEntityId", qualifiedByName = "checkpointPackage")
    CheckpointResponseDto toDto(CheckpointEntity CheckpointEntity);

    @Mapping(target = "locationDto", source = "locationEntityId", qualifiedByName = "checkpointLocation")
    @Mapping(target = "packageDto", source = "packageEntityId", qualifiedByName = "checkpointPackage")
    List<CheckpointResponseDto> toDtoList(List<CheckpointEntity> CheckpointEntity);

    @Named("packageWithLocation")
    default LocationReponseDto checkpointLocation(Long id) {
        return locationClient().getLocationById(String.valueOf(id)).getBody();
    }

    @Named("packageWithLocation")
    default ResponseEntity<PackageResponseDto> checkpointPackage(Long id) {
        return ResponseEntity.ok(packageClient().getById(id).getBody());
    }

    LocationClient locationClient();


    PackageClient packageClient();
}
