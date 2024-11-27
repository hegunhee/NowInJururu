plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("hegunhee.hilt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.coroutine.core)
    implementation(libs.paging.common)
}
