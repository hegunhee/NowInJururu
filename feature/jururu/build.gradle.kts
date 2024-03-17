plugins {
    id("hegunhee.android.feature")
}

android {
    namespace = "com.hegunhee.nowinjururu.feature.jururu"

}

dependencies {

    implementation(project(":core:designsystem"))

    implementation(project(":feature:searchkakao"))
}