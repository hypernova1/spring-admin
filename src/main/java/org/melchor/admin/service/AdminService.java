package org.melchor.admin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final Map<String, String> entityMap;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("all")
    public List findAll(String entityName, int page, int size) {
        JpaRepository repository = getJpaRepository(entityName);

        Pageable pageable = PageRequest.of(page - 1, size);

        Page result = repository.findAll(pageable);

        return result.getContent();
    }

    @SuppressWarnings("all")
    public void updateDomain(String domainName, Long id, String json) {
        try {
            JpaRepository repository = getJpaRepository(domainName);

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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("all")
    private JpaRepository getJpaRepository(String entityName) {

        return null;
    }
}
