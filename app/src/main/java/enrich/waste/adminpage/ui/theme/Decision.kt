package enrich.waste.adminpage.ui.theme

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import enrich.waste.adminpage.MainViewModel
import enrich.waste.adminpage.R
import enrich.waste.adminpage.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Decision(navController: NavController, mainViewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {



        var imageUrlState by remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = Unit) {
            val imageUrl = withContext(Dispatchers.IO) {
                try {
                    getDownloadUrlFromPath(mainViewModel.imagePath.value ?: "")
                } catch (e: Exception) {
                    e.printStackTrace().toString()
                }
            }
            println("imageUrlStates: $imageUrl")
            imageUrlState = imageUrl
        }

        println("imageUrlState: $imageUrlState")



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            TextBig(text = "Reported Waste", size = 23.sp)
        }

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
                        Text(text = mainViewModel.userEmail.value ?: "", modifier = Modifier.padding(15.dp))
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
                    if (imageUrlState != "") {
                        AsyncImage(
                            model = imageUrlState,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(bottom = 30.dp)
                                .clip(RoundedCornerShape(30.dp)),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = mainViewModel.address.value ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                CustomButtom(
                    text = "See Info",
                    onClick = { navController.navigate(route = Screens.Decision.route) })

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            CoinButtom(text = "50")
            CoinButtom(text = "100")
            CoinButtom(text = "-100")
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            FixedButtom(text = "Reported Successfully")
            FixedButtom(text = " Wrong Waste")
        }

    }

}


