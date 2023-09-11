package enrich.waste.adminpage.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import enrich.waste.adminpage.MainViewModel
import enrich.waste.adminpage.SplashScreen
import enrich.waste.adminpage.datastore.UserDataStore
import enrich.waste.adminpage.ui.theme.Adminhome
import enrich.waste.adminpage.ui.theme.CollectWasteInfo
import enrich.waste.adminpage.ui.theme.Decision
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainNavController(mainViewModel: MainViewModel) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screens.StartScreen.route,
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

        composable(Screens.StartScreen.route) {
            SplashScreen(navHostController)
        }

        composable(Screens.CollectWaste.route) {
            CollectWasteInfo(navHostController)
        }

        composable(Screens.Decision.route) {
            Decision(navController = navHostController)
        }


    }
}