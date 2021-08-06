package org.melchor.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    public List<Object> findAll(String entityName) {
        return null;
    }
}
