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
    }
}