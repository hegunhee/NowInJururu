plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.searchkakao"
}

dependencies{
    implementation(project(":compose:ui-component"))

    implementation(libs.jsoup)
}