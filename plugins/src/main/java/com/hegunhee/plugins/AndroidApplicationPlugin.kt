package com.hegunhee.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.hegunhee.plugins.setup.setupAndroid
import com.hegunhee.plugins.setup.setupViewDataBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("hegunhee.hilt")
            }
            extensions.configure<ApplicationExtension> {
                setupAndroid()
                setupViewDataBinding()

                buildTypes {
                    getByName("release"){
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation",libs.findLibrary("core-ktx").get())
                add("implementation",libs.findLibrary("appcompat").get())
                add("implementation",libs.findLibrary("material").get())
                add("implementation",libs.findLibrary("constraint-layout").get())
                add("implementation",libs.findLibrary("junit").get())
                add("androidTestImplementation",libs.findLibrary("ext-junit").get())
                add("androidTestImplementation",libs.findLibrary("espresso-core").get())

                add("implementation",libs.findBundle("retrofit2").get())
                add("implementation",libs.findBundle("moshi").get())
                add("implementation",libs.findLibrary("room-runtime").get())
                add("kapt",libs.findLibrary("room-compiler").get())
                add("implementation",libs.findLibrary("room-ktx").get())
            }
        }
    }
}
