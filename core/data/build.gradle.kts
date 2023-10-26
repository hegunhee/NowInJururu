plugins {
    id("hegunhee.android")
    id("hegunhee.android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android{
    namespace = "com.hegunhee.data"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.moshi)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.bundles.paging)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}