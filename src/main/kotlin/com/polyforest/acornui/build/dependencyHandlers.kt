package com.polyforest.acornui.build

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

/**
 * Generate a dependency notation for a named Acorn UI [module] at the given [version].
 *
 * To be used in Kotlin MPP plugin source set dependency blocks.
 *
 * Example:
 * ```
 * kotlin {
 *     // ... Target declaration and compilation configuration
 *     sourceSets {
 *         commonMain {
 *             dependencies {
 *                 implementation(kotlin("stdlib-common"))
 *
 *                 // Version is controlled by `pluginsManagement` block in `settings.gradle.kts`
 *                 implementation(acornui("core"))
 *
 *                 // Versions set here will not override version substitution from `pluginsManagement` block
 *                 implementation(acornui("core", "1.0.0")
 *
 *                 // This will cause an error
 *                 implementation(acornui("core", ""))
 *             }
 *         }
 *         // ... More source set dependency configuration
 *     }
 * }
 * ```
 *
 * @param module simple name of the Acorn UI module, such as "core", "utils", or "build-tasks."
 * @param target optional requested artifact target name used in artifact (e.g. typically "metadata" \[common], "jvm", or "js").
 * @param version optional requested version where it is unspecified if null.
 */
fun KotlinDependencyHandler.acornui(module: String, target: String? = null, version: String? = null): String =
	acornuiDependencyNotation(module, target, version)

/**
 * Generate a dependency notation for a named Acorn UI [module] at the given [version].
 *
 * To be used in non-Kotlin MPP plugin dependency blocks.
 *
 * @param module simple name of the Acorn UI module, such as "core", "utils", or "build-tasks."
 * @param target optional requested artifact target name used in artifact (e.g. typically "metadata" \[common], "jvm", or "js").
 * @param version optional requested version where it is unspecified if null.
 */
fun DependencyHandler.acornui(module: String, target: String? = null, version: String? = null): String =
	acornuiDependencyNotation(module, target, version)

fun acornuiDependencyNotation(module: String, target: String? = null, version: String? = null): String =
	"com.polyforest:acornui-$module" +
			"${target?.let { "-${it.toLowerCase()}" } ?: ""}" +
			"${version?.let { ":$it" } ?: ""}"