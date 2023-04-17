plugins {
    id("hegunhee.android")
    id("hegunhee.android.hilt")
}

dependencies {

    implementation(project(":domain"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}