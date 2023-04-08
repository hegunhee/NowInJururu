package com.hegunhee.plugins.feature

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeaturePlugin : Plugin<Project>{

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("hegunhee.android")
            }
        }
    }
}