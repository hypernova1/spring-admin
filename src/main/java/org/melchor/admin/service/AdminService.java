package org.melchor.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    @Value("${domain-package}")
    private String domainPackage;

    @Value("${repository-package}")
    private String repositoryPackage;

    private final ApplicationContext applicationContext;

    @SuppressWarnings("all")
    public List findAll(String domainName, int page, int size) {
        try {
            JpaRepository repository = (JpaRepository) applicationContext.getBean(Class.forName(repositoryPackage + "." + domainName + "Repository"));

            Pageable pageable = PageRequest.of(page - 1, size);

            Page result = repository.findAll(pageable);

            return result.getContent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("server error");
    }

}
