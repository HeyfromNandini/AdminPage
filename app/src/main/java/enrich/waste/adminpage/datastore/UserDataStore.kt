package enrich.waste.adminpage.datastore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("pref")

class UserDataStore(private val context: Context) {
    companion object {
        val isLoggedIn = booleanPreferencesKey("isLoggedIn")
    }

    val getLogIn: Flow<Boolean> = context.datastore.data.map {
        it[isLoggedIn] ?: false
    }

    suspend fun saveLogIn(loggedIn: Boolean) {
        context.datastore.edit {
            it[isLoggedIn] = loggedIn
        }
    }
}