package com.hegunhee.plugins.setup

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.androidApplication(action : BaseAppModuleExtension.() -> Unit){
    extensions.configure(action)
}

fun Project.androidLibrary(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action : TestedExtension.() -> Unit){
    extensions.configure(action)
}

fun Project.setupAndroid(){
    android {
        compileSdkVersion(33)

        defaultConfig{
            minSdk = 24
        }

        compileOptions{
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        dataBinding{
            enable = true
        }
        viewBinding{
            enable = true
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }

        defaultConfig.targetSdk = 33

    }
}

fun TestedExtension.kotlinOptions(block : KotlinJvmOptions.() -> Unit ){
    (this as ExtensionAware).extensions.configure("kotlinOptions",block)
}