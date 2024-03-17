plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.main"
}
dependencies {

    implementation(project(":feature:streamer"))
    implementation(project(":feature:search"))
    implementation(project(":feature:jururu"))

    implementation(project(":core:designsystem"))
}
