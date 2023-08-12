plugins {
    id("hegunhee.android.compose")
}

android {
    namespace = "com.hegunhee.compose.jururu"
}

dependencies{
    implementation(project(":compose:ui-component"))
}