package enrich.waste.adminpage.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import enrich.waste.adminpage.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Adminhome(navHostController: NavHostController) {

    var nameValue = remember { mutableStateOf("") }
    var phoneValue = remember { mutableStateOf("") }
    var passwordValue = remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp), horizontalArrangement = Arrangement.Center) {
              
             TextBig(text = "Admin Page", size =  40.sp)
                
            }
            Row {

                val compnotify by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("profile.json")
                )
                val progress by animateLottieCompositionAsState(compnotify)
                LottieAnimation(
                    composition = compnotify,
                    iterations = Int.MAX_VALUE,
                    isPlaying = true,
                    contentScale = ContentScale.Crop,
                    speed = 1.45f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(45.dp)
                        .padding(top = 170.dp, start = 50.dp, end = 50.dp)
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(
                        rememberScrollState(),
                        flingBehavior = ScrollableDefaults.flingBehavior()
                    )
                    .fillMaxWidth()
                    .padding(top = 170.dp,),
                verticalArrangement = Arrangement.Center,

            ) {
                OutlinedText(value = nameValue.value , text = "Name" , Placeholder = "Name")
                OutlinedText(value = phoneValue.value , text = "Phone Number" , Placeholder = "Phone Number")
                OutlinedText(value = passwordValue.value , text = "Password" , Placeholder = "Password")

            }

            CustomButtom(text = "SIGN IN", onClick = { navHostController.navigate(route = Screens.CollectWaste.route)})




            }}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedText(value: String, text: String, Placeholder: String) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

            OutlinedTextField(
                value = value,
                colors = TextFieldDefaults.textFieldColors(Color.White),
                onValueChange = {
//            value = itL
                },
                label = {

                    Text(
                        text = text,
                        color = Color.Gray,
                    )
                },
                placeholder = {

                    Text(text = Placeholder, fontSize = 10.sp) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 25.dp, end = 25.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
fun CustomButtom(text: String, onClick: () -> Unit ) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {

        Button(
            onClick = {
                  onClick()
            },
            modifier = Modifier.padding(all = 12.dp),
            enabled = true,
            shape = MaterialTheme.shapes.medium
        )
        {
            Text(text = text, color = Color.White)
        }

    }

}

@Composable
fun FixedButtom(text: String) {


        Button(
            onClick = {
            },
            modifier = Modifier
                .padding(all = 12.dp)
                .height(60.dp)
                .width(140.dp),
            enabled = true,
            shape = MaterialTheme.shapes.medium
        )
        {
            Text(text = text, color = Color.White)
        }
}

@Composable
fun CoinButtom(text: String) {
    Button(
        onClick = {
        },
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .padding(top = 3.dp, end = 10.dp),
        enabled = true,
    )
    {
        CoinText(text = text, modifier = Modifier
            .padding(start = 1.dp, top = 5.dp, end = 5.dp)
            .size(20.dp), textmodifier = Modifier)
    }

}

@Composable
fun CoinText(text: String, modifier:Modifier , textmodifier: Modifier) {

    Icon(imageVector = Icons.Filled.AccountCircle,
        contentDescription = "",
        tint =  Color.Black ,
        modifier = modifier

    )
    Text(text = text, color = Color.White, modifier=textmodifier)
}

@Composable
fun TextBig(text: String, size: TextUnit) {
    Text(text = text , fontSize = size, fontWeight = FontWeight.SemiBold, color = Color.White , )
}

