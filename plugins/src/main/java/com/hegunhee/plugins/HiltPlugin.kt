package com.hegunhee.plugins

import com.hegunhee.plugins.setup.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin-kapt")
            dependencies {
                add("kapt", libs.findLibrary("hilt.compiler").get())
            }


            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    add("implementation", libs.findLibrary("hilt.core").get())
                }
            }

            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("dagger.hilt.android.plugin")
                dependencies {
                    add("implementation", libs.findLibrary("hilt.android").get())
                }
            }
        }
    }
}
