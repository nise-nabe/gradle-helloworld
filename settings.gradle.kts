dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "HelloWorld"

include("hello-kotlin")
include("hello-typescript")
include("hello-springboot")
include("hello-springboot-webmvc-tomcat")
include("hello-springboot-webmvc-jetty")
include("hello-httpclient")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
