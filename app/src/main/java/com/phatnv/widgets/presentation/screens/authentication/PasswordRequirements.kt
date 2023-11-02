package com.phatnv.widgets.presentation.screens.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.phatnv.widgets.data.enum.PasswordRequirements

@Composable
fun PasswordRequirements(
    completedPasswordRequirements: List<PasswordRequirements>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PasswordRequirements.values().forEach { requirement ->
            Requirement(
                message = stringResource(id = requirement.label),
                satisfied = completedPasswordRequirements.contains(requirement)
            )
        }
    }
}