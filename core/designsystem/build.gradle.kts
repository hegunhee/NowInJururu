plugins {
    id("hegunhee.android.feature")
}

android {
    namespace = "com.hegunhee.nowinjururu.core.designsystem"
}

dependencies {

    implementation(libs.lottie)
    implementation(project(":resource_common"))
    implementation(project(":domain"))
    implementation(project(":core:navigation"))
}