@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        maven {
            name = "Spring Release Repository"
            url = uri("https://repo.spring.io/release")
            mavenContent {
                releasesOnly()
                includeGroup("org.springframework")
                includeGroup("org.springframework.boot")
            }
        }

        maven {
            name = "Spring Snapshot Repository"
            url = uri("https://repo.spring.io/snapshot")
            mavenContent {
                snapshotsOnly()
                includeGroup("org.springframework")
                includeGroup("org.springframework.boot")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
                }
            }
            filter {
                includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.minecraftforge.net/")
                }
            }

            filter {
                includeGroup("cpw.mods")
                includeGroup("net.minecraftforge")
                includeGroup("net.jodah")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://libraries.minecraft.net/")
                    metadataSources {
                        artifact()
                    }
                }
            }

            filter {
                includeGroup("net.minecraft")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://www.jetbrains.com/intellij-repository/releases")
                    metadataSources {
                        mavenPom()
                    }
                    mavenContent {
                        releasesOnly()
                    }
                }
            }
            filter {
                includeGroup("com.jetbrains.intellij.idea")
                includeGroup("com.jetbrains.intellij.java")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
                }
            }
            filter {
                includeGroup("org.jetbrains.intellij.deps")
            }
        }

        // for intellij idea plugin's jetbrains runtime jre repository
        exclusiveContent {
            forRepository {
                ivy {
                    url = uri("https://cache-redirector.jetbrains.com/intellij-jbr")
                    patternLayout {
                        artifact("[revision].tar.gz")
                    }
                    metadataSources {
                        artifact()
                    }
                }
            }
            filter {
                includeModule("com.jetbrains", "jbre")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    name = "HelloGithubPackage"
                    url = uri("https://maven.pkg.github.com/nise-nabe/gradle-helloworld")
                    // %HOMEPATH%/.gradle/gradle.properties
                    // HelloGithubPackageUsername=
                    // HelloGithubPackagePassword=
                    credentials(PasswordCredentials::class)
                }
            }
            filter {
                includeModule("com.nisecoder.helloworld", "hello-github-packages")
            }
        }

        exclusiveContent {
            forRepository {
                ivy {
                    name = "Node.js"
                    setUrl("https://nodejs.org/dist/")
                    patternLayout {
                        artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]")
                    }
                    metadataSources {
                        artifact()
                    }
                }
            }
            filter {
                includeModule("org.nodejs", "node")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
                }
            }

            filter {
                includeGroup("org.spigotmc")
                includeGroup("org.bukkit")
            }

        }

        mavenCentral()
    }
}

// includeBuild repositories need to be add in root project to resolve for runtime classpath
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.minecraftforge.net")
                }
            }
            filter {
                includeGroup("net.minecraftforge.gradle")
                includeGroup("net.minecraftforge")
            }
        }
    }
}

plugins {
    id("com.gradle.enterprise") version("3.6.3")
}

rootProject.name = "HelloWorld"

includeBuild("build-platforms")
includeBuild("build-conventions")
include("hello-kotlin")
include("hello-typescript")
include("hello-springboot")
include("hello-springboot-webmvc-tomcat")
include("hello-springboot-webmvc-jetty")
include("hello-springboot-restclient-lib")
include("hello-springboot-zipkin")
include("hello-httpclient")
include("hello-apply-plugin")
include("hello-grpc")
include("hello-mybatis")
include("hello-provider")
include("hello-consumer")
include("hello-ktor")
include("hello-crs")
include("hello-tax-return")
include("hello-springboot-graphql")
include("hello-nuxtjs")
include("hello-asciidoc")
include("hello-fritz2")
include("hello-github-actions")
include("hello-github-packages")
include("hello-github-packages-consumer")
include("hello-testng")
include("hello-fluency")
include("hello-gradle-plugin")
include("hello-gradle-settings-plugin")
include("hello-minecraft-forge-mod")
include("hello-minecraft-spigot-mod")
include("hello-intellij-idea-plugin-simple")
include("hello-intellij-idea-plugin-new-project-wizard")


for (project in rootProject.children) {
    val projectPath = when {
        project.name.startsWith("hello-springboot") -> {
            file("subprojects/springboot/${project.name}")
        }
        project.name.startsWith("hello-intellij-idea-plugin") -> {
            file("subprojects/intellij-idea-plugin/${project.name}")
        }
        else -> {
            file("subprojects/${project.name}")
        }
    }

    project.projectDir = projectPath
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
