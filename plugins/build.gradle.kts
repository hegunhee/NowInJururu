plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
        register("androidComposeApplication"){
            id = "hegunhee.android.compose.application"
            implementationClass = "com.hegunhee.plugins.AndroidComposeApplicationPlugin"
        }
        register("Android"){
            id = "hegunhee.android"
            implementationClass = "com.hegunhee.plugins.AndroidPlugin"
        }
        register("AndroidFetaure"){
            id = "hegunhee.android.feature"
            implementationClass = "com.hegunhee.plugins.feature.AndroidFeaturePlugin"
        }
        register("AndroidComposeFeature"){
            id = "hegunhee.android.compose"
            implementationClass = "com.hegunhee.plugins.feature.AndroidComposePlugin"
        }
        register("Hilt"){
            id = "hegunhee.hilt"
            implementationClass = "com.hegunhee.plugins.HiltPlugin"
        }
    }
}
