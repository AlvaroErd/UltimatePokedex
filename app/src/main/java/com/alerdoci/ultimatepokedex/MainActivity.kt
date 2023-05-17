package com.alerdoci.ultimatepokedex

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.alerdoci.ultimatepokedex.app.common.conectivity.ConnectivityObserver
import com.alerdoci.ultimatepokedex.app.common.conectivity.NetworkConnectivityObserver
import com.alerdoci.ultimatepokedex.app.screens.splash.viewmodel.SplashViewModel
import com.alerdoci.ultimatepokedex.app.theme.UltimatePokedexTheme
import com.alerdoci.ultimatepokedex.navigation.SetupNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        installSplashScreen().apply {
            Thread.sleep(2100)
            this.setKeepOnScreenCondition {
                !splashViewModel.isLoading.value
            }
        }

        setContent {
            UltimatePokedexTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()

                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Available
                )

                var previousStatus by remember { mutableStateOf(status) }

                if (status != previousStatus) {
                    previousStatus = status
                    val networkStatusText = "Network status: $status"
                    Toast.makeText(applicationContext, networkStatusText, Toast.LENGTH_SHORT).show()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    SetupNavGraph(navController = navController, startDestination = screen)
                }
            }
        }
    }
}