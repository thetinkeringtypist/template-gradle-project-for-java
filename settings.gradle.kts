pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

includeBuild("build-logic")

rootProject.name = "${PROJECT_NAME}"

file("modules").listFiles()?.filter { it.isDirectory }?.forEach {
  include("modules:${it.name}")
}
