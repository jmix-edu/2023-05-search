package com.company.jmixpm.app;

import io.jmix.search.index.mapping.analysis.AnalysisConfigurationContext;
import io.jmix.search.index.mapping.analysis.IndexAnalysisConfigurer;
import org.springframework.stereotype.Component;

@Component
public class MyAnalysisConfigurer implements IndexAnalysisConfigurer {

    @Override
    public void configure(AnalysisConfigurationContext context) {
        context.defineAnalyzer("custom_analyser")
                .createCustom()
                .withTokenizer("custom_tokenizer_with_native_config")
                .withCharacterFilters("html_strip")
                .withTokenFilters("stop");

        context.defineTokenizer("custom_tokenizer_with_native_config")
                .withNativeConfiguration(
                        "{" +
                                "\"type\": \"standard\"," +
                                "\"max_token_length\": 10" +
                                "}"
                );
    }
}
