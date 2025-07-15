package com.example.checkpoint.feign;

import com.example.checkpoint.dto.microServicesDto.PackageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "package-service", url = "http://localhost:8083/api/package")
public interface PackageClient {
    @GetMapping("/{id}")
    ResponseEntity<PackageResponseDto> getById(@PathVariable Long id);

}
