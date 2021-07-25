package org.melchor.admin.controller;

import lombok.RequiredArgsConstructor;
import org.melchor.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{domainName}")
    public ResponseEntity<?> getDomainList(@PathVariable String domainName,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        List domainList = adminService.findAll(domainName, page, size);
        return ResponseEntity.ok(domainList);
    }

}
