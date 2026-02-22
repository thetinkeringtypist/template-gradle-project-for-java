package com.thetinkeringtypist.gradle;

import java.util.Objects;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CreateModulePlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    Object property = project.findProperty("module");
    if (Objects.isNull(property)) {
      return;
    }

    project.getTasks().register("createModule", CreateModuleTask.class,
        task -> task.getModuleName().set(property.toString()));
  }
}
