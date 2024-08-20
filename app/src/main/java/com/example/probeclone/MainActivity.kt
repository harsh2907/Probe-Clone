package com.example.probeclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.probeclone.navigation.ProbeNavigation
import com.example.probeclone.ui.theme.ProbeCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProbeCloneTheme {
                val navController = rememberNavController()
                ProbeNavigation(navController = navController)
            }
        }
    }
}


enum class BidType{ YES,NO; }