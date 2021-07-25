package org.melchor.admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.melchor.admin.domain.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    @Value("${domain-package}")
    private String domainPackage;

    @Value("${repository-package}")
    private String repositoryPackage;

    private final ApplicationContext applicationContext;

    private final ObjectMapper objectMapper;

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

    @SuppressWarnings("all")
    public void updateDomain(String domainName, Long id, String json) {
        try {
            JpaRepository repository = (JpaRepository) applicationContext.getBean(Class.forName(repositoryPackage + "." + domainName + "Repository"));

            Object domain = repository.getById(id);

            LinkedHashMap map = objectMapper.readValue(json, LinkedHashMap.class);

            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Object value = map.get(key);
                Field field = domain.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(domain, value);
                field.setAccessible(false);
            }
        } catch (JsonProcessingException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
