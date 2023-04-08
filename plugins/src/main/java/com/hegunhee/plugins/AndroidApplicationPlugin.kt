package com.hegunhee.plugins

import com.hegunhee.plugins.setup.androidApplication
import com.hegunhee.plugins.setup.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            androidApplication {
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