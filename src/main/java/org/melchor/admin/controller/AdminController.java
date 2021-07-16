package org.melchor.admin.controller;

import lombok.RequiredArgsConstructor;
import org.melchor.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{domainName}")
    public ResponseEntity<?> getDomainList(@PathVariable String domainName) {

        List<Object> domainList = adminService.findAll(domainName);
        return ResponseEntity.ok(domainName);
    }

}
