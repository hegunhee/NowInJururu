plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.common"
}

dependencies {

    implementation(libs.lottie)
    implementation(project(":resource_common"))
}