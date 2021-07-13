import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    vcsRoot(HttpsGithubComNiseNabeGradleHelloworldGit)

    buildType(Build)

    params {
        password("env.ORG_GRADLE_PROJECT_HelloGithubPackageUsername", "credentialsJSON:b5f01e92-c41b-402c-a7ab-12d858fcf19a", display = ParameterDisplay.HIDDEN)
        password("env.ORG_GRADLE_PROJECT_HelloGithubPackagePassword", "credentialsJSON:a6704e64-dce2-43f5-a899-29f937455424", display = ParameterDisplay.HIDDEN)
    }

    features {
        feature {
            id = "PROJECT_EXT_2"
            type = "project-graphs"
            param("series", """
                [
                  {
                    "type": "valueType",
                    "title": "Build Duration (excluding Checkout Time)",
                    "sourceBuildTypeId": "GradleHelloworld_Build",
                    "key": "BuildDurationNetTime"
                  }
                ]
            """.trimIndent())
            param("format", "duration")
            param("hideFilters", "")
            param("title", "Build Duration")
            param("defaultFilters", "")
            param("seriesTitle", "Serie")
        }
    }
}

object Build : BuildType({
    name = "Build"

    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(HttpsGithubComNiseNabeGradleHelloworldGit)
    }

    steps {
        gradle {
            tasks = "build"
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComNiseNabeGradleHelloworldGit : GitVcsRoot({
    name = "https://github.com/nise-nabe/gradle-helloworld.git"
    url = "https://github.com/nise-nabe/gradle-helloworld.git"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/master"
})
