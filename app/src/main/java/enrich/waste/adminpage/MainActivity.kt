package enrich.waste.adminpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import enrich.waste.adminpage.navigation.MainNavController
import enrich.waste.adminpage.ui.theme.AdminPageTheme
import enrich.waste.adminpage.ui.theme.Adminhome
import enrich.waste.adminpage.ui.theme.CollectWasteInfo
import enrich.waste.adminpage.ui.theme.Decision

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainViewModel by viewModels<MainViewModel>()

            AdminPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  MainNavController(mainViewModel)

//          Decision()
                }
            }
        }
    }
}
