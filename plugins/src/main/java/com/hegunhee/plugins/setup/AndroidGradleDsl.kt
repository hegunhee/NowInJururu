package com.hegunhee.plugins.setup

import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.android(action : TestedExtension.() -> Unit) {
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

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }

        defaultConfig.targetSdk = 33
    }
}

fun Project.setupViewDataBinding() {
    android {
        viewBinding {
            enable = true
        }
        dataBinding {
            enable = true
        }
    }
}

fun TestedExtension.kotlinOptions(block : KotlinJvmOptions.() -> Unit ){
    (this as ExtensionAware).extensions.configure("kotlinOptions",block)
}
