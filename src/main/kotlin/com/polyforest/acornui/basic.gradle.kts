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

package com.polyforest.acornui

import com.polyforest.acornui.build.AUI

/**
 * Plugin:  com.polyforest.acornui.basic
 * Used in acornui and consumer projects for basic kotlin multiplatform modules.
 * 
 * JS compilation main defaults to "noCall"
 */

repositories {
	jcenter()
}

plugins {
	id("org.jetbrains.kotlin.multiplatform")
}

val acorn = AUI(project)
val KOTLIN_LANGUAGE_VERSION by acorn.defaults
val KOTLIN_JVM_TARGET by acorn.defaults
val PRODUCT_VERSION by acorn.defaults
val PRODUCT_GROUP by acorn.defaults
val JS_MODULE_KIND by acorn.defaults

version = PRODUCT_VERSION
group = PRODUCT_GROUP

kotlin {
	js {
		compilations.all {
			kotlinOptions {
				moduleKind = JS_MODULE_KIND.also { kind ->
					val validModuleKinds = listOf("plain", "amd", "commonjs", "umd")
					if (!validModuleKinds.any { kind == it })
						logger.warn("JS module kind is invalid.  It must be one of the following: ${validModuleKinds.joinToString(", ")}")
				}
				sourceMap = true
				sourceMapEmbedSources = "always"
				main = "noCall"
			}
		}
	}
	jvm {
		compilations.all {
			kotlinOptions {
				jvmTarget = KOTLIN_JVM_TARGET
			}
		}
	}
	targets.all {
		compilations.all {
			kotlinOptions {
				languageVersion = KOTLIN_LANGUAGE_VERSION
				apiVersion = KOTLIN_LANGUAGE_VERSION
				verbose = true
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(kotlin("stdlib-common"))
			}
		}
		val commonTest by getting {
			dependencies {
				implementation(kotlin("test-common"))
				implementation(kotlin("test-annotations-common"))
			}
		}
		jvm().compilations["main"].defaultSourceSet {
			dependencies {
				implementation(kotlin("stdlib-jdk8"))
			}
		}
		jvm().compilations["test"].defaultSourceSet {
			dependencies {
				implementation(kotlin("test"))
				implementation(kotlin("test-junit"))
			}
		}
		js().compilations["main"].defaultSourceSet {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}
		js().compilations["test"].defaultSourceSet {
			dependencies {
				implementation(kotlin("test-js"))
			}
		}
	}
}

afterEvaluate {
	tasks.withType(Test::class.java).configureEach {
		jvmArgs("-ea")
	}
}
