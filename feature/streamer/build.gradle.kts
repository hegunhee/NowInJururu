plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.streamer"
}
dependencies {

    implementation (project(":domain"))
    implementation(project(":resource_common"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
}