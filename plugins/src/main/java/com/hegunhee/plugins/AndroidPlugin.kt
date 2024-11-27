package com.hegunhee.plugins

import com.android.build.gradle.LibraryExtension
import com.hegunhee.plugins.setup.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

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
            }
        }
    }
}
