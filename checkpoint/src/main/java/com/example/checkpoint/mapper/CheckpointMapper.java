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

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class  CheckpointMapper {

    @Autowired
    private LocationClient locationClient;

    @Autowired
    private PackageClient packageClient;

    @Mapping(target = "locationEntityId", source = "locationId")
    @Mapping(target = "packageEntityId", source = "packageId")
    public abstract CheckpointEntity toEntity(CheckpointRequestDto checkpointRequestDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "locationDto", source = "locationEntityId", qualifiedByName = "checkpointLocation")
    @Mapping(target = "packageDto", source = "packageEntityId", qualifiedByName = "checkpointPackage")
    public abstract CheckpointResponseDto toDto(CheckpointEntity checkpointEntity);

    public abstract List<CheckpointResponseDto> toDtoList(List<CheckpointEntity> checkpointEntities);

    @Named("checkpointLocation")
    public LocationReponseDto checkpointLocation(Long id) {
        if (id == null) {
            return null;
        }
        LocationReponseDto dto = locationClient.getLocationById(String.valueOf(id)).getBody();
        if (dto == null) {
            return null;
        }
        return dto;
    }

    @Named("checkpointPackage")
    public PackageResponseDto checkpointPackage(Long id) {
        if (id == null) {
            return null;
        }
        PackageResponseDto dto = packageClient.getById(id).getBody();
        if (dto == null) {
            return null;
        }
        return dto;
    }
}

