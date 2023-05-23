package com.company.jmixpm.search;

import com.company.jmixpm.entity.Project;
import io.jmix.search.index.annotation.AutoMappedField;
import io.jmix.search.index.annotation.JmixEntitySearchIndex;

@JmixEntitySearchIndex(entity = Project.class)
public interface ProjectIndexDefinition {

    @AutoMappedField(includeProperties = {"name", "manager.username", "tasks.name"},
            analyzer = "custom_analyser")
//    @AutoMappedField(includeProperties = {"attachment"}, analyzer = "russian")
    void mapping();


//    @IndexablePredicate
//    default Predicate<Project> predicate(DataManager dataManager) {
//        return project -> {
//            Id<Project> id = Id.of(project);
//            Project proj = dataManager.load(id).fetchPlanProperties("attachment").one();
//            return proj.getAttachment() != null;
//        };
//    }
}
