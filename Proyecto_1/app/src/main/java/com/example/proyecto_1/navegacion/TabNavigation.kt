package com.example.proyecto_1.navegacion

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Función principal de barra de navegación
 * Mostrando la pantalla de acuerdo a lo seleccionado en la barra
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TabNavigation() {
    val tabs = listOf(TabItem.History, TabItem.Home, TabItem.Profile) //Elementos en barra
    val pagerState = rememberPagerState { tabs.count() }
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabsContent(tabs = tabs, pagerState = pagerState)
            Tabs(tabs = tabs, pagerState = pagerState)
        }
    }
}

//Desplazamiento de elementos en la barra
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) {page ->
        tabs[page].screen()
    }
}