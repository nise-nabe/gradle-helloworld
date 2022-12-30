package com.nisecoder.helloworld.gradle;

import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;
import org.gradle.jvm.toolchain.JavaLanguageVersion;
import org.gradle.jvm.toolchain.JavaToolchainService;
import org.gradle.jvm.toolchain.JvmVendorSpec;
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension;
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile;

public class KotlinPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getPlugins().apply("org.jetbrains.kotlin.jvm");

        JavaToolchainService javaToolchain = (JavaToolchainService) project.getExtensions().getByName("javaToolchains");
        javaToolchain.compilerFor(spec -> {
            spec.getLanguageVersion().set(JavaLanguageVersion.of(17));
            spec.getVendor().set(JvmVendorSpec.ADOPTIUM);
        });

        project.getExtensions().configure(KotlinJvmProjectExtension.class, it ->
            it.jvmToolchain(spec -> spec.getLanguageVersion().set(JavaLanguageVersion.of(17)))
        );

        var tasks = project.getTasks();
        tasks.withType(KotlinJvmCompile.class).configureEach(task -> {
            var options = task.getKotlinOptions();
            options.setLanguageVersion("1.8");
            options.setApiVersion("1.8");
            options.setJvmTarget(JavaVersion.VERSION_17.toString());
            options.setJavaParameters(true);
        });

        var dependencies = project.getDependencies();
        dependencies.add("implementation", dependencies.platform("org.jetbrains.kotlin:kotlin-bom:1.7.10"));
        dependencies.add("testImplementation", dependencies.platform("org.jetbrains.kotlin:kotlin-bom:1.7.10"));
        dependencies.add("testImplementation", "org.jetbrains.kotlin:kotlin-test");

        Test test = (Test) tasks.getByName("test");
        var processorNumber = Runtime.getRuntime().availableProcessors();
        if (processorNumber > 0) {
            test.setMaxParallelForks(processorNumber);
        } else {
            test.setMaxParallelForks(1);
        }

        test.reports(reports -> {
            var rootBuildDir = project.getRootProject().getLayout().getBuildDirectory();
            var junitXml = reports.getJunitXml();
            junitXml.getRequired().set(true);
            junitXml.getOutputLocation().set(rootBuildDir.dir("test-results/junit/" + project.getName()));

            var html = reports.getHtml();
            html.getRequired().set(true);
            html.getOutputLocation().set(rootBuildDir.dir("reports/tests/test/" + project.getName()));
        });
    }
}
