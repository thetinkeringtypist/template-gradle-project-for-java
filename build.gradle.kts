plugins {
  id("java-base")
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(25))
  }
}

val jmhVersion = "1.37"
val junitVersion = "5.11.0"

subprojects {
  if (project.path.startsWith(":modules:")) {
    apply(plugin = "java")

    repositories {
      mavenCentral()
    }


    layout.buildDirectory.set(rootProject.layout.projectDirectory.dir("out/modules/${project.name}"))
    reporting.baseDirectory.set(rootProject.layout.projectDirectory.dir("out/reports/${project.name}"))

    extensions.configure<SourceSetContainer> {
      named("main") {
        java.srcDirs("src/java")
        resources.srcDirs("src/resources")
        java.destinationDirectory.set(rootProject.layout.projectDirectory.dir("out/modules/${project.name}/classes"))
        output.setResourcesDir(rootProject.layout.projectDirectory.file("out/modules/${project.name}/classes").asFile)
      }

      named("test") {
        java.srcDirs("test/java")
        resources.srcDirs("test/resources")
        java.destinationDirectory.set(rootProject.layout.projectDirectory.dir("out/modules/${project.name}/classes-test"))
        output.setResourcesDir(rootProject.layout.projectDirectory.file("out/modules/${project.name}/classes-test").asFile)
      }

      create("benchmarks") {
        java.srcDirs("benchmarks/java")
        resources.srcDirs("benchmarks/resources")
        java.destinationDirectory.set(rootProject.layout.projectDirectory.dir("out/modules/${project.name}/classes-benchmarks"))
        output.setResourcesDir(rootProject.layout.projectDirectory.file("out/modules/${project.name}/classes-benchmarks").asFile)
        compileClasspath += named("main").get().runtimeClasspath
        runtimeClasspath += named("main").get().runtimeClasspath
      }
    }

    tasks.withType<JavaCompile>().configureEach {
      options.generatedSourceOutputDirectory.set(rootProject.layout.projectDirectory.dir("out/modules/${project.name}/generated"))
      options.headerOutputDirectory.set(rootProject.layout.projectDirectory.dir("out/tmp/headers/${project.name}/${name}"))
    }

    tasks.withType<Jar>().configureEach {
      destinationDirectory.set(rootProject.layout.projectDirectory.dir("out/artifacts"))
      archiveFileName.set("${rootProject.name}-${project.name}.jar")
    }

    tasks.withType<Test>().configureEach {
      useJUnitPlatform()
    }

    dependencies {
      "testImplementation"("org.junit.jupiter:junit-jupiter:$junitVersion")
      "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")

      "benchmarksImplementation"("org.openjdk.jmh:jmh-core:$jmhVersion")
      "benchmarksAnnotationProcessor"("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
    }
  }
}