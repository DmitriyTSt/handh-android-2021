package ru.dmitriyt.lesson12.data.model

sealed class BridgeListState {
    object Loading : BridgeListState()
    class Data(val bridges: List<Bridge>) : BridgeListState()
    class Error(val exception: Exception) : BridgeListState()
}