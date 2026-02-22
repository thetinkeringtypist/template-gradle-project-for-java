plugins {
  id("java-gradle-plugin")
}

repositories {
  gradlePluginPortal()
}

layout.buildDirectory.set(file("../out/build-logic"))

gradlePlugin {
  plugins {
    create("createModulePlugin") {
      id = "template-gradle-project-for-java.create-module"
      implementationClass = "template_gradle_project_for_java.CreateModulePlugin"
    }
  }
}
