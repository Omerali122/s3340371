package uk.ac.tees.mad.jobs.ui.mainapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import uk.ac.tees.mad.jobs.authentication.viewmodel.AuthViewmodel

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "This is home screen!!!"
        )
        Button(
            onClick = {
                navController.navigate("profile_screen")
            }
        ) {
            Text(
                text = "To Profile Screen"
            )
        }
    }
}