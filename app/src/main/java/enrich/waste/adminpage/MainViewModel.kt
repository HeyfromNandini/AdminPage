package enrich.waste.adminpage

import android.app.Application
import android.content.Context
import android.nfc.Tag
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enrich.waste.adminpage.datastore.UserDataStore
import enrich.waste.adminpage.dto.TagWithoutTips
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
    var userEmail: MutableState<String?> = mutableStateOf(null)
    var latitude: MutableState<Double?> = mutableStateOf(null)
    var longitude: MutableState<Double?> = mutableStateOf(null)
    var timeStamp: MutableState<Long?> = mutableStateOf(null)
    var imagePath: MutableState<String?> = mutableStateOf(null)
    var tag: MutableState<List<TagWithoutTips>?> = mutableStateOf(null)
    var address: MutableState<String?> = mutableStateOf(null)

    init {
        val dataStore = UserDataStore(application.applicationContext)
        viewModelScope.launch {
            dataStore.getLogIn.collectLatest {
                isLoggedIn.value = it
            }
        }

    }

}