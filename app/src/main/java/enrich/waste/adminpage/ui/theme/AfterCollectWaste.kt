package enrich.waste.adminpage.ui.theme

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import enrich.waste.adminpage.MainViewModel
import enrich.waste.adminpage.R
import enrich.waste.adminpage.dto.CollectedWasteItem
import enrich.waste.adminpage.dto.WasteItem
import enrich.waste.adminpage.dto.getTimeAgo
import enrich.waste.adminpage.navigation.Screens
import kotlinx.coroutines.tasks.await
import kotlin.system.exitProcess

@Composable
fun AfterCollectWaste(navController: NavController, mainViewModel: MainViewModel) {

    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finishAndRemoveTask()
        exitProcess(0)
    }

    var allWastes by remember { mutableStateOf<List<CollectedWasteItem>?>(null) }
    var collectedWastes by remember { mutableStateOf<List<WasteItem>?>(null) }

    JetFirestore(path = {
        collection("TempCollectedWastes")
    }, onRealtimeCollectionFetch = { values, _ ->
        allWastes = values?.getListOfObjects()
    }) {
        JetFirestore(path = {
            collection("AllWastes")
        }, onRealtimeCollectionFetch = { values, _ ->
            collectedWastes = values?.getListOfObjects()
        }) {

            Column(modifier = Modifier.fillMaxSize()) {


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
                            .padding(start = 5.dp,)
                            .size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    TextBig(text = "Collected Waste", size = 23.sp)
                }

                Row(modifier = Modifier.fillMaxWidth()) {

                    LazyColumn() {
                        itemsIndexed(allWastes ?: emptyList()) { index, listItem ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 15.dp, start = 20.dp, end = 20.dp),
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 5.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 20.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Filled.AccountCircle,
                                                contentDescription = "",
                                                tint = Color.Black,
                                                modifier = Modifier
                                                    .padding(start = 15.dp, top = 10.dp)
                                                    .size(23.dp)
                                            )
                                            Text(
                                                text = listItem.userEmail,
                                                modifier = Modifier.padding(10.dp)
                                            )
                                        }

                                        CoinText(
                                            text = getTimeAgo(listItem.timeStamp),
                                            modifier = Modifier.padding(top = 15.dp, start = 80.dp),
                                            textmodifier = Modifier.padding(top = 15.dp),
                                            icon = R.drawable.clock,
                                            iconSize = 25.dp,
                                            textSize = 10.sp
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                    ) {

                                        Spacer(modifier = Modifier.width(15.dp))
                                        Text(text = listItem.address)
                                    }


                                    CustomButtom(
                                        text = "See Info",
                                        onClick = {
                                            mainViewModel.userEmail.value = listItem.userEmail
                                            mainViewModel.address.value = listItem.address
                                            mainViewModel.latitude.value = listItem.latitude
                                            mainViewModel.longitude.value = listItem.longitude
                                            mainViewModel.imagePath.value = listItem.imagePath
                                            mainViewModel.timeStamp.value = listItem.timeStamp
                                            mainViewModel.collectedImagePath.value = listItem.beforeCollectedPath
                                            navController.navigate(route = Screens.AfterCollectedDecision.route)
                                        }
                                    )


                                }


                            }

                        }
                    }


                }

            }
        }
    }
}

fun updateCollectedWasteToFirebase(
    context: Context,
    latitude: Double,
    longitude: Double,
    imagePath: String,
    timeStamp: Long,
    userEmail: String,
    address: String,
    isWasteCollected: Boolean,
    allWasteCollected: Boolean,
    feedBack: String,
    beforeCollectedPath: String? = null
) {
    val wasteItem = CollectedWasteItem(
        latitude,
        longitude,
        imagePath,
        timeStamp,
        userEmail,
        address,
        isWasteCollected,
        allWasteCollected,
        feedBack,
        beforeCollectedPath
    )

    val db = FirebaseFirestore.getInstance()
    timeStamp.let {
        db.collection("TempCollectedWastes").document(it.toString()).set(wasteItem)
            .addOnSuccessListener {

                Toast.makeText(context, "Waste Reported Successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    "Fail to Report Waste " + exception.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }


}




