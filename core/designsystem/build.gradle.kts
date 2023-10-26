plugins {
    id("hegunhee.android.feature")
}

android {
    namespace = "com.hegunhee.nowinjururu.core.designsystem"
}

dependencies {

    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))

    implementation(project(":resource_common"))

    implementation(libs.lottie)
}