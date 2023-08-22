package com.hegunhee.compose.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hegunhee.ui_component.text.ScreenHeaderText

@Composable
fun SearchScreenRoot(
    viewModel : SearchViewModel = hiltViewModel(),
    onNavigateTwitchChannelClick : (String) -> Unit
) {
    val uiModel = viewModel.uiModel.value
    val (searchQuery, onValueChanged) = viewModel.searchQuery
    SearchScreen(
        uiModel = uiModel,
        searchQuery = searchQuery,
        onValueChanged = onValueChanged,
        onNavigateTwitchChannelClick = onNavigateTwitchChannelClick,
        onSearchStreamDataClick = viewModel::fetchStreamData,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    uiModel : SearchUiModel,
    searchQuery : String,
    onValueChanged : (String) -> Unit,
    onNavigateTwitchChannelClick: (String) -> Unit,
    onSearchStreamDataClick : () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalPaddingValues.current)
            .padding(horizontal = 20.dp)
    ) {
        ScreenHeaderText(text = "검색")
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onValueChanged,
            label = { Text("검색어를 입력해주세요") },
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                Icon(
                    painter = painterResource(com.hegunhee.ui_component.R.drawable.ic_search_24),
                    contentDescription = null,
                    modifier = Modifier.clickable { onSearchStreamDataClick() }
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchStreamDataClick()
                keyboardController?.hide()
            }),
            modifier = Modifier.fillMaxWidth()
        )
        when(uiModel){
            is SearchUiModel.Loading -> {
                Text(text = uiModel.toString())
            }
            is SearchUiModel.Success -> {
                Text(text = uiModel.toString())
            }
            is SearchUiModel.Error -> {
                Text(text = uiModel.toString())
            }
        }
    }
}