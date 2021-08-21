package com.nisecoder.helloworld.intellij.plugin.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import javax.swing.JComponent
import javax.swing.JLabel


class MyNewProjectWizardModuleBuilder: ModuleBuilder() {
    override fun getModuleType(): ModuleType<*> {
        return ModuleType.EMPTY
    }

    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {
        super.setupRootModel(modifiableRootModel)
    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        return arrayOf(object : ModuleWizardStep() {
            override fun getComponent(): JComponent {
                return JLabel("Put your content here")
            }

            override fun updateDataModel() {}
        }
        )
    }
}
