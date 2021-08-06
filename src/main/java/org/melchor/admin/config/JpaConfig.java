package org.melchor.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig {

    private final EntityManager entityManager;

    @Bean
    public Map<String, String> entityMap() {
        Set<EntityType<?>> entityTypeSet = entityManager.getMetamodel().getEntities();

        Map<String, String> entityMap = new LinkedHashMap<>();

        for (EntityType<?> entityType: entityTypeSet) {
            String entityName = entityType.getName();
            String packageName = entityType.getJavaType().getPackageName();
            entityMap.put(entityName.toLowerCase(), packageName + "." + entityName);
        }

        return entityMap;
    }

}
