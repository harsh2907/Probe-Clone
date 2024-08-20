package com.example.probeclone.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.probeclone.BidType
import com.example.probeclone.R
import com.example.probeclone.homeScreen.HomeScreen
import com.example.probeclone.homeScreen.Question
import com.example.probeclone.homeScreen.YesNoBottomSheet
import com.example.probeclone.questionScreen.QuestionScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProbeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            val scope = rememberCoroutineScope()
            var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
            val sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            )
            val question = remember {
                Question(
                    question = "Bitcoin to be priced at 2611.37 USDT or more at 02:00 PM?",
                    tag = "Bitcoin",
                    tip = "Bitcoin open price at 01:50 PM was 2611.37 USDT.",
                    totalTraders = 3043,
                    image = R.drawable.bitcoin,
                    yesAmount = 6.0,
                    noAmount = 4.0
                )
            }
            var selectedBid by remember { mutableStateOf(BidType.YES) }

            HomeScreen(
                question = question,
                onCardClicked = {
                    navController.navigate(Screen.QuestionScreen.route)
                },
                onYesClicked = {
                    selectedBid = BidType.YES
                    isBottomSheetVisible = true
                },
                onNoClicked = {
                    selectedBid = BidType.NO
                    isBottomSheetVisible = true
                }
            )



            YesNoBottomSheet(
                question = question,
                isBottomSheetVisible = isBottomSheetVisible,
                sheetState = sheetState,
                selectedBid = selectedBid,
                onSelectedBidChange = { newBid ->
                    selectedBid = newBid
                },
                onDismiss = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible = false }
                }
            )
        }

        composable(route = Screen.QuestionScreen.route) {
            QuestionScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}