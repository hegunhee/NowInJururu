plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.streamer"
}

dependencies{
    implementation(project(":core:domain"))

    implementation(project(":compose:ui-component"))

    implementation(project(":resource_common"))

    implementation(libs.compose.bottomsheetdialog)
}