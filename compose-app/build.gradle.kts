plugins {
    id("hegunhee.android.compose.application")
}

android {
    namespace = "com.hegunhee.compose.app"

    defaultConfig {
        applicationId = "com.hegunhee.compose.app"
        versionCode = 1
        versionName = "1.0"
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
    implementation(project(":compose:jururu"))
    implementation(project(":compose:streamer"))
    implementation(project(":compose:search"))
    implementation(project(":compose:ui-component"))
    implementation(project(":resource_common"))
}