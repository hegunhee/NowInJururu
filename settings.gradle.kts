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
include(":core:domain")
include(":core:data")

include(":core:designsystem")

include(":feature:main")
include(":feature:jururu")
include(":feature:streamer")
include(":feature:search")
include(":feature:searchkakao")

include(":compose-app")

include(":compose:search")
include(":compose:streamer")
include(":compose:jururu")
include(":compose:ui-component")

include(":resource_common")
include(":core:navigation")