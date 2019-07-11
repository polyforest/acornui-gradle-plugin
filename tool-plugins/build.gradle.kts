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

plugins {
    `kotlin-dsl`
    `maven-publish`
    //id("com.acornui.plugins.kotlin-mpp") version "0.1-SNAPSHOT" apply false
}

//buildscript {
//	repositories {
//		mavenLocal()
//		gradlePluginPortal()
//	}
//	dependencies {
//		classpath("com.acornui.plugins.ConfigPlugins:0.1-SNAPSHOT")
//	}
//}

repositories {
    mavenLocal()
    gradlePluginPortal()
    maven {
        url = uri("https://github.com/polyforest/acornui-gradle-plugin/raw/repository")
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

val kotlinVersion: String by extra
val acornVersion: String by extra
val acornConfigPluginsVersion: String by extra

dependencies {
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    implementation("com.acornui.plugins:config-plugins:$acornConfigPluginsVersion")
    implementation("com.acornui:acornui-utils:$acornVersion")
    implementation("com.acornui:acornui-core:$acornVersion")
    implementation("com.acornui:acornui-lwjgl-backend:$acornVersion")
    implementation("com.acornui:acornui-texture-packer:$acornVersion")
}

gradlePlugin {
    plugins {
        create("app") {
            id = "$group.app"
            implementationClass = "com.acornui.plugins.AppPlugin"
            displayName = "Acorn UI Multi-Platform Application"
            description = "Configuration of an Acorn UI Application."
        }
    }
}

val acornUiGradlePluginRepository: String? by extra
if (acornUiGradlePluginRepository != null) {
    publishing {
        repositories {
            maven {
                url = uri(acornUiGradlePluginRepository!!)
            }
        }
    }
}