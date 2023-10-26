plugins {
    id ("hegunhee.android.application")
}

android{
    namespace = "com.hegunhee.nowinjururu"
    defaultConfig{
        applicationId = "com.hegunhee.nowinjururu"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(project(":feature:main"))
}