package com.dgopadakak.qrscanner.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dgopadakak.qrscanner.MainViewModel
import com.dgopadakak.qrscanner.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanQrFragment : Fragment(R.layout.scan_fragment), ZXingScannerView.ResultHandler {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var scannerView: ZXingScannerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scannerView = view.findViewById<ZXingScannerView?>(R.id.zxing_scanner_view).apply {
            setFormats(listOf(BarcodeFormat.QR_CODE))
        }

        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
            viewModel.onQrCancel()
        }
    }

    override fun onResume() {
        super.onResume()

        scannerView.apply {
            setResultHandler(this@ScanQrFragment)
            startCamera()
        }
    }

    override fun onPause() {
        super.onPause()

        scannerView.stopCamera()
    }

    override fun handleResult(p0: Result?) {
        viewModel.onQrResult(p0?.text ?: "null")
    }
}
