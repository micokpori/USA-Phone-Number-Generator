package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsaStateCard(
    isChecked: Boolean,
    usaStateName: String,
    modifier: Modifier = Modifier,
    updateCheckedState: (Boolean) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = updateCheckedState,
                modifier.weight(0.2f)
            )
            Text(text = usaStateName, modifier.weight(0.8f), style = MaterialTheme.typography.titleMedium)
        }
    }
}
