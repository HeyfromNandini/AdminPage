package enrich.waste.adminpage.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import enrich.waste.adminpage.ui.theme.Adminhome
import enrich.waste.adminpage.ui.theme.CollectWasteInfo
import enrich.waste.adminpage.ui.theme.Decision

@Composable
fun MainNavController() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screens.Admin.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300),
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300),
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300),
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300),
            )
        },

        ) {
        composable(Screens.Admin.route) {
           Adminhome(navHostController = navHostController)
        }

        composable(Screens.CollectWaste.route) {
           CollectWasteInfo(navHostController)
        }

        composable(Screens.Decision.route) {
            Decision(navController = navHostController)
        }





    }
}