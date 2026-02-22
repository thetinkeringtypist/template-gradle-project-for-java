package com.thetinkeringtypist.gradle;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

public abstract class CreateModuleTask extends DefaultTask {

  private static final List<String> DIRS = List.of(
      "src/java",
      "src/resources",
      "test/java",
      "test/resources",
      "benchmarks/java",
      "benchmarks/resources"
  );

  @Input
  @Optional
  public abstract Property<String> getModuleName();

  @TaskAction
  public void create() {
    String name = getModuleName().get();
    if (name.isBlank()) {
      System.err.println("No module names provided. Usage: ./gradlew createModule -Pmodule=<module-name>");
      return;
    }

    File moduleDir = getProject().file("modules/" + name);
    moduleDir.mkdirs();

    for (String dir : DIRS) {
      File folder = new File(moduleDir, dir);
      folder.mkdirs();

      try {
        new File(folder, ".gitkeep").createNewFile();
      } catch (IOException e) {
        System.err.println("Failed to create .gitkeep in " + folder.getPath());
      }
    }

    System.out.println("Module " + name + " created successfully.");
  }
}
