# Gradle Template Project (For Java)

---

This repository is for setting up my multi-module Java projects with Gradle.


### Some Notes About This Template

---

The root project of this template is _not_ intended to have source code.
All source code should be in the sub-projects (ie. modules) in the `modules` directory.
Modules gain the following dependencies by default:
- JUnit 5
- Java Microbenchmark Harness (JMH)

The entire modules directory is captured in `settings.gradle.kts`, so no modules need to
be explicitly added to the `include` list.

Compiled output (and generated code) of all modules ends up in the same top-level `out` directory.
I prefer all module output to be in the same directory for navigational ease. Plus, it makes it
easier to symlink the folder to a ramdisk to save on disk wear.

All custom Gradle plugins are defined in the `build-logic` directory. Since it's only for project and build management,
it is separate from the rest of the source code modules. 

### Folder Structure

---

```text
<root-project>
  ├─ .githooks/    <-- Git hooks
  ├─ build-logic/  <-- Custom Gradle plugins
  ├─ gradle
  │  └─ wrapper
  │     ├─ gradle-wrapper.jar
  │     └─ gradle-wrapper.properties
  ├─ modules       <-- Project modules
  │  └─ example
  │     ├─ benchmarks
  │     │  ├─ java/
  │     │  └─ resources/
  │     ├─ src/
  │     │  ├─ java/
  │     │  └─ resources/
  │     └─ test
  │        ├─ java/
  │        └─ resources/
  ├─ out/  <-- Compiled and generated output of all modules       
  ├─ build.gradle.kts
  ├─ gradlew      
  ├─ gradlew.bat
  ├─ README.md  
  └─ settings.gradle.kts
```

### How to Use This Template

---

Clone the project template and initialize a new git repository

```bash
# Define your project name
project="my-project"

# Clone the template
git clone https://github.com/thetinkeringtypist/template-gradle-project-for-java.git "$project"

cd "$project"

# Set the root-project name
sed -i "s/${PROJECT_NAME}/<project-name>/g" settings.gradle.kts

# Remove the commit history for the template project
rm -rf .git .githooks

# Initialize the git repository for the new project
git init
```

### Creating A New Module

---

To create a new module (and the relevant directories), run the following command.

```bash
./gradlew createModule -Pmodule="<module-name>"
```

And you're ready to go!
