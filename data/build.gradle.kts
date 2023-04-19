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

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)
}