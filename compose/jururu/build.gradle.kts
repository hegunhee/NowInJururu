plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.jururu"
}

dependencies{
    implementation(project(":core:domain"))

    implementation(project(":compose:ui-component"))

    implementation(project(":resource_common"))
}