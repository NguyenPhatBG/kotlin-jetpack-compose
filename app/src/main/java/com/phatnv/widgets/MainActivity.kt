/**
 * Gmail: dev.phatnv@gmail.com
 * Github: https://github.com/NguyenPhatBG
 * Resource: https://github.com/NguyenPhatBG/kotlin-jetpack-compose
 */

package com.phatnv.widgets

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatnv.widgets.presentation.MainScreen
import com.phatnv.widgets.theme.AppTheme
import com.phatnv.widgets.utils.NetworkChangeReceiver
import com.shashank.sony.fancytoastlib.FancyToast
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
            AppTheme {
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
        FancyToast.makeText(
            this,
            "Internet connection is available.",
            FancyToast.LENGTH_LONG,
            FancyToast.INFO,
            false,
        ).show()
    }

    override fun onNetworkLost() {
        // Perform actions when the internet connection is lost
        FancyToast.makeText(
            this,
            "No internet connection.",
            FancyToast.LENGTH_LONG,
            FancyToast.ERROR,
            false,
        ).show()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        var isNetworkAvailable = true
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.i("TAG", "Hello World 1 $network")
            if (!isNetworkAvailable) {
                onNetworkAvailable()
                isNetworkAvailable = true
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.i("TAG", "Hello World 2 $network")
            onNetworkLost()
            isNetworkAvailable = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    @Preview
    @Composable
    fun ShowMain() {
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()
            }
        }
    }
}