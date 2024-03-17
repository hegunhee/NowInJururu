plugins {
    id("hegunhee.android.feature")
}
android {
    namespace = "com.hegunhee.feature.streamer"
}
dependencies {

    implementation(project(":core:designsystem"))
    
    implementation(project(":feature:searchkakao"))
}