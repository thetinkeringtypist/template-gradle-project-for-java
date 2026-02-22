package com.thetinkeringtypist.gradle;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class CreateModuleTask extends DefaultTask {

    @Input
    @Optional
    public abstract Property<String> getModuleName();

    @TaskAction
    public void create() {
        String name = getModuleName().getOrNull();
        if (name == null) {
            System.out.println("No module name provided. Usage: ./gradlew createModule \"module-name\"");
            return;
        }

        File moduleDir = getProject().file("modules/" + name);

        if (moduleDir.exists()) {
            System.out.println("Module " + name + " already exists.");
            return;
        }

        List<String> dirs = Arrays.asList(
                "src/java",
                "src/resources",
                "test/java",
                "test/resources",
                "benchmarks/java",
                "benchmarks/resources"
        );

        for (String dir : dirs) {
            File folder = new File(moduleDir, dir);
            if (folder.mkdirs()) {
                System.out.println("Created " + folder.getPath());
                try {
                    new File(folder, ".gitkeep").createNewFile();
                } catch (IOException e) {
                    System.err.println("Failed to create .gitkeep in " + folder.getPath());
                }
            }
        }

        System.out.println("Module " + name + " created successfully.");
    }
}
