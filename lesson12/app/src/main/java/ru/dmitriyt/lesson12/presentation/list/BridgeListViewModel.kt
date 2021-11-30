package ru.dmitriyt.lesson12.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmitriyt.lesson12.data.model.BridgeListState
import ru.dmitriyt.lesson12.data.repository.BridgesRepository

class BridgeListViewModel : ViewModel() {
    private val bridgesRepository = BridgesRepository()

    /** Мосты */
    private val _bridgesLiveData = MutableLiveData<BridgeListState>()
    val bridgesLiveData: LiveData<BridgeListState> = _bridgesLiveData

    fun loadBridges() {
        viewModelScope.launch {
            _bridgesLiveData.postValue(BridgeListState.Loading)
            try {
                val bridges = bridgesRepository.getBridges()
                _bridgesLiveData.postValue(BridgeListState.Data(bridges))
            } catch (e: Exception) {
                _bridgesLiveData.postValue(BridgeListState.Error(e))
            }
        }
    }
}