//package enrich.waste.adminpage.ui.theme
//
//import enrich.waste.adminpage.R
//import enrich.waste.adminpage.navigation.Screens
//
//sealed class BottomBarScreens(val route: String?, val title: String?, val icon: Int) {
//    data object CollectWaste : BottomBarScreens(
//        Screens.CollectWaste.route,
//        "Collect",
//        R.drawable.img
//    )
//
//    data object CollectedList : BottomBarScreens(
//        Screens.CollectedList.route,
//        "CollectedList",
//        R.drawable.img
//    )
//
//}
//
//val items = listOf(
//    BottomBarScreens.CollectWaste,
//    BottomBarScreens.CollectedList,
//
//)