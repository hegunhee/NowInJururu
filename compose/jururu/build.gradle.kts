plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.jururu"
}

dependencies{
    implementation(project(":domain"))
    implementation(project(":compose:ui-component"))
    implementation(project(":resource_common"))
}