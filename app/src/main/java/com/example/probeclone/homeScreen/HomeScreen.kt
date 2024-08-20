package com.example.probeclone.homeScreen

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.probeclone.R
import com.example.probeclone.ui.theme.UiColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    question: Question,
    onCardClicked: () -> Unit,
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
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
                },
                actions = {
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = UiColors.BackgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccountBalanceWallet,
                                contentDescription = "wallet"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        modifier = Modifier.padding(end = 8.dp)

                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = UiColors.BackgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "notification"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = UiColors.BackgroundColor)
            )
        },
        bottomBar = {
            HomeScreenBottomBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = UiColors.BackgroundColor)
        ) {
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CategoryCard(
                            modifier = Modifier.weight(1f),
                            title = "Cricket",
                            image = R.drawable.cricket
                        )
                        CategoryCard(
                            modifier = Modifier.weight(1f),
                            title = "Crypto",
                            image = R.drawable.bitcoin
                        )
                        CategoryCard(
                            modifier = Modifier.weight(1f),
                            title = "News",
                            image = R.drawable.news
                        )
                        CategoryCard(
                            modifier = Modifier.weight(1f),
                            title = "Show more",
                            image = R.drawable.show_more
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalBanners()

                    Text(
                        text = "Trending Now",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp),
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelSmall
                    )

                    TrendingItemsList()

                }
            }

            items(8) {
                QuestionCard(
                    question = question,
                    onCardClicked = onCardClicked,
                    onYesClicked = onYesClicked,
                    onNoClicked = onNoClicked
                )
            }
        }
    }
}

@Composable
fun QuestionCard(
    question: Question,
    onCardClicked: () -> Unit,
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = UiColors.CardColor),
        onClick = onCardClicked,
        shape = ShapeDefaults.ExtraSmall
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Group,
                    contentDescription = "icon",
                    modifier = Modifier.size(15.dp),
                )
                Text(
                    text = "${question.totalTraders} traders >",
                    fontSize = 8.sp
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = question.question,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp),
                    style = MaterialTheme.typography.bodyMedium,

                    )
                Image(
                    painter = painterResource(id = question.image),
                    contentDescription = question.tag,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = question.tip,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W400)
                )
                Text(
                    text = " Read More",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W400),
                    color = UiColors.Blue
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onYesClicked,
                    modifier = Modifier.weight(1f),
                    shape = ShapeDefaults.ExtraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UiColors.LightBlue,
                        contentColor = UiColors.Blue
                    )
                ) {
                    Text(text = "Yes ₹ ${question.yesAmount}")
                }

                Button(
                    onClick = onNoClicked,
                    modifier = Modifier.weight(1f),
                    shape = ShapeDefaults.ExtraSmall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UiColors.LightRed,
                        contentColor = UiColors.Red
                    )
                ) {
                    Text(text = "No ₹ ${question.noAmount}")
                }
            }
        }
    }
}

@Composable
fun TrendingItemsList() {
    val trendingItems = listOf(
        Pair("Bitcoin", R.drawable.bitcoin),
        Pair("Ethereum", R.drawable.ethereum),
        Pair("KKR v/s MI", R.drawable.ipl_logo),
        Pair("Business News", R.drawable.rocket),
        Pair("Ethereum", R.drawable.ethereum),
        Pair("Bitcoin", R.drawable.bitcoin),
        Pair("RCB v/s CSK", R.drawable.ipl_logo),
        Pair("Ethereum", R.drawable.ethereum),
        Pair("Business", R.drawable.rocket),
        Pair("CSK v/s MI", R.drawable.ipl_logo),
        Pair("Ethereum", R.drawable.ethereum),
        Pair("Business", R.drawable.rocket),
    )

    LazyHorizontalStaggeredGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        rows = StaggeredGridCells.Fixed(2),
        horizontalItemSpacing = 12.dp,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(trendingItems) { (title, image) ->
            TrendingItemsListItem(title = title, image = image)
        }
    }
}

@Composable
fun TrendingItemsListItem(
    title: String,
    @DrawableRes image: Int
) {
    AssistChip(
        onClick = { /*TODO*/ },
        label = { Text(text = title) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = image),
                contentDescription = title,
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = UiColors.CardColor
        ),
        shape = ShapeDefaults.Small,
        modifier = Modifier.height(80.dp),
        border = null
    )
}

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes image: Int
) {

    Card(
        modifier = modifier.padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(containerColor = UiColors.CardColor),
        onClick = { /*TODO*/ },
        shape = ShapeDefaults.ExtraSmall
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = image),
                contentDescription = "cricket_ball"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalBanners() {
    val pagerState = rememberPagerState(pageCount = { 4 })

    //Auto scroll feature for banners
    LaunchedEffect(key1 = pagerState.settledPage) {
        launch {
            delay(3000)
            with(pagerState) {
                //Target will move between 0 - pageSize-1
                val target = (currentPage + 1) % pageCount

                animateScrollToPage(
                    page = target,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        //  contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        RacingBanner()
    }
}

@Composable
fun RacingBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp)
            .clip(ShapeDefaults.ExtraSmall)


    ) {
        Image(
            painter = painterResource(id = R.drawable.racing),
            contentDescription = "racing",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.4f),
                blendMode = BlendMode.Hardlight
            ),
            modifier = Modifier.fillMaxSize()

        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Indian Racing Championship",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(4.dp))

                Box(
                    modifier = Modifier
                        .background(Color.Red, ShapeDefaults.ExtraSmall)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "LIVE", color = Color.White,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(Color.White, ShapeDefaults.ExtraSmall)
                    .padding(4.dp)
            ) {
                Text(
                    text = "Tap Now!",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}


