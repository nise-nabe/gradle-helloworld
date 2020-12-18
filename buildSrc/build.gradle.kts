import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.plugins.precompiled.PrecompiledScriptPlugins

plugins {
    java
    idea
    `kotlin-dsl` apply false
}

dependencies {
    subprojects.forEach {
        runtimeOnly(project(it.path))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.gradle.kotlin.kotlin-dsl")
    apply<PrecompiledScriptPlugins>()

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    configure<KotlinDslPluginOptions> {
        experimentalWarning.set(false)
    }
}
