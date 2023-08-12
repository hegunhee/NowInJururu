plugins {
    id("hegunhee.android.compose.application")
}

android {
    namespace = "com.hegunhee.compose.app"

    defaultConfig {
        applicationId = "com.hegunhee.compose.app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":compose:search"))
    implementation(project(":compose:streamer"))
}