package com.company.jmixpm.screen.project;

import com.company.jmixpm.entity.Project;
import io.jmix.core.Id;
import io.jmix.search.index.EntityIndexer;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {
    @Autowired
    private GroupTable<Project> projectsTable;
    @Autowired
    private EntityIndexer entityIndexer;

    @Subscribe("projectsTable.index")
    public void onProjectsTableIndex(Action.ActionPerformedEvent event) {
        List<Id<?>> ids = projectsTable.getSelected()
                .stream()
                .map(Id::of)
                .collect(Collectors.toList());

        entityIndexer.indexCollectionByEntityIds(ids);
    }

}