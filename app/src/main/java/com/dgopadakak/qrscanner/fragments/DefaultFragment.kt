package com.dgopadakak.qrscanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dgopadakak.qrscanner.MainViewModel

class DefaultFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold { paddingValues ->
                    DefaultFragmentScreen(
                        modifier = Modifier
                            .padding(paddingValues)
                    )
                }
            }
        }
    }

    @Composable
    private fun DefaultFragmentScreen(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectionContainer {
                Text(text = viewModel.result)
            }
            Button(
                modifier = Modifier
                    .padding(top = 8.dp),
                onClick = { viewModel.goScan() }
            ) { Text(text = "Go Scan") }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    private fun DefaultFragmentScreenPreview() {
        DefaultFragmentScreen()
    }
}
