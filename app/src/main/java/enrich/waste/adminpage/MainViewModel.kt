package enrich.waste.adminpage

import android.app.Application
import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import enrich.waste.adminpage.datastore.UserDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var isLoggedIn: MutableState<Boolean> = mutableStateOf(false)

    init {
        val dataStore = UserDataStore(application.applicationContext)
        viewModelScope.launch {
            dataStore.getLogIn.collectLatest {
                isLoggedIn.value = it
            }
        }

    }

}