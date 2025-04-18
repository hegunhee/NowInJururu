plugins {
    id("hegunhee.android")
    id("hegunhee.hilt")
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

    implementation(libs.bundles.retrofit2)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.bundles.moshi)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.bundles.paging)
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}