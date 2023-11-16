plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.streamer"
}
dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(project(":resource_common"))
    implementation(project(":feature:searchkakao"))
}