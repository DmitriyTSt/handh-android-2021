package ru.dmitriyt.lesson8.presentation.first

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.dmitriyt.lesson8.data.db.DatabaseClient
import ru.dmitriyt.lesson8.data.db.entity.BridgeEntity
import kotlin.random.Random

class FirstViewModel : ViewModel() {
    private val _bridgesLiveData = MutableLiveData<List<BridgeEntity>>()
    val bridgesLiveData: LiveData<List<BridgeEntity>> = _bridgesLiveData

//    fun loadBridges(context: Context) {
//        viewModelScope.launch {
//            try {
//                _bridgesLiveData.postValue(LoadingState.Loading())
//                val bridges = DatabaseClient(context).getBridges()
//                _bridgesLiveData.postValue(LoadingState.Data(bridges))
//            } catch (e: Exception) {
//                _bridgesLiveData.postValue(LoadingState.Error(e))
//            }
//        }
//    }

    fun subscribeToBridges(context: Context) {
        viewModelScope.launch {
            DatabaseClient.getInstance(context).getBridgesFlow().collect {
                _bridgesLiveData.postValue(it)
            }
        }
    }

    fun createBridge(context: Context) {
        val id = Random.nextInt()
        val bridge = BridgeEntity(
            id = id,
            name = "Название $id",
            nameEng = "Name $id"
        )
        viewModelScope.launch {
            DatabaseClient.getInstance(context).saveBridges(listOf(bridge))
        }
    }
}