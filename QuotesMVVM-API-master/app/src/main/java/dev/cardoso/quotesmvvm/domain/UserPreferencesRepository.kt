package dev.cardoso.quotesmvvm.domain

import android.content.Context
import android.media.session.MediaSession
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val USER_DATASTORE = "preferencesquotesmvvm"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class UserPreferencesRepository(val context: Context) {

    companion object{
        val TOKEN = stringPreferencesKey("TOKEN")
    }

    suspend fun saveTokenToDataStore(token: String){
        context.dataStore.edit{
            it[TOKEN]= token
        }
    }

    suspend fun getTokenFromDataStore(): Flow<String> = context.dataStore.data.map{
            preferences -> preferences[TOKEN] ?:""
        }


}