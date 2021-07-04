pipeline {
    agent any

    environment {
        GRADLE_OPTS ='-Dorg.gradle.daemon=false'
    }

    stages {
        stage("build") {
            steps {
                withGradle {
                    sh './gradlew build'
                }
            }
        }
    }
}
