plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies{
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.kotlinGradlePlugin)
    compileOnly(libs.hiltGradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidApplication"){
            id = "hegunhee.android.application"
            implementationClass = "com.hegunhee.plugins.AndroidApplicationPlugin"
        }
        register("Android"){
            id = "hegunhee.android"
            implementationClass = "com.hegunhee.plugins.AndroidPlugin"
        }
        register("AndroidFetaure"){
            id = "hegunhee.android.feature"
            implementationClass = "com.hegunhee.plugins.feature.AndroidFeaturePlugin"
        }
        register("AndroidHilt"){
            id = "hegunhee.android.hilt"
            implementationClass = "com.hegunhee.plugins.AndroidHiltPlugin"
        }
    }
}