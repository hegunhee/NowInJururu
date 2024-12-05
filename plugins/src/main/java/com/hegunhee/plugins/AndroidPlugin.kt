package com.hegunhee.plugins

import com.android.build.gradle.LibraryExtension
import com.hegunhee.plugins.setup.libs
import com.hegunhee.plugins.setup.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                setupAndroid()
                buildTypes {
                    getByName("release"){
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
            dependencies {
                add("testImplementation",libs.findLibrary("junit").get())
                add("androidTestImplementation",libs.findLibrary("test-core").get())
                add("androidTestImplementation",libs.findLibrary("ext-junit").get())
                add("androidTestImplementation",libs.findLibrary("espresso-core").get())
                add("testImplementation",libs.findLibrary("mockito-kotlin").get())
            }
        }
    }
}
