plugins {
    id("hegunhee.android")
    id("hegunhee.hilt")
}

android {
    namespace = "com.hegunhee.nowinjururu.core.navigation"
}
dependencies {
    implementation(project(":resource_common"))
}