package com.dgopadakak.qrscanner

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _neededFragmentStateFlow = MutableStateFlow<FragmentVariants>(FragmentVariants.DefaultFragment)
    val neededFragmentStateFlow = _neededFragmentStateFlow.asStateFlow()

    var result = "Result will be here"
        private set

    fun goScan() {
        _neededFragmentStateFlow.value = FragmentVariants.QrScanFragment
    }

    fun onQrResult(result: String) {
        this.result = result
        _neededFragmentStateFlow.value = FragmentVariants.DefaultFragment
    }

    fun onQrCancel() {
        result = "Canceled"
        _neededFragmentStateFlow.value = FragmentVariants.DefaultFragment
    }
}
