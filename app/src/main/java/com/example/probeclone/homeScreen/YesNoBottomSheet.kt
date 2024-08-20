package com.example.probeclone.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probeclone.BidType
import com.example.probeclone.ui.theme.UiColors
import com.example.probeclone.utils.SwipeToConfirmButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YesNoBottomSheet(
    question: Question,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    selectedBid: BidType,
    onSelectedBidChange: (BidType) -> Unit,
    onDismiss: () -> Unit,
) {

    var selectedYesAmount by remember { mutableDoubleStateOf(question.yesAmount) }
    var selectedYesQuantity by remember { mutableIntStateOf(1) }

    var selectedNoAmount by remember { mutableDoubleStateOf(question.noAmount) }
    var selectedNoQuantity by remember { mutableIntStateOf(1) }

    // Handler for resetting values on dismiss
    val handleDismiss = {
        // Reset the values to their initial state
        selectedYesAmount = question.yesAmount
        selectedYesQuantity = 1

        selectedNoAmount = question.noAmount
        selectedNoQuantity = 1

        // Call the passed onDismiss function
        onDismiss()
    }




    if (isBottomSheetVisible) {

        ModalBottomSheet(
            modifier = Modifier.navigationBarsPadding() ,
            onDismissRequest = handleDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = RectangleShape,
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                BottomSheetDefaults.DragHandle(
                    color = Color.White, height = 6.dp, width = 30.dp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = ShapeDefaults.Large.copy(
                            bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)
                        )
                    )
                    .background(color = UiColors.BackgroundColor)
                    .padding(24.dp), // Inner padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = question.question,
                        modifier = Modifier.weight(1f)
                    )
                    Image(
                        painter = painterResource(id = question.image),
                        contentDescription = question.tag,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .background(
                                if (selectedBid == BidType.YES) UiColors.Blue else Color.Transparent
                            )
                            .clickable {
                                if (selectedBid != BidType.YES) {
                                    onSelectedBidChange(BidType.YES)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Yes ₹ ${question.yesAmount}",
                            color = if (selectedBid == BidType.YES) Color.White else Color.Black,
                            modifier = Modifier.padding(vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .background(
                                if (selectedBid == BidType.NO) UiColors.Red else Color.Transparent
                            )
                            .clickable {
                                if (selectedBid != BidType.NO) {
                                    onSelectedBidChange(BidType.NO)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No ₹ ${question.noAmount}",
                            color = if (selectedBid == BidType.NO) Color.White else Color.Black,
                            modifier = Modifier.padding(vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                BidAmountSelection(
                    originalBitAmount = if (selectedBid == BidType.YES) question.yesAmount else question.noAmount,
                    selectedBitAmount = if (selectedBid == BidType.YES) selectedYesAmount else selectedNoAmount,
                    onBitAmountChange = { amount ->
                        if (selectedBid == BidType.YES) {
                            selectedYesAmount = amount
                        } else {
                            selectedNoAmount = amount
                        }
                    },
                    bidQuantity = if (selectedBid == BidType.YES) selectedYesQuantity else selectedNoQuantity,
                    onBidQuantityChange = { qty ->
                        if (selectedBid == BidType.YES) {
                            selectedYesQuantity = qty
                        } else {
                            selectedNoQuantity = qty
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))


                SwipeToConfirmButton(
                    selectedBid = selectedBid,
                    onConfirm = handleDismiss
                )

            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun BidAmountSelection(
    originalBitAmount: Double,
    selectedBitAmount: Double,
    onBitAmountChange: (Double) -> Unit,
    bidQuantity: Int,
    onBidQuantityChange: (Int) -> Unit,
) {
    val isHighProbability = remember(selectedBitAmount) {
        selectedBitAmount - originalBitAmount >= 0
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = if (isHighProbability) {
                    "High probability of getting a match"
                } else {
                    "Low probability of getting a match"
                },
                color = if (isHighProbability) Color(0xff387F39) else Color(0xffF4CE14),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "₹$selectedBitAmount",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Text(
                        text = "3247 qty available",
                        fontSize = 8.sp,
                        color = Color.LightGray
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ElevatedCard(
                    onClick = {
                        if (selectedBitAmount > 0.5) {
                            onBitAmountChange(selectedBitAmount - 0.5)
                        }
                    },
                    shape = ShapeDefaults.Small,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White,
                        contentColor = UiColors.Blue
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }

                Slider(
                    value = selectedBitAmount.toFloat(),
                    onValueChange = {
                        val newValue = (it * 2).toInt() / 2.0
                        onBitAmountChange(newValue.coerceIn(0.5, 9.5))
                    },
                    valueRange = 0.5f..9.5f,
                    steps = 18, // Since 0.5 to 9.5 with 0.5 step has 18 intervals
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = if (isHighProbability) Color.Black else Color(0xffF4CE14),
                        activeTrackColor = if (isHighProbability) Color.Black else Color(0xffF4CE14),
                        activeTickColor = if (isHighProbability) Color.Black else Color(0xffF4CE14),
                        inactiveTrackColor = Color.LightGray,
                        inactiveTickColor = Color.LightGray
                    )
                )

                ElevatedCard(
                    onClick = {
                        if (selectedBitAmount < 9.5) {
                            onBitAmountChange(selectedBitAmount + 0.5)
                        }
                    },
                    shape = ShapeDefaults.Small,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White,
                        contentColor = UiColors.Blue
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                    }
                }
            }

            // Bit Amount Slider with Buttons

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Quantity",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$bidQuantity",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

            // Bid Quantity Slider with Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedCard(
                    onClick = {
                        if (bidQuantity > 1) {
                            onBidQuantityChange(bidQuantity - 1)
                        }
                    },
                    shape = ShapeDefaults.Small,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White,
                        contentColor = UiColors.Blue
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }

                Slider(
                    value = bidQuantity.toFloat(),
                    onValueChange = {
                        onBidQuantityChange(it.toInt().coerceIn(1, 5))
                    },
                    valueRange = 1f..5f,
                    steps = 3, // Since 1 to 5 with 1 step has 3 intervals
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Black,
                        activeTrackColor = Color.Black,
                        activeTickColor = Color.Black,
                        inactiveTrackColor = Color.LightGray,
                        inactiveTickColor = Color.LightGray
                    )
                )

                ElevatedCard(
                    onClick = {
                        if (bidQuantity < 5) {
                            onBidQuantityChange(bidQuantity + 1)
                        }
                    },
                    shape = ShapeDefaults.Small,
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White,
                        contentColor = UiColors.Blue
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                LazyRow(userScrollEnabled = false) {
                    items(90) { _ ->
                        Text(
                            text = ".", color = Color.LightGray,
                            modifier = Modifier.padding(1.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "₹${selectedBitAmount * bidQuantity}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Text(
                        text = "You put",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.LightGray
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "₹${10 * bidQuantity}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xff387F39)
                    )
                    Text(
                        text = "You get",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}