package com.dothebestmayb.dodotalk.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.compose: ComposePlugin.Dependencies
    get() = dependencies.extensions.getByType<ComposePlugin.Dependencies>()

