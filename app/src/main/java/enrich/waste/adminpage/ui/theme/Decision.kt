package enrich.waste.adminpage.ui.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection.Companion.In
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import enrich.waste.adminpage.R
import enrich.waste.adminpage.navigation.Screens

@Composable
fun Decision(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        MainHeading(text = "Reported Waste")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 25.dp, end = 25.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp), Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 15.dp)
                                .size(25.dp)
                        )
                        Text(text = "Person", modifier = Modifier.padding(15.dp))
                    }

                    CoinText(
                        text = "100",
                        modifier = Modifier.padding(top = 15.dp, start = 80.dp),
                        textmodifier = Modifier.padding(top = 15.dp),
                        icon = R.drawable.coins,
                        textSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = "Sies djgybdeskug ukgfjenhgfubd uhhujhf,kdzrhfb huiuehfb",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "No. of times wrong reported wrong waste:",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "0",
                        fontSize = 15.sp, modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {

//                    CustomButton(
//                        text = "See Info",
//                        onClick = {
//                            navController.navigate(route = Screens.Decision.route)
//                        }
//                    )
                    Tags(text = "Biodegradable") {

                    }
                }


            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            CoinButton(text = "50")
            CoinButton(text = "100")
            CoinButton(text = "-100")
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Button(text = "Accept")

            Button(text = " Reject")

        }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 20.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            var counter by remember { mutableStateOf(1) }
//
//            val compnotify by rememberLottieComposition(
//                spec = LottieCompositionSpec.Asset("profile.json")
//            )
//            val progress by animateLottieCompositionAsState(compnotify)
//            LottieAnimation(
//                composition = compnotify,
//                iterations = Int.MAX_VALUE,
//                isPlaying = true,
//                contentScale = ContentScale.Crop,
//                speed = 1.45f,
//                modifier = Modifier
//
//                    .size(50.dp)
//                    .padding(5.dp)
//            )
//            Text(text = "Wrong Waste", fontSize = 25.sp)
//            Spacer(modifier = Modifier.width(20.dp))
//            OutlinedCard {
//                Row() {
//                    Row(
//                        modifier = Modifier
//                            .padding(top = 3.dp, start = 3.dp)
//                            .fillMaxWidth(0.1f),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(text = counter.toString())
//                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Row {
//                        Column {
//                            Icon(
//                                imageVector = Icons.Filled.KeyboardArrowUp,
//                                contentDescription = "",
//                                tint = Color.White,
//                                modifier = Modifier
//                                    .padding(start = 5.dp)
//                                    .size(25.dp)
//                                    .clickable { if (counter > 1) counter-- }
//                            )
//                            Icon(
//                                imageVector = Icons.Filled.KeyboardArrowDown,
//                                contentDescription = "",
//                                tint = Color.White,
//                                modifier = Modifier
//                                    .padding(start = 5.dp)
//                                    .size(25.dp)
//                                    .clickable { if (counter < 3) counter++ }
//                            )
//                        }
//                    }
//
//
//                }
//
//            }
//
//        }


    }

}


//@Composable
//fun BigButton(text: String) {
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 30.dp, end = 30.dp)
//    ) {
//
//
//        Button(
//            onClick = {
//            },
//            modifier = Modifier
//                .padding(all = 12.dp)
//                .height(50.dp)
//                .fillMaxWidth(),
//            enabled = true,
//            shape = MaterialTheme.shapes.medium
//        )
//        {
//            Text(text = text, color = Color.White, fontSize = 15.sp)
//        }
//    }
//}

//@Composable
//fun CounterButton(text: String, onClick: () -> Unit) {
//    BasicTextField(
//        value = TextFieldValue(text = text),
//        onValueChange = { },
//        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp), // Increase the font size here
//        modifier = Modifier
//            .clickable { onClick() }
//            .padding(8.dp)
//    )
//}
//
//@Composable
//fun Counterr() {
//    var counter by remember { mutableStateOf(1) }
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        CounterButton(
//            text = "<",
//            onClick = { if (counter > 1) counter-- }
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Text(
//            text = counter.toString(),
//            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 36.sp), // Increase the font size here
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        CounterButton(
//            text = ">",
//            onClick = { if (counter < 3) counter++ }
//        )
//    }
//}





