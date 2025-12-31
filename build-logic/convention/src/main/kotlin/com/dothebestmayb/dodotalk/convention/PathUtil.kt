package com.dothebestmayb.dodotalk.convention

import org.gradle.api.Project
import java.util.Locale

/**
 * namespace를 하드코딩 하지 않도록 도와주는 함수
 *
 * path는 ":core:data"와 같은 형태로 되어 있음
 *
 *     androidLibrary {
 *         namespace = "com.dothebestmayb.core.data"
 */
fun Project.pathToPackageName(): String {
    val relativePackageName = path
        .replace(':', '.')
        .lowercase()

    return "com.dothebestmayb$relativePackageName"
}

fun Project.pathToResourcePrefix(): String {
    return path
        .replace(':', '_')
        .lowercase()
        .drop(1) + "_"
}

fun Project.pathToFrameworkName(): String {
    val parts = this.path.split(":", "-", "_", " ")
    // :core:data -> ["Core", "Data"] -> "CoreData"
    return parts.joinToString("") { part ->
        part.replaceFirstChar {
            /**
             * uppercase는 전체를, titlecase는 첫 글자만 대문자로 변경한다.
             * 예를 들어, 독일어 ß는 uppercase를 사용하면 "SS", titlecase를 사용하면 "Ss"가 된다.
             */
            it.titlecase(Locale.ROOT)
        }
    }
}
