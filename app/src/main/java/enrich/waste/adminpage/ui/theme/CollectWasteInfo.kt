package enrich.waste.adminpage.ui.theme

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import enrich.waste.adminpage.R
import enrich.waste.adminpage.navigation.Screens
import kotlin.system.exitProcess

@Composable
fun CollectWasteInfo(navController: NavController) {

    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finishAndRemoveTask()
        exitProcess(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)) {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint =  Color.White ,
                modifier = Modifier
                    .padding(start = 5.dp,)
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
           TextBig(text = "Reported Waste", size = 23.sp)
        }

        Row (modifier = Modifier.fillMaxWidth()){

            LazyColumn() {
                items(5) { listItem ->

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 20.dp, end = 20.dp), ) {
                        Card (modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)){
                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(end = 20.dp),
                               horizontalArrangement = Arrangement.SpaceBetween
                           ){
                               Row {
                                   Icon(
                                       imageVector = Icons.Filled.AccountCircle,
                                       contentDescription = "",
                                       tint = Color.Black,
                                       modifier = Modifier
                                           .padding(start = 15.dp, top = 10.dp)
                                           .size(23.dp)
                                   )
                                   Text(text = "Person", modifier = Modifier.padding(10.dp))
                               }

                               CoinText(
                                   text = "Reported 10 hours ago",
                                   modifier = Modifier.padding(top = 15.dp, start = 80.dp),
                                   textmodifier = Modifier.padding(top = 15.dp),
                                   icon = R.drawable.clock,
                                   iconSize = 25.dp,
                                   textSize = 10.sp
                               )
                           }

                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)){

                                Image(painter = painterResource(id = R.drawable.img), contentDescription = "", modifier = Modifier.clip(RoundedCornerShape(15.dp)) )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(text = "Sies djgybdeskug ukgfjenhgfubd uhhujhf,kdzrhfb huiuehfb")
                            }


                                CustomButtom(text = "See Info", onClick = { navController.navigate(route = Screens.Decision.route)})




                        }


                    }

                  }}


        }

    }
}