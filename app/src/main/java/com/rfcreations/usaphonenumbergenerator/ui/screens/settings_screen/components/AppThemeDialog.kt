package com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.theme.ThemeUiState

@Composable
fun AppThemeDialog(
    themeUiState: ThemeUiState,
    toggleShowAppThemeDialog: () -> Unit
) {
    val selectedTheme = themeUiState.selectedTheme.collectAsState().value
    val dynamicTheme = themeUiState.dynamicTheme.collectAsState().value
    val themeOptions = stringArrayResource(id = R.array.theme_options)
    Dialog(
        onDismissRequest = { toggleShowAppThemeDialog() }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 18.dp, bottom = 8.dp),
                text = stringResource(id = R.string.app_theme),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            themeOptions.forEachIndexed { index, theme ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            themeUiState.updateSelectedTheme(index)
                            toggleShowAppThemeDialog()
                        }
                ) {
                    RadioButton(
                        selected = selectedTheme == index,
                        onClick = {
                            themeUiState.updateSelectedTheme(index)
                            toggleShowAppThemeDialog()
                        }
                    )
                    HeaderText(
                        text = theme
                    )
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Divider(color = MaterialTheme.colorScheme.outline)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            themeUiState.toggleDynamicTheme()
                            toggleShowAppThemeDialog()
                        }) {
                    HeaderText(
                        text = stringResource(id = R.string.dynamic_theme),
                        modifier = Modifier
                            .weight(0.9f)
                            .padding(start = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    DynamicThemeCheckBox(
                        checked = dynamicTheme,
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(end = 8.dp)
                    ) {
                        themeUiState.toggleDynamicTheme()
                        toggleShowAppThemeDialog()
                    }
                }
            }
        }
    }
}

@Composable
private fun DynamicThemeCheckBox(
    checked: Boolean,
    modifier: Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier
            .padding(end = 8.dp)
    )
}

@Composable
private fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    fontStyle: FontStyle = FontStyle.Normal
) {
    Text(text, modifier, fontWeight = fontWeight, style = style, fontStyle = fontStyle)
}
