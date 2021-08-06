package org.melchor.admin.controller;

import lombok.RequiredArgsConstructor;
import org.melchor.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{entityName}")
    public ResponseEntity<?> getDomainList(@PathVariable String entityName) {

        List<Object> entities = adminService.findAll(entityName);
        return ResponseEntity.ok(entities);
    }

}
