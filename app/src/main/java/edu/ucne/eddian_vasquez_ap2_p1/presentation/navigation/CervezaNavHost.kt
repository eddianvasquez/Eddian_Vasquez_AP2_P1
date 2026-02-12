package edu.ucne.eddian_vasquez_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.eddian_vasquez_ap2_p1.presentation.registro.edit.CervezaEditScreen
import edu.ucne.eddian_vasquez_ap2_p1.presentation.registro.list.CervezaListScreen


@Composable
fun BeerTrackerNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CervezaList
    ) {

        composable<Screen.CervezaList> {
            CervezaListScreen(
                onAdd = {

                    navController.navigate(Screen.CervezaEdit(0))
                },
                onEdit = { id ->

                    navController.navigate(Screen.CervezaEdit(id))
                }
            )
        }


        composable<Screen.CervezaEdit> {
            CervezaEditScreen(
                goBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}