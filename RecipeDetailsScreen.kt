package hr.ferit.filipleskovic.myapplication.ui.theme

import android.content.Intent.ShortcutIconResource
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.filipleskovic.myapplication.data.recipes
import hr.ferit.filipleskovic.myapplication.R
import hr.ferit.filipleskovic.myapplication.data.Recipe

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreen(
    navigation: NavController,
    recipeId:Int
) {
    val scrollState = rememberLazyListState()
    val recipe = recipes[recipeId]

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
        TopImageAndBar(coverImage =recipe.image )
        ScreenTitle(title = recipe.title, subTitle =recipe.category )
        BasicInfo(recipe = recipe)
        Description(recipe = recipe)
        Servings()
        IngredientsHeader()
        IngredientsList(recipe)
        ShoppingListButton()
        Reviews(recipe = recipe)
        OtherRecipes()


        }
    }
}

@Composable
fun TopImageAndBar(
    @DrawableRes coverImage:Int
){
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .height(300.dp)            
    ){
        Image(
            painter = painterResource(id = coverImage),
            contentDescription =null,
            contentScale= ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier=Modifier.fillMaxSize()
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                CircularButton(iconResource = R.drawable.ic_arrow_back)
                CircularButton(iconResource = R.drawable.ic_favorite)
            }
            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                White
                            ), startY = 100f
                        )
                    )
            )
        }
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    elevation: ButtonElevation?=ButtonDefaults.buttonElevation(12.dp),
    color: Color = DarkGray,
    onClick:() -> Unit={}
    
) {
    Button(
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(containerColor = White,contentColor=color),
        elevation = elevation,
        onClick = onClick,
        shape= RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ){
        Icon(painter =painterResource(id = iconResource),
             contentDescription = null
            )
        
    }
}
@Composable
fun InfoColumn(
    @DrawableRes iconResource: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun BasicInfo(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, recipe.cookingTime)
        InfoColumn(R.drawable.ic_flame, recipe.energy)
        InfoColumn(R.drawable.ic_star, recipe.rating)
    }
}
@Composable
fun Description(
    recipe: Recipe
) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    )
}
@Composable
fun Servings() {
    var value by remember {
        mutableStateOf(6)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(LightGray)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Serving",
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium
        )
        CircularButton(
            iconResource = R.drawable.ic_minus,
            elevation = null,
            color = Pink
        ) {
            value--
        }
        Text(
            text = "$value",
            modifier = Modifier
                .padding(16.dp),
            fontWeight = FontWeight.Medium
        )
        CircularButton(
            iconResource = R.drawable.ic_plus,
            elevation = null,
            color = Pink
        ) {
            value ++
        }
    }
}
@Composable
fun IngredientCard(
    @DrawableRes iconRes: Int,
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "Ingredient image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        Text(
            text = subtitle,
            style = TextStyle(
                color = Gray,
                fontSize = 12.sp,
            )
        )
    }
}
@Composable
fun IngredientsHeader() {
    var currentActiveButton by remember {
        mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "Ingredient",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Tools",
            isActive = currentActiveButton == 1,
            modifier = Modifier
                .weight(1f)
        ) {
            currentActiveButton = 1
        }
        TabButton("Steps", currentActiveButton == 2, Modifier.weight(1f))
        {
            currentActiveButton = 2
        }
    }
}
@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientsList(
    recipe: Recipe
) {
    EasyGrid(nColumns = 3, items = recipe.ingredients) {
        IngredientCard(it.image, it.title, it.subTitle)
    }
}
@Composable
fun ShoppingListButton() {
    Button(
        onClick = { /*TODO*/ },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add to shopping list", modifier =
        Modifier.padding(8.dp))
    }
}
@Composable
fun Reviews(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 16.dp))
    ) {
        Column {
            Text(text = "Reviews", style = TextStyle(fontSize = 16.sp,
                fontWeight = FontWeight.Bold))
            Text(text = recipe.reviews, color = DarkGray)
        }
        IconButton(
            iconResource = R.drawable.ic_arrow_right,
            text = "See all",
            colors = ButtonDefaults.buttonColors(containerColor =
            Transparent, contentColor = Pink),
            side = 1
        )
    }
}
@Composable
fun OtherRecipes() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
