plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.search"
}

dependencies{
    implementation(project(":compose:ui-component"))
    implementation(project(":resource_common"))
}