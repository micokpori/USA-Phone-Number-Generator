package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.isDigitsOnly
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.HomeUiEvent
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Composable
fun GenerateUsaContactDialog(
    homeViewModel: HomeViewModel,
    toggleGenerateContactDialog: (HomeUiEvent.ToggleGenerateContactDialog) -> Unit
) {
    val amountToGenerate = rememberSaveable { mutableStateOf("100") }
    val isProgressBarVisible = homeViewModel.isProgressBarVisible.collectAsState().value
    val isErrorTextField = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    //the code below ensures that we cant generate more than 100000 contacts at once
    isErrorTextField.value = amountToGenerate.value.isNotEmpty() &&
            (amountToGenerate.value.toInt() > 100000 || amountToGenerate.value.toInt() < 1)
    if (amountToGenerate.value.isEmpty()) isErrorTextField.value = true

    AlertDialog(
        onDismissRequest = {
            toggleGenerateContactDialog(
                HomeUiEvent.ToggleGenerateContactDialog(context)
            )
        },
        properties = DialogProperties(
            dismissOnBackPress = !isProgressBarVisible,
            dismissOnClickOutside = !isProgressBarVisible
        ), confirmButton = {
            if (!isProgressBarVisible) {
                GenerateButton(isErrorTextField) {
                    CoroutineScope(IO).launch {
                        homeViewModel.uiEvent(
                            HomeUiEvent.GenerateContacts(context, amountToGenerate.value.toInt())
                        )
                    }
                }
            } else GeneratingProgressBar()
        },
        title = { Text(text = stringResource(R.string.generate_phone_numbers), maxLines = 2) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                AmountToGenerateTextField(amountToGenerate, isErrorTextField)
                //show error message with animation
                AnimatedVisibility(visible = isErrorTextField.value) {
                    Text(
                        text = stringResource(R.string.txtfield_error_msg),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }
        }
    )
}

@Composable
private fun AmountToGenerateTextField(
    amountToGenerate: MutableState<String>,
    isErrorTextField: MutableState<Boolean>
) {
    val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(R.string.enter_amount_to_generate)) },
        value = amountToGenerate.value,
        onValueChange = { if (it.isDigitsOnly() && it.length <= 7) amountToGenerate.value = it },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
        },
        shape = RoundedCornerShape(18.dp),
        singleLine = true,
        isError = isErrorTextField.value
    )
}

@Composable
private fun GenerateButton(
    isErrorTextField: MutableState<Boolean>,
    generateButtonClicked: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        enabled = !isErrorTextField.value,
        onClick = {
            generateButtonClicked()
        }
    )
    {
        Text(text = stringResource(id = R.string.generate))
    }
}

@Composable
private fun GeneratingProgressBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.generating_please_wait),
            modifier = Modifier.padding(top = 12.dp)
        )
        CircularProgressIndicator()
    }
}

