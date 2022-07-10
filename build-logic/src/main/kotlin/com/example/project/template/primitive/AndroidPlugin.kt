package com.example.project.template.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            androidLibrary {
                namespace?.let {
                    this.namespace = it
                }
                compileSdk = 32

                defaultConfig {
                    minSdk = 23
                }

                compileOptions {
                    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
                    targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
                    isCoreLibraryDesugaringEnabled = true
                }

                kotlinOptions {
                    // Treat all Kotlin warnings as errors (disabled by default)
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
//              "-opt-in=kotlin.RequiresOptIn",
                        // Enable experimental coroutines APIs, including Flow
//              "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    )

                    jvmTarget = org.gradle.api.JavaVersion.VERSION_1_8.toString()
                }

                dependencies {
                    add("coreLibraryDesugaring", libs.findLibrary("androidDesugarJdkLibs").get())
                }

                defaultConfig.targetSdk = 32
            }
        }
    }
}
