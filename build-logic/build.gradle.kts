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
      id = "com.thetinkeringtypist.gradle.create-module"
      implementationClass = "com.thetinkeringtypist.gradle.CreateModulePlugin"
    }
  }
}
