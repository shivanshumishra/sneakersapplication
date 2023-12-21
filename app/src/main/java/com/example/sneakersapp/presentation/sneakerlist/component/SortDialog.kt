package com.example.sneakersapp.presentation.sneakerlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sneakersapp.R

@Composable
fun CommonDialog(
    title: String?,onStateChange : (Boolean) -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            onStateChange(false)
        },
        title = title?.let {
            {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(text = title)
                }
            }
        },
        text = content,
        confirmButton = {
            TextButton(onClick = { onStateChange(false) }) {
                Text(stringResource(id = R.string.button_ok), color = Color.Red)
            }
        }, modifier = Modifier.padding(vertical = 5.dp)
    )
}

@Composable
fun SortByDialog(
    onStateChange: (Boolean) -> Unit,
    onOptionSelected: (String) -> Unit,
    currentSelected: String
) {
    val dialogSelectedValue = remember { mutableStateOf(currentSelected) }

    CommonDialog(title = "Sort List By", onStateChange = {
        onStateChange(it)
        onOptionSelected(dialogSelectedValue.value)
    }) {
        SingleChoiceView(
            onOptionSelected = {
                dialogSelectedValue.value = it
            },
            currentSelected = dialogSelectedValue.value
        )
    }
}

@Composable
fun SingleChoiceView(
    onOptionSelected : (String) -> Unit,
    currentSelected : String
) {
    val radioOptions = listOf(
        "Year - Ascending",
        "Year - Descending",
        "Year - None")

    var (selectedOption) = remember { mutableStateOf(radioOptions[2]) }

    if (currentSelected.isNotBlank()){
        selectedOption = currentSelected
    }

    Column(
        Modifier.fillMaxWidth()
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Red,
                        unselectedColor = Color.Gray
                    )
                )
                Text(
                    text = text
                )
            }
        }
    }
}