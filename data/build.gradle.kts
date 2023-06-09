import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("hegunhee.android")
    id("hegunhee.android.hilt")
}

val properties = gradleLocalProperties(rootDir)
val clientId : String = properties.getProperty("clientId")
val clientSecret : String = properties.getProperty("clientSecret")
android{
    buildTypes{
        getByName("debug"){
            buildConfigField("String", "clientId", clientId)
            buildConfigField("String", "clientSecret", clientSecret)
        }
    }
}

dependencies {

    implementation(project(":domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
}