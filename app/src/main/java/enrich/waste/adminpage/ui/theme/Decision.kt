package enrich.waste.adminpage.ui.theme

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import enrich.waste.adminpage.MainViewModel
import enrich.waste.adminpage.R
import enrich.waste.adminpage.dto.ProfileInfo
import enrich.waste.adminpage.dto.TagWithoutTips
import enrich.waste.adminpage.dto.WasteItem
import enrich.waste.adminpage.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Decision(navController: NavController, mainViewModel: MainViewModel) {
    var profileList by remember {
        mutableStateOf<List<ProfileInfo>?>(null)
    }
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var userAddress by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var organization by remember {
        mutableStateOf("")
    }
    var pointsEarned by remember {
        mutableStateOf(0)
    }
    var pointsRedeemed by remember {
        mutableStateOf(0)
    }
    var noOfTimesReported by remember {
        mutableStateOf(0)
    }
    var noOfTimesCollected by remember {
        mutableStateOf(0)
    }
    var noOfTimesActivity by remember {
        mutableStateOf(0)
    }
    var communities by remember {
        mutableStateOf(mutableListOf(""))
    }
    var maxReported by remember {
        mutableStateOf(0)
    }
    var maxCollected by remember {
        mutableStateOf(0)
    }
    var maxCommunity by remember {
        mutableStateOf(0)
    }
    val choosePoints = remember {
        mutableStateOf(0)
    }

    BackHandler {
        mainViewModel.userEmail.value = null
        mainViewModel.address.value = null
        mainViewModel.latitude.value = null
        mainViewModel.longitude.value = null
        mainViewModel.imagePath.value = null
        mainViewModel.timeStamp.value = null
        mainViewModel.tag.value = null
        navController.popBackStack()
    }

    JetFirestore(path = {
        collection("ProfileInfo")
    }, onRealtimeCollectionFetch = { value, _ ->
        profileList = value?.getListOfObjects()
        maxReported = (profileList?.map { it.noOfTimesReported.toDouble() } ?: emptyList())
            .max().toInt()

        maxCollected = (profileList?.map { it.noOfTimesCollected.toDouble() } ?: emptyList())
            .max().toInt()
        maxCommunity = (profileList?.map { it.communities.size.toDouble() } ?: emptyList())
            .max().toInt()
    }) {
        if (profileList != null) {
            for (i in profileList!!) {
                if (i.email == mainViewModel.userEmail.value) {
                    userAddress = i.address ?: ""
                    gender = i.gender ?: ""
                    phoneNumber = i.phoneNumber ?: ""
                    organization = i.organization ?: ""
                    pointsEarned = i.pointsEarned
                    pointsRedeemed = i.pointsRedeemed
                    noOfTimesReported = i.noOfTimesReported
                    noOfTimesCollected = i.noOfTimesCollected
                    noOfTimesActivity = i.noOfTimesActivity
                    name = i.name ?: ""
                    email = i.email ?: ""
                    communities = i.communities.toMutableList()
                }
            }
        }

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
            val context = LocalContext.current


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
                            Text(
                                text = mainViewModel.userEmail.value ?: "",
                                modifier = Modifier.padding(15.dp)
                            )
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
                        onClick = {
                            navController.navigate(route = Screens.Decision.route)
                        }
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val points1 = (calculatePointsEarned(
                    noOfTimesReported,
                    noOfTimesCollected,
                    noOfTimesActivity,
                    isCollectedWaste = true,
                    maxReportedValue = maxReported,
                    maxCollectedValue = maxCollected,
                    maxCommunitiesJoinedValue = maxCommunity
                ) + (-10..10).random())

                val points2 = (calculatePointsEarned(
                    noOfTimesReported,
                    noOfTimesCollected,
                    noOfTimesActivity,
                    isCollectedWaste = true,
                    maxReportedValue = maxReported,
                    maxCollectedValue = maxCollected,
                    maxCommunitiesJoinedValue = maxCommunity
                ) + (0..40).random())

                val points3 = (calculatePointsEarned(
                    noOfTimesReported,
                    noOfTimesCollected,
                    noOfTimesActivity,
                    isCollectedWaste = true,
                    maxReportedValue = maxReported,
                    maxCollectedValue = maxCollected,
                    maxCommunitiesJoinedValue = maxCommunity
                ) + (0..30).random())

                CoinButtom(text = points1.toString()) {
                    choosePoints.value = points1
                }
                CoinButtom(text = points2.toString()){
                    choosePoints.value = points2
                }
                CoinButtom(text = points3.toString()) {
                    choosePoints.value = points3
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FixedButtom(text = "Reported Successfully") {
                    updateWasteToFirebase(
                        context = context,
                        address = mainViewModel.address.value ?: "",
                        latitude = mainViewModel.latitude.value ?: 0.0,
                        longitude = mainViewModel.longitude.value ?: 0.0,
                        imagePath = mainViewModel.imagePath.value ?: "",
                        timeStamp = mainViewModel.timeStamp.value ?: 0,
                        userEmail = mainViewModel.userEmail.value ?: "",
                        tags = mainViewModel.tag.value ?: emptyList()
                    )
                    updateInfoToFirebase(
                        context,
                        name = name,
                        email = email,
                        phoneNumber = phoneNumber,
                        gender = gender,
                        organization = organization,
                        address = userAddress,
                        pointsEarned = pointsEarned + choosePoints.value,
                        pointsRedeemed = pointsRedeemed,
                        noOfTimesReported = noOfTimesReported,
                        noOfTimesCollected = noOfTimesCollected + 1,
                        noOfTimesActivity = noOfTimesActivity,
                        communities = communities


                    )

                }
                FixedButtom(text = " Wrong Waste")
            }

        }
    }

}


