plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.streamer"
}

dependencies{
    implementation(project(":compose:ui-component"))

    implementation(libs.compose.bottomsheetdialog)
}