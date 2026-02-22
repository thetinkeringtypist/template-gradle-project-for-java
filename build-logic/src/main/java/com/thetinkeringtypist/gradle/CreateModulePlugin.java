package com.thetinkeringtypist.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.invocation.Gradle;
import java.util.ArrayList;
import java.util.List;

public class CreateModulePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        Gradle gradle = project.getGradle();
        List<String> taskNames = new ArrayList<>(gradle.getStartParameter().getTaskNames());
        int createModuleIndex = taskNames.indexOf("createModule");

        final String[] moduleNameFromArg = {null};
        Object moduleNameProp = project.findProperty("moduleName");
        if (moduleNameProp != null) {
            moduleNameFromArg[0] = moduleNameProp.toString();
        }

        if (createModuleIndex != -1 && createModuleIndex + 1 < taskNames.size()) {
            String nextArg = taskNames.get(createModuleIndex + 1);
            // If the next argument doesn't look like a task, assume it's the module name
            if (!nextArg.startsWith("-")) {
                moduleNameFromArg[0] = nextArg;
                // We need to tell Gradle to ignore this "task"
                List<String> newTaskNames = new ArrayList<>();
                for (int i = 0; i < taskNames.size(); i++) {
                    if (i != createModuleIndex + 1) {
                        newTaskNames.add(taskNames.get(i));
                    }
                }
                gradle.getStartParameter().setTaskNames(newTaskNames);
            }
        }

        project.getTasks().register("createModule", CreateModuleTask.class, task -> {
            if (moduleNameFromArg[0] != null) {
                task.getModuleName().set(moduleNameFromArg[0]);
            }
        });
    }
}
