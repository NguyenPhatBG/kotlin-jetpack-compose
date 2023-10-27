package com.phatnv.widgets

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnv.widgets.presentation.MainScreen
import com.phatnv.widgets.theme.AppTheme
import com.phatnv.widgets.utils.NetworkChangeReceiver
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), NetworkChangeReceiver.OnNetworkChangeListener {
    private val viewModel: SplashScreenViewModel by viewModels()
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.loading.value
            }
        }

        // check network connection
        networkChangeReceiver = NetworkChangeReceiver()
        networkChangeReceiver.onNetworkChangeListener = this
        connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        setContent {
            AppTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    class SplashScreenViewModel : ViewModel() {
        private val _loading = MutableStateFlow(true)
        val loading = _loading.asStateFlow()

        init {
            viewModelScope.launch {
                // run background task here
                delay(2000)
                _loading.value = false
            }
        }
    }

    override fun onNetworkAvailable() {
        // Perform actions when the internet connection is available again
        Toast.makeText(this, "Internet connection is available.", Toast.LENGTH_SHORT).show()
    }

    override fun onNetworkLost() {
        // Perform actions when the internet connection is lost
        Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onNetworkAvailable()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onNetworkLost()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}