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

    @GetMapping("/{entityName}")
    public ResponseEntity<?> getDomainList(
            @PathVariable String entityName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<Object> entities = adminService.findAll(entityName, page, size);
        return ResponseEntity.ok(entities);
    }

}
