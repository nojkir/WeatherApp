package pl.nojkir.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

import java.io.IOException

const val USER_PREFERENCES = "user_preferences"

class UserPreferencesRepository(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = USER_PREFERENCES
    )

    companion object PreferencesKeys{
        val CITY= preferencesKey<String>("city")
    }



    suspend fun saveToDataStore (city: String){
        dataStore.edit {
            it[PreferencesKeys.CITY] = city
        }
    }

    val readFromDataStore: Flow <String> = dataStore.data
        .catch { exception ->
            if (exception is IOException){
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            }else{
                throw exception
            }

        }.map { preference ->
            val myCity = preference[PreferencesKeys.CITY]?: "Default"
            myCity
        }


}