package com.example.probeclone.questionScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probeclone.R
import com.example.probeclone.homeScreen.Question
import com.example.probeclone.ui.theme.UiColors

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun QuestionScreen(
    onNavigateBack: () -> Unit
) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Event 28997009")
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.IosShare,
                            contentDescription = "back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = UiColors.BackgroundColor
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    shape = ShapeDefaults.ExtraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UiColors.LightBlue,
                        contentColor = UiColors.Blue
                    )
                ) {
                    Text(text = "Yes ₹ ${question.yesAmount}")
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    shape = ShapeDefaults.ExtraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UiColors.LightRed,
                        contentColor = UiColors.Red
                    )
                ) {
                    Text(text = "No ₹ ${question.noAmount}")
                }
            }
        },
        containerColor = UiColors.BackgroundColor
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.bitcoin),
                        contentDescription = "bitcoin",
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(
                                color = Color.LightGray.copy(alpha = 0.5f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = question.tag,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = question.question,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth()
                            .clip(ShapeDefaults.Small)
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bulb),
                            contentDescription = "bulb",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 4.dp)
                        )
                        Text(
                            text = question.tip,
                            modifier = Modifier
                                .weight(1f)
                                .basicMarquee(),
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Light)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "The Market Predicts",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = Color.Gray
                    )
                    Text(
                        text = "60% probability of Yes",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                        color = UiColors.Blue
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    BarWithLineChart()

                    Text(
                        text = "Insights",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Start
                    )

                    ElevatedCard(
                        modifier = Modifier.padding(12.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Column {
                            Text(
                                text = "Check live Crypto price here!",
                                color = Color.Black,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                textAlign = TextAlign.Start
                            )
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .border(
                                        2.dp, UiColors.BackgroundColor,
                                        shape = ShapeDefaults.Small
                                    ),
                                shape = ShapeDefaults.Small
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        text = "https://in.tradingview.com/chart/?symbol=BINANCE%3ABTCusDT",
                                        color = Color.Black,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                    Text(
                                        text = "Updated an hour ago",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(
                        thickness = 12.dp,
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(UiColors.ActivityBackgroundColor)
                    ) {
                        TabRow(
                            containerColor = UiColors.ActivityBackgroundColor,
                            selectedTabIndex = 0,
                            divider = {
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            indicator = { tabPositions ->
                                TabRowDefaults.SecondaryIndicator(
                                    Modifier.tabIndicatorOffset(tabPositions[0]),
                                    color = Color.Black
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Tab(
                                selected = true,
                                onClick = { /*TODO*/ },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            ) {
                                Text(
                                    text = "Activity",
                                    color = Color.Black,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                )
                            }
                            Tab(
                                selected = true,
                                onClick = { /*TODO*/ },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            ) {
                                Text(
                                    text = "Order Book",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                )
                            }

                            Tab(
                                onClick = { /*TODO*/ },
                                selected = false,
                                enabled = false
                            ) {}

                            Tab(
                                onClick = { /*TODO*/ },
                                selected = false,
                                enabled = false
                            ) {}
                        }

                        repeat(6) {
                            ActivityItem()
                        }

                        ElevatedCard(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            shape = ShapeDefaults.Small,
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Show more >",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
                                    modifier = Modifier.padding(6.dp)
                                )
                            }

                        }
                    }


                }

            }

            item {


                HorizontalDivider(
                    thickness = 12.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "About the event",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Start
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Traders",
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "334",
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Started at",
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Aug 15, 2024 8:00 PM",
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Volume",
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "₹28.1K",
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold

                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Ending at",
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Aug 15, 2024 10:00 PM",
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold

                                )
                            }
                        }
                    }




                    HorizontalDivider(color = Color.LightGray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Overview",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "Information about event",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "overview",
                                tint = Color.LightGray
                            )
                        }
                    }

                    HorizontalDivider(color = Color.Gray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Source of truth",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "Information about source of truth",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "overview",
                                tint = Color.LightGray
                            )
                        }
                    }

                    HorizontalDivider(color = Color.Gray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Rules",
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "Terms and conditions",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "overview",
                                tint = Color.LightGray
                            )
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.footer),
                        contentDescription = "footer",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun ActivityItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person",
                    tint = Color.White
                )
            }
            Text(
                text = "Bobby",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(.6f),
                        color = UiColors.LightBlue,
                        thickness = 30.dp
                    )

                    HorizontalDivider(
                        modifier = Modifier.weight(.6f),
                        color = UiColors.LightRed,
                        thickness = 30.dp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "₹6.0",
                        color = UiColors.Blue,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
                    )

                    Text(
                        text = "₹4.0",
                        color = UiColors.Red,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "a minute ago",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium

            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person",
                    tint = Color.White
                )
            }
            Text(
                text = "sverma",
                color = Color.Gray,
                style = MaterialTheme.typography.labelMedium

            )
        }
    }
}