pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // AutoBus is published through JitPack.
        maven("https://jitpack.io")
    }
}

rootProject.name = "MiniWeather"
include(":app")
