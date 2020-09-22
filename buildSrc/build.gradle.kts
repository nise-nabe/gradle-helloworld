import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.plugins.precompiled.PrecompiledScriptPlugins as PrecompiledScriptPlugins1

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
    apply<PrecompiledScriptPlugins1>()

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_14.toString()
        }
    }

    configure<KotlinDslPluginOptions> {
        experimentalWarning.set(false)
    }
}
