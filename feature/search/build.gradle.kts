plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.search"
}
dependencies {

    implementation (project(":domain"))
    implementation (project(":feature:common"))
}
