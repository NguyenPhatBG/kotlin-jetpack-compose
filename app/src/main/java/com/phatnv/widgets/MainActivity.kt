@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.phatnv.widgets
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.phatnv.widgets.src.MainScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }

        }
    }
}

//
//data class MenuItem(
//    val route: ScreensRoute,
//    val icon: ImageVector,
//    val title: String
//)
//
//val drawerScreens = listOf(
//    MenuItem(ScreensRoute.SCREEN_1, Icons.Filled.Phone ,"R.string.screen_1"),
//    MenuItem(ScreensRoute.SCREEN_2, Icons.Filled.Email, "R.string.screen_2"),
//    MenuItem(ScreensRoute.SCREEN_3, Icons.Filled.AccountCircle, "R.string.screen_3"),
//)
//
//enum class ScreensRoute {
//    SCREEN_1, SCREEN_2, SCREEN_3
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun DrawerExample() {
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet {
//                DrawerContent(menus = drawerScreens, onMenuClick = {}) { route ->
//                      println(2134);
//                }
//            }
//        },
//        gesturesEnabled = true
//    ) {
//        ScafoldExample(drawerState, scope)
//    }
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerContent(
//    menus: List<MenuItem>,
//    onMenuClick: (ScreensRoute) -> Unit,
//    param: (Any) -> Unit
//) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                modifier = Modifier.size(150.dp),
//                imageVector = Icons.Filled.AccountCircle,
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//            );
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        menus.forEach {
//            NavigationDrawerItem(
//                label = { Text(text = it.title) },
//                icon = { Icon(imageVector = it.icon, contentDescription = null) },
//                selected = false,
//                onClick = {
//                    onMenuClick(it.route)
//                }
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ScafoldExample(drawerState: DrawerState, scope: CoroutineScope) {
//    var presses by remember { mutableStateOf(0) }
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                colors = TopAppBarDefaults.smallTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                navigationIcon = {
//                    IconButton(onClick = {
//                        scope.launch {
//                            drawerState.apply {
//                                if (isClosed) open() else close()
//                            }
//                        }
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//
//                    }
//                },
//                title = {
//                    Text(
//                        "Top app bar",
//                        fontSize = 20.sp,
//                        color = Color.Black,
//                        fontStyle = FontStyle.Italic,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                    )
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { presses++ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            Text(
//                modifier = Modifier.padding(8.dp),
//                text =
//                """
//                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
//
//                    It also contains some basic inner content, such as this text.
//
//                    You have pressed the floating action button $presses times.
//                """.trimIndent(),
//            )
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
////@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WidgetsTheme {
//        Greeting("Android")
//    }
//}