pluginManagement {
    includeBuild("plugins")
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
    }
}
rootProject.name = "NowInJururu"
include(":app")
include(":feature:main")
include(":domain")
include(":data")
include(":feature:common")
include(":feature:streamer")
include(":feature:search")
include(":compose-app")
include(":compose:search")
include(":compose:streamer")
include(":compose:jururu")
include(":compose:ui-component")
include(":resource_common")