fun updateWasteToFirebase(
    context: Context,
    latitude: Double,
    longitude: Double,
    imagePath: String,
    timeStamp: Long,
    userEmail: String,
    address: String,
    tags: List<TagWithoutTips> = emptyList(),
) {
    val wasteItem = WasteItem(
        latitude, longitude, imagePath, timeStamp, userEmail, address, tags
    )

    val db = FirebaseFirestore.getInstance()
    timeStamp.let {
        db.collection("AllWastes").document(it.toString()).set(wasteItem)
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

fun updateInfoToFirebase(
    context: Context,
    name: String?,
    email: String?,
    phoneNumber: String?,
    gender: String?,
    organization: String?,
    address: String?,
    pointsEarned: Int,
    pointsRedeemed: Int,
    noOfTimesReported: Int = 0,
    noOfTimesCollected: Int = 0,
    noOfTimesActivity: Int = 0,
    communities: List<String> = emptyList(),
) {
    val profile = ProfileInfo(
        name,
        email,
        phoneNumber,
        gender,
        organization,
        address,
        pointsEarned,
        pointsRedeemed,
        noOfTimesReported,
        noOfTimesCollected,
        noOfTimesActivity,
        communities
    )

    val db = FirebaseFirestore.getInstance()
    email?.let {
        db.collection("ProfileInfo").document(it).set(profile)
            .addOnSuccessListener {

                Toast.makeText(context, "Profile Updated successfully..", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    "Fail to update Profile : " + exception.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
    }

}

fun calculatePointsEarned(
    noOfTimesReported: Int,
    noOfTimesCollected: Int,
    noOfCommunitiesJoined: Int,
    maxReportedValue: Int,
    maxCollectedValue: Int,
    maxCommunitiesJoinedValue: Int,
    isCollectedWaste: Boolean = false,
): Int {
    // Calculate percentiles for each input variable
    val percentileReported = noOfTimesReported.toDouble() / maxReportedValue.toDouble()
    val percentileCollected = noOfTimesCollected.toDouble() / maxCollectedValue.toDouble()
    val percentileCommunitiesJoined =
        noOfCommunitiesJoined.toDouble() / maxCommunitiesJoinedValue.toDouble()

    // Calculate the overall percentile as the average of the individual percentiles
    val overallPercentile =
        (percentileReported + percentileCollected + percentileCommunitiesJoined) / 4.0

    // Scale the percentile to points between 5 and 20
    val scaledPoints = (overallPercentile * 15.0 + 5.0).toInt()
    return if (isCollectedWaste) scaledPoints.coerceIn(5, 20) + 5 else scaledPoints.coerceIn(5, 50)
}


