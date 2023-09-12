package enrich.waste.adminpage.ui.theme

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import enrich.waste.adminpage.R
import enrich.waste.adminpage.datastore.UserDataStore
import enrich.waste.adminpage.dto.Credentials
import enrich.waste.adminpage.navigation.Screens
import enrich.waste.adminpage.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Adminhome(navHostController: NavHostController) {

    var nameValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var adminCredentials by remember { mutableStateOf<List<Credentials>?>(null) }
    val context = LocalContext.current
    val dataStore = UserDataStore(context = context)
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finishAndRemoveTask()
        exitProcess(0)
    }

    LaunchedEffect(key1 = isLoading) {
        delay(2000)
        if (isLoading) {
            if (nameValue.isNotEmpty() && passwordValue.isNotEmpty()) {
                val correctUserName = adminCredentials?.find {
                    it.username == nameValue.substringBefore(" ")
                }
                val correctPassword =
                    adminCredentials?.find { it.password == passwordValue.substringBefore(" ") }
                if (correctUserName != null && correctPassword != null) {
                    isLoading = false
                    runBlocking {
                        dataStore.saveLogIn(true)
                        navHostController.popBackStack()
                        navHostController.navigate(Screens.CollectWaste.route)
                    }
                } else {
                    isLoading = false
                    Toast.makeText(context, "Enter Correct Value", Toast.LENGTH_SHORT)
                        .show()
                    dataStore.saveLogIn(false)
                }
            } else {
                isLoading = false
                Toast.makeText(context, "Enter Value Soon", Toast.LENGTH_SHORT)
                    .show()
                dataStore.saveLogIn(false)
            }
        }
    }

    JetFirestore(path = {
        collection("AdminCredentials")
    }, onRealtimeCollectionFetch = { values, _ ->
        adminCredentials = values?.getListOfObjects()
        adminCredentials?.forEach {
            Log.i("LoginCredentials", "Adminhomesss Username: ${it.username}")
            Log.i("LoginCredentials", "Adminhomesss Password: ${it.password}")
        }
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (isLoading) Modifier.blur(10.dp) else Modifier)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp), horizontalArrangement = Arrangement.Center
                ) {

                    TextBig(text = "Admin Page", size = 40.sp)

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
                        .padding(top = 170.dp),
                    verticalArrangement = Arrangement.Center,

                    ) {
                    OutlinedText(
                        value = nameValue,
                        text = "Name",
                        Placeholder = "Name"
                    ) {
                        nameValue = it
                    }
                    OutlinedText(
                        value = passwordValue,
                        text = "Password",
                        Placeholder = "Password"
                    ) {
                        passwordValue = it
                    }

                }
                CustomButton(
                    text = "SIGN IN",
                    onClick = {
                        Log.i("Adminhome", "Adminhomesss: $nameValue")
                        Log.i("Adminhome", "Adminhomesss Pass: $passwordValue")
                        isLoading = true
                    }

                )

            }
            if (isLoading) {
                LoadingAnimation()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedText(
    value: String,
    text: String,
    Placeholder: String,
    onValueChange: (String) -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = value,
            colors = TextFieldDefaults.textFieldColors(Color.White),
            onValueChange = {
                onValueChange(it)
            },
            label = {

                Text(
                    text = text,
                    color = Color.Gray,
                )
            },
            placeholder = {

                Text(text = Placeholder, fontSize = 10.sp)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 25.dp, end = 25.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}



@Composable
fun Button(text: String) {


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
fun CoinButton(text: String) {
    Button(
        onClick = {
        },
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .padding(top = 3.dp, end = 10.dp),
        enabled = true,
    )
    {
        CoinText(
            text = text,
            modifier = Modifier
                .padding(start = 1.dp, top = 5.dp, end = 5.dp)
                .size(20.dp),
            textmodifier = Modifier,
            icon = R.drawable.coins
        )
    }

}

@Composable
fun CoinText(
    text: String,
    modifier: Modifier,
    textmodifier: Modifier,
    icon: Int,
    textSize: TextUnit = 10.sp,
    iconSize: Dp = 20.dp
) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painterResource(id = icon),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = modifier.size(iconSize)

        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            color = Color.White,
            modifier = textmodifier,
            fontSize = textSize,
            softWrap = true
        )
    }
}

@Composable
fun TextBig(text: String, size: TextUnit) {
    Text(text = text, fontSize = size, fontWeight = FontWeight.SemiBold, color = Color.White)
}


@Composable
fun LoadingAnimation() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val compnotify by rememberLottieComposition(
            spec = LottieCompositionSpec.Asset("loading.json")
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

        )
    }
}
