/*
 * Copyright 2019 Poly Forest, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.polyforest.acornui.build.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

internal val Project.acornExtension
	get(): AcornUiPluginExtension? =
		project.extensions.findByName(ACORNUI_EXTENSION_NAME) as? AcornUiPluginExtension

private const val ACORNUI_EXTENSION_NAME: String = "acorn"

/**
 * Provides a starter configuration for a basic (non-app) module that relies on Acorn UI.
 *
 * Aimed at modules that depend on Acorn UI modules.  Modules within an Acorn UI project that do not depend on Acorn UI
 * do not require this plugin.
 *
 * Plugin:   Acorn UI Basic Plugin
 * ID:       com.polyforest.acornui.basic
 * Applies:	 [KotlinMpBasicPlugin]
 */
class AcornUiBasicPlugin : Plugin<Project> {
	/**
	 * Apply this plugin to the given [project] object.
	 */
	override fun apply(project: Project) {
		TODO("not implemented")
		val extension = project.extensions.create<AcornUiPluginExtension>(ACORNUI_EXTENSION_NAME)
	}
}