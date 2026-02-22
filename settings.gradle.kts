pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

includeBuild("build-logic")

rootProject.name = "template-gradle-project-for-java"

file("modules").listFiles()?.filter { it.isDirectory }?.forEach {
  include("modules:${it.name}")
}
