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
public abstract class CheckpointMapper {

    @Autowired
    private LocationClient locationClient;

    @Autowired
    private PackageClient packageClient;

    @Mapping(target = "locationEntityId", source = "packageEntity")
    @Mapping(target = "packageEntityId", source = "packageEntity")
    public abstract CheckpointEntity toEntity(CheckpointRequestDto checkpointRequestDto);

    @Mapping(target = "id", source = "checkpointEntity.id")
    @Mapping(target = "locationDto", source = "locationEntityId", qualifiedByName = "checkpointLocation")
    @Mapping(target = "packageDto", source = "packageEntityId", qualifiedByName = "checkpointPackage")
    public abstract CheckpointResponseDto toDto(CheckpointEntity CheckpointEntity);

    @Mapping(target = "locationDto", source = "locationEntityId", qualifiedByName = "checkpointLocation")
    @Mapping(target = "packageDto", source = "packageEntityId", qualifiedByName = "checkpointPackage")
    public abstract List<CheckpointResponseDto> toDtoList(List<CheckpointEntity> CheckpointEntity);

    @Named("checkpointPackage")
    LocationReponseDto checkpointLocation(Long id) {
        return locationClient.getLocationById(String.valueOf(id)).getBody();
    }

    @Named("packageWithLocation")
    ResponseEntity<PackageResponseDto> checkpointPackage(Long id) {
        return ResponseEntity.ok(packageClient.getById(id).getBody());
    }

}
