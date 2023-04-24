plugins {
    id("hegunhee.android.feature")
}
dependencies {

    implementation (project(":domain"))
    implementation (project(":feature:common"))
    implementation (project(":feature:streamer"))
}
