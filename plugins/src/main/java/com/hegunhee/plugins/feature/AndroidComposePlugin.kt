package com.hegunhee.plugins.feature

import com.android.build.gradle.LibraryExtension
import com.hegunhee.plugins.setup.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("hegunhee.android")
                apply("hegunhee.hilt")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                add("implementation",libs.findLibrary("core-ktx").get())
                add("implementation",platform(libs.findLibrary("kotlin-bom").get()))
                add("implementation",libs.findLibrary("lifecycle-lifecycleScope").get())
                add("implementation",libs.findLibrary("compose-lifecycleScope").get())
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

                add("implementation",libs.findLibrary("coil").get())

                add("implementation",project(":core:domain"))
                add("implementation",project(":core:navigation"))

                add("implementation",project(":resource_common"))

                add("implementation",libs.findBundle("paging").get())
                add("implementation",libs.findLibrary("paging-compose").get())
            }
        }
    }
}
