rootProject.name = "${PROJECT_NAME}"

file("modules").listFiles()?.filter { it.isDirectory }?.forEach {
    include("modules:${it.name}")
}

