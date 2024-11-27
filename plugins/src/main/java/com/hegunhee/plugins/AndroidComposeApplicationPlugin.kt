package com.hegunhee.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.hegunhee.plugins.feature.configureAndroidCompose
import com.hegunhee.plugins.setup.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("hegunhee.hilt")
            }
            extensions.configure<ApplicationExtension> {
                setupAndroid()
                configureAndroidCompose(this)

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation",libs.findLibrary("core-ktx").get())
                add("implementation",platform(libs.findLibrary("kotlin-bom").get()))
                add("implementation",libs.findLibrary("lifecycle-lifecycleScope").get())
                add("implementation",libs.findLibrary("activity-compose").get())
                add("implementation",platform(libs.findLibrary("compose-bom").get()))
                add("implementation",libs.findBundle("compose-ui").get())
                add("implementation",libs.findLibrary("navigation-compose").get())
                add("implementation",libs.findLibrary("hilt-viewmodel").get())
                add("implementation",libs.findLibrary("junit").get())
                add("androidTestImplementation",libs.findLibrary("ext-junit").get())
                add("androidTestImplementation",libs.findLibrary("espresso-core").get())

                add("androidTestImplementation",platform(libs.findLibrary("compose-bom").get()))
                add("androidTestImplementation",libs.findLibrary("compose-ui-test-junit").get())
                add("debugImplementation",libs.findLibrary("compose-ui-tooling").get())
                add("debugImplementation",libs.findLibrary("compose-ui-test-manifest").get())

                add("implementation",libs.findBundle("retrofit2").get())
                add("implementation",libs.findBundle("moshi").get())
                add("implementation",libs.findLibrary("room-runtime").get())
                add("kapt",libs.findLibrary("room-compiler").get())
                add("implementation",libs.findLibrary("room-ktx").get())

                add("implementation",libs.findLibrary("coil").get())
            }
        }
    }
}
