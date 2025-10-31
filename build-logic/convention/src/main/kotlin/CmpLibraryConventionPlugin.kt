import com.dothebestmayb.dodotalk.convention.compose
import com.dothebestmayb.dodotalk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.dothebestmayb.convention.kmp.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(compose.ui)
                            implementation(compose.foundation)
                            implementation(compose.material3)
                            implementation(
                                libs.findLibrary("jetbrains-compose-material-icons-core").get()
                            )
                        }
                    }
                }
            }
        }
    }
}
