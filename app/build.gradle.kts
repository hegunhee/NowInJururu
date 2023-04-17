plugins {
    id ("hegunhee.android.application")
}

android{
    defaultConfig{
        applicationId = "com.hegunhee.routiner"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":feature:main"))
    implementation(project(":domain"))
    implementation(project(":data"))
}