import com.dothebestmayb.dodotalk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpFeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.dothebestmayb.convention.cmp.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(project(":core:presentation"))
                            implementation(project(":core:designsystem"))

                            implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))

                            implementation(libs.findLibrary("koin-compose").get())
                            implementation(libs.findLibrary("koin-compose-viewmodel").get())

                            implementation(libs.findLibrary("jetbrains-compose-runtime").get())
                            implementation(libs.findLibrary("jetbrains-compose-viewmodel").get())
                            implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel").get())
                            implementation(libs.findLibrary("jetbrains-lifecycle-compose").get())

                            implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel-savedstate").get())
                            implementation(libs.findLibrary("jetbrains-savedstate").get())
                            implementation(libs.findLibrary("jetbrains-bundle").get())
                            implementation(libs.findLibrary("jetbrains-compose-navigation").get())

                        }
                    }

                    androidMain {
                        dependencies {
                            implementation(project.dependencies.platform(libs.findLibrary("koin-bom").get()))

                            implementation(libs.findLibrary("koin-android").get())
                            implementation(libs.findLibrary("koin-androidx-compose").get())
                            implementation(libs.findLibrary("koin-androidx-navigation").get())
                            implementation(libs.findLibrary("koin-core-viewmodel").get())
                        }
                    }
                }
            }
        }
    }
}
