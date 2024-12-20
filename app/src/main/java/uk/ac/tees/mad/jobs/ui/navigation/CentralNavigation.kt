package uk.ac.tees.mad.jobs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import uk.ac.tees.mad.jobs.authentication.viewmodel.AuthViewmodel
import uk.ac.tees.mad.jobs.mainapp.model.JobInfo
import uk.ac.tees.mad.jobs.mainapp.viewmodel.MainViewmodel
import uk.ac.tees.mad.jobs.ui.authentication.LogInScreen
import uk.ac.tees.mad.jobs.ui.authentication.SignUpScreen
import uk.ac.tees.mad.jobs.ui.authentication.SplashScreen
import uk.ac.tees.mad.jobs.ui.mainapp.AddNewJob
import uk.ac.tees.mad.jobs.ui.mainapp.EditProfileScreen
import uk.ac.tees.mad.jobs.ui.mainapp.HomeScreen
import uk.ac.tees.mad.jobs.ui.mainapp.JobDetailsScreen
import uk.ac.tees.mad.jobs.ui.mainapp.ProfileScreen


@Composable
fun CentralNavigation(
    navController: NavHostController,
    authViewmodel: AuthViewmodel,
    mainViewmodel: MainViewmodel
) {
    NavHost(
        navController = navController,
        startDestination = "splash_graph"
    ) {

        navigation(
            startDestination = "splash_screen",
            route = "splash_graph"
        ) {
            composable("splash_screen") {
                SplashScreen(
                    authViewmodel,
                    navController
                )
            }
        }

        navigation(
            startDestination = "login_screen",
            route = "auth_graph"
        ) {
            composable("login_screen") {
                LogInScreen(
                    authViewmodel,
                    navController
                )
            }

            composable("signup_screen") {
                SignUpScreen(
                    authViewmodel,
                    navController
                )
            }
        }

        navigation(
            startDestination = "home_screen",
            route = "home_graph"
        ) {
            composable("home_screen") {
                HomeScreen(navController, mainViewmodel, authViewmodel)
            }

            composable("profile_screen") {
                ProfileScreen(authViewmodel, navController)
            }

            composable("edit_profile_screen") {
                EditProfileScreen(authViewmodel, navController)
            }

            composable("add_new_job") {
                AddNewJob(mainViewmodel = mainViewmodel, navController)
            }

            composable(
                "job_details/{job}",
                arguments = listOf(
                    navArgument("job") {
                        type = NavType.StringType
                    }
                )
            ) {entry->
                val jobItem = entry.arguments?.getString("job")
                val job = Gson().fromJson(jobItem, JobInfo::class.java)

                JobDetailsScreen(job, navController)
            }
        }
    }
}