package org.melchor.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Value("${domain-package}")
    private String domainPackage;

    public List<Object> findAll(String domainName) {
        return null;
    }
}
