plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.main"
}
dependencies {

    implementation (project(":domain"))
    implementation (project(":feature:common"))
    implementation (project(":feature:streamer"))
    implementation (project(":feature:search"))
    implementation(project(":resource_common"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
}
