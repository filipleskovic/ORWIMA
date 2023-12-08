package hr.ferit.filipleskovic.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.filipleskovic.myapplication.ui.theme.RecipeDetailScreen
import hr.ferit.filipleskovic.myapplication.ui.theme.RecipeScreen

object Routes{
    const val SCREEN_ALL_RECIPES ="recipesList"
    const val SCREEN_RECIPE_DETAILS="recipeDetails/{recipeID}"

    fun getDetailsPath(recipeId:Int?):String{
        if(recipeId!=null &&recipeId!=-1){
            return "recipeDetails/$recipeId"

        }
        return "recipeDetails/0"
    }
}

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SCREEN_ALL_RECIPES
    ){
        composable(Routes.SCREEN_ALL_RECIPES)
        {
         RecipeScreen(navController)
        }
        composable(Routes.SCREEN_RECIPE_DETAILS,
             arguments = listOf(
                 navArgument("recipeId"){
                     type= NavType.IntType
                 })
        ){ backStackEntry ->
        backStackEntry.arguments?.getInt("recipeId")?.let {
            RecipeDetailScreen(navController,it)
        }

        }
    }
}