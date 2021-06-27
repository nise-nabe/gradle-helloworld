dependencyResolutionManagement {
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

        mavenCentral()
    }
}

// includeBuild repositories need to be add in root project to resolve for runtime classpath
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
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


for (project in rootProject.children) {
    val projectPath = if (project.name.startsWith("hello-springboot")) {
        file("subprojects/springboot/${project.name}")
    } else {
        file("subprojects/${project.name}")
    }

    project.projectDir = projectPath
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
