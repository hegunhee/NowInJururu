package com.hegunhee.plugins.feature

import com.hegunhee.plugins.setup.setupViewDataBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeaturePlugin : Plugin<Project>{

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("hegunhee.android")
                apply("hegunhee.android.hilt")
            }
            setupViewDataBinding()

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation",libs.findLibrary("core-ktx").get())
                add("implementation",libs.findLibrary("appcompat").get())
                add("implementation",libs.findLibrary("material").get())
                add("implementation",libs.findLibrary("constraint-layout").get())
                add("implementation",libs.findLibrary("junit").get())
                add("androidTestImplementation",libs.findLibrary("ext-junit").get())
                add("androidTestImplementation",libs.findLibrary("espresso-core").get())
                add("implementation",libs.findBundle("lifecycleScope").get())
                add("implementation",libs.findLibrary("picasso").get())
                add("implementation",libs.findBundle("navigation").get())
                add("implementation",libs.findLibrary("concat-adapter").get())

                add("implementation",libs.findBundle("paging").get())

                add("implementation",project(":core:domain"))
                add("implementation",project(":core:navigation"))

                add("implementation",project(":resource_common"))
            }
        }
    }
}