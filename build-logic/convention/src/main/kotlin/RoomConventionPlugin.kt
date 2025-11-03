import androidx.room.gradle.RoomExtension
import com.dothebestmayb.dodotalk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                // RoomConventionPlugin을 설정한 module(A module이라 하자)은 아래 library의 코드들을 볼 수 있다.
                // A module에 대한 의존성을 추가한 다른 모듈들은 별도로 추가하지 않으면 아래 library의 코드들을 볼 수 없다.
                // Implementation 대신 Api로 library를 추가하면, 다른 모듈들도 아래 library의 코드들을 볼 수 있다.
                "commonMainApi"(libs.findLibrary("androidx-room-runtime").get())
                "commonMainApi"(libs.findLibrary("sqlite-bundled").get()) // KMP SQLite : https://developer.android.com/kotlin/multiplatform/sqlite
                "kspAndroid"(libs.findLibrary("androidx-room-compiler").get())
                "kspIosSimulatorArm64"(libs.findLibrary("androidx-room-compiler").get())
                "kspIosArm64"(libs.findLibrary("androidx-room-compiler").get())
                "kspIosX64"(libs.findLibrary("androidx-room-compiler").get())
            }
        }
    }
}
