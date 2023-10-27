package com.example.proyecto_1.navegacion

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Contenido de barra y formato general
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    NavigationBar(contentColor = Color.Black) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = { androidx.compose.material3.Icon(tab.icon, contentDescription = tab.title,
                    modifier = Modifier.width(15.dp)) },
                label = { androidx.compose.material3.Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem.History, TabItem.Home, TabItem.Profile
    )
    val pagerState = rememberPagerState { tabs.count() }
    Tabs(tabs = tabs, pagerState = pagerState)
}