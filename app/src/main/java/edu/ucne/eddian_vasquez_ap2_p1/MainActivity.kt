package edu.ucne.eddian_vasquez_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.navigation.BeerTrackerNavHost
import edu.ucne.eddian_vasquez_ap2_p1.ui.theme.Eddian_Vasquez_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Eddian_Vasquez_AP2_P1Theme {

                val navController = rememberNavController()


                BeerTrackerNavHost(navController = navController)
            }
        }
    }
}