@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
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
                ivy {
                    name = "Yarn"
                    setUrl("https://github.com/yarnpkg/yarn/releases/download")
                    patternLayout {
                        artifact("v[revision]/[artifact](-v[revision]).[ext]")
                    }
                    metadataSources {
                        artifact()
                    }
                }
            }
            filter {
                includeModule("com.yarnpkg", "yarn")
            }
        }

        maven {
            name = "Gradle"
            url = uri("https://repo.gradle.org/gradle/libs-releases")
            content {
                includeModule("org.gradle", "gradle-tooling-api")
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

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://packages.jetbrains.team/maven/p/ij/intellij-shared-indexes-public/")
                }
            }
            filter {
                includeGroup("com.jetbrains.intellij.indexing.shared")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    name = "HelloGithubPackage"
                    url = uri("https://maven.pkg.github.com/nise-nabe/gradle-plugins")
                    credentials(PasswordCredentials::class)
                }
            }
            filter {
                includeGroupByRegex("com\\.nisecoder\\..*")
            }
        }

        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://maven.pkg.github.com/nise-nabe/nodejs-gradle-plugins")
                    credentials {
                        val hello = providers.credentials(PasswordCredentials::class, "HelloGithubPackage").get()
                        username = hello.username
                        password = hello.password
                    }
                }
            }
            filter {
                includeModule("com.nisecoder.gradle", "nodejs-gradle-plugins")
            }
        }
    }

    plugins {
        id("com.google.osdetector") version "1.7.2"
        id("com.nisecoder.ci-detect") version "0.0.12"
        id("com.nisecoder.nodejs") version "0.0.5"
        id("org.gradle.crypto.checksum") version "1.4.0"
    }

}

plugins {
    id("com.gradle.enterprise") version("3.12.1")
}

rootProject.name = "HelloWorld"

includeBuild("build-platforms")
includeBuild("build-logic")
includeBuild("subprojects/intellij-idea-plugin")
includeBuild("subprojects/minecraft-forge")
includeBuild("subprojects/minecraft-spigot")
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
include("hello-exposed")
include("hello-micronaut")
include("hello-archunit")
include("hello-unboundid")
include("hello-vite")
include("hello-my-nodejs")
include("hello-nextjs")
include("hello-gradle-tooling-api")
include("hello-komapper")
include("hello-ksp")


for (project in rootProject.children) {
    listOf("/", "springboot/")
        .map { file("subprojects/${it}${project.name}") }
        .find { it.isDirectory }?.let {
            project.projectDir = it
        }
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
