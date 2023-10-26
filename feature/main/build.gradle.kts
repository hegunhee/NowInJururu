plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.main"
}
dependencies {

    implementation(project(":core:domain"))
    implementation(project(":feature:streamer"))
    implementation(project(":feature:search"))
    implementation(project(":feature:jururu"))
    implementation(project(":resource_common"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
}
