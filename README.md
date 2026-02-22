# Gradle Template Project (For Java)

---

This repository is for setting up my multi-module Java projects with Gradle.


### Some Notes About This Template

The root project of this template is _not_ intended to have source code.
All source code should be in the sub-projects (ie. modules) in the `modules` directory.

Compiled and generated output of all modules ends up in the same top-level directory `out`.
I like all of my compiled and generated output to be in the same directory. Plus, it makes it
easier to leverage a ramdisk for fewer writes to disk during development.

The folder structure for this template is as follows:

```text
<root-project>
  ├─ gradle
  │  └─ wrapper
  │     ├─ gradle-wrapper.jar
  │     └─ gradle-wrapper.properties
  ├─ modules
  │  └─ example
  │     ├─ benchmarks
  │     │  ├─ java/
  │     │  └─ resources/
  │     ├─ sr/
  │     │  ├─ java/
  │     │  └─ resources/
  │     └─ test
  │        ├─ java/
  │        └─ resources/
  ├─ out/  <-- Compiled and generated output of all modules here       
  ├─ build.gradle.kts
  ├─ gradlew      
  ├─ gradlew.bat
  ├─ README.md  
  └─ settings.gradle.kts
```



### How to Use This Template

First, clone the project:

```bash
# Define your project name
project="my-project"

# Clone the template
git clone https://github.com/thetinkeringtypist/template-gradle-project-for-java.git "$project"

cd "$project"

# Set the root-project name
sed -i -e "s/${PROJECT_NAME}/<project-name>/g" settings.gradle.kts

# Remove the commit history for the template project
rm -rf .git

# Initialize the git repository for the new project
git init
```

And you're ready to go!
