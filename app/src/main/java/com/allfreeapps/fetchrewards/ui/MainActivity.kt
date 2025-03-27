package com.allfreeapps.fetchrewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.allfreeapps.balanser_tool.ui.theme.My_Theme
import com.allfreeapps.fetchrewards.ui.composables.MainScreen
import com.allfreeapps.fetchrewards.viewModel.FetchRewardsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: FetchRewardsViewModel by viewModels()
    private lateinit var mainScreen: MainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        mainScreen = MainScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            My_Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    mainScreen.FetchRewardsApp(
                        innerPadding,
                        viewModel
                    )
                }
            }
        }
    }
}