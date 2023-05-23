package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import io.jmix.search.index.IndexSettingsConfigurationContext;
import io.jmix.search.index.IndexSettingsConfigurer;
import org.springframework.stereotype.Component;

@Component("jmixpm_AppIndexSettingsConfigurer")
public class AppIndexSettingsConfigurer implements IndexSettingsConfigurer {

    @Override
    public void configure(IndexSettingsConfigurationContext context) {
        context.getCommonSettingsBuilder()
                .put("index.max_terms_count", 10)
                .put("index.max_result_window", 15000);

        context.getEntitySettingsBuilder(Project.class)
                .put("index.max_terms_count", 20);
    }
}
