package com.example.checkpoint.feign;

import com.example.checkpoint.dto.microServicesDto.PackageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "package-service", url = "http://localhost:8080/api/company")
public interface PackageClient {
    @GetMapping("/{id}")
    ResponseEntity<PackageResponseDto> getById(@PathVariable Long id);
}
