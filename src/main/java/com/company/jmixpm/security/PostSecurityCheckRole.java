package com.company.jmixpm.security;

import com.company.jmixpm.entity.Project;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

import javax.annotation.Nonnull;

@Nonnull
@RowLevelRole(name = "PostSecurityCheckRole", code = "post-security-check-role")
public interface PostSecurityCheckRole {

    @JpqlRowLevelPolicy(
            entityClass = Project.class,
            where = "{E}.manager.id=:current_user_id"
    )
    void check();
}