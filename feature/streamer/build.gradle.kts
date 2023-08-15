plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.streamer"
}
dependencies {

    implementation (project(":domain"))
    implementation (project(":feature:common"))
    implementation(project(":resource_common"))
}