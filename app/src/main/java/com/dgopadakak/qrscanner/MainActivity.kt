package com.dgopadakak.qrscanner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.dgopadakak.qrscanner.fragments.DefaultFragment
import com.dgopadakak.qrscanner.fragments.ScanQrFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var currentFragment: FragmentVariants = FragmentVariants.DefaultFragment
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentById(R.id.main_fragment_container) == null) {
            fragmentManager.commit {
                add<DefaultFragment>(R.id.main_fragment_container)
            }
        }
        createFragmentsListener()
    }

    private fun createFragmentsListener() {
        lifecycleScope.launch {
            viewModel.neededFragmentStateFlow.collect { neededFragment ->
                if (neededFragment != currentFragment) {
                    when (neededFragment) {
                        is FragmentVariants.DefaultFragment -> {
                            supportFragmentManager.commit {
                                replace<DefaultFragment>(R.id.main_fragment_container)
                            }
                        }

                        is FragmentVariants.QrScanFragment -> {
                            supportFragmentManager.commit {
                                replace<ScanQrFragment>(R.id.main_fragment_container)
                            }
                        }
                    }

                    currentFragment = neededFragment
                }
            }
        }
    }
}

sealed class FragmentVariants() {
    data object DefaultFragment : FragmentVariants()
    data object QrScanFragment : FragmentVariants()
}
