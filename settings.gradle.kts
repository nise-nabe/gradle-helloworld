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
include("hello-springboot-restclient-lib")
include("hello-httpclient")
include("hello-apply-plugin")
include("hello-grpc")

for (project in rootProject.children) {
    project.projectDir = file("subprojects/${project.name}")
}
