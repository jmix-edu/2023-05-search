package com.company.jmixpm.screen.customsearch;

import com.company.jmixpm.entity.SearchResultItem;
import io.jmix.core.Metadata;
import io.jmix.search.searching.EntitySearcher;
import io.jmix.search.searching.FieldHit;
import io.jmix.search.searching.SearchContext;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UiController("CustomSearchScreen")
@UiDescriptor("custom-search-screen.xml")
public class CustomSearchScreen extends Screen {

    @Autowired
    private EntitySearcher entitySearcher;
    @Autowired
    private CollectionContainer<SearchResultItem> searchResultItemsDc;
    @Autowired
    private Metadata metadata;

    @Subscribe("searchField")
    public void onSearchFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String value = event.getValue();

        if (Strings.isNullOrEmpty(value)) {
            searchResultItemsDc.getMutableItems().clear();
            return;
        }

        SearchContext searchContext = new SearchContext(value).setSize(100);

        List<SearchResultItem> tableItems = entitySearcher.search(searchContext)
                .getAllEntries()
                .stream()
                .map(resultEntity -> {
                    String fields = resultEntity.getFieldHits()
                            .stream()
                            .map(FieldHit::getFieldName)
                            .collect(Collectors.joining(","));

                    return createSearchResultItem(
                            resultEntity.getEntityName(),
                            resultEntity.getInstanceName(),
                            resultEntity.getDocId(),
                            fields
                    );
                })
                .collect(Collectors.toList());

        searchResultItemsDc.setItems(tableItems);
    }

    private SearchResultItem createSearchResultItem(String entityName,
                                                    String instanceName,
                                                    String instanceId,
                                                    String fields) {
        SearchResultItem searchResultItem = metadata.create(SearchResultItem.class);

        searchResultItem.setEntityName(entityName);
        searchResultItem.setInstanceName(instanceName);
        searchResultItem.setInstanceId(instanceId);
        searchResultItem.setFields(fields);

        return searchResultItem;
    }
}