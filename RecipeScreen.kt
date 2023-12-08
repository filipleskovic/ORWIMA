package hr.ferit.filipleskovic.myapplication.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import hr.ferit.filipleskovic.myapplication.ui.theme.DarkGray
import hr.ferit.filipleskovic.myapplication.ui.theme.LightGray
import hr.ferit.filipleskovic.myapplication.ui.theme.Pink
import hr.ferit.filipleskovic.myapplication.ui.theme.Purple200
import hr.ferit.filipleskovic.myapplication.ui.theme.White
import hr.ferit.filipleskovic.myapplication.R


@Preview(showBackground = true)
@Composable
fun MyPreview() {
    RecipeScreen(navigation = rememberNavController())
    
}
@Composable
fun RecipeScreen(navigation: NavController) {
    Column(
        verticalArrangement =  Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        ScreenTitle(
            title="What would you like to cook today?",
            subTitle="Good morning, Filip"
        )
        SearchBar(
            iconResource = R.drawable.ic_search,
            labelText ="Search..."
        )
        var currentActiveButton by remember { mutableStateOf(0) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .background(Color.Transparent)
                .fillMaxWidth()
                .height(44.dp)
        ) {
            TabButton(
                text = "All",
                isActive = currentActiveButton == 0,
                modifier = Modifier.weight(1f)
            ) {
                currentActiveButton = 0
            }
            TabButton(
                text = "Breakfast",
                isActive = currentActiveButton == 1,
                modifier = Modifier.weight(1f)
            ){
                currentActiveButton = 1
            }
            TabButton(text="Lunch",
                isActive =currentActiveButton == 2,
                modifier= Modifier.weight(1f))
            {
                currentActiveButton = 2
            }
        }
        IconButton(iconResource = R.drawable.ic_plus, text = "Add new recipe")
        RecipeCard(imageResource = R.drawable.strawberry_pie_2, title ="Pita od jagoda" )

    }

}

@Composable
fun ScreenTitle(
    title: String,
    subTitle: String
){
    Box(
        modifier= Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ){
        Text(
            text=subTitle,
            style = TextStyle(
                color=Purple200,
                fontWeight= FontWeight.Light,
                fontStyle= FontStyle.Italic
            ),

            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        Text(
            text=title,
            style= TextStyle(
                fontSize = 26.sp,
                fontWeight= FontWeight.Bold,

                ),
            modifier=Modifier
                .padding(horizontal= 15.dp, vertical= 8.dp)


        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        placeholderColor = DarkGray,
        textColor = DarkGray,
        unfocusedLabelColor = DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    var searchInput by remember {
        mutableStateOf("")
    }

    TextField(
        value =searchInput,
        onValueChange ={searchInput= it},
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    modifier:Modifier=Modifier,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(contentColor = White, containerColor = Pink)
        else
            ButtonDefaults.buttonColors(contentColor = DarkGray, containerColor = LightGray),
        modifier = Modifier.fillMaxHeight(),
        onClick =  { onClick()}
    ) {
        Text(text)
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor =
    Pink),
    side: Int = 0
) {
    Button(
        onClick = { /*TODO*/ },
        colors = colors,
    ) {
        Row {
            if (side == 0) {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = text
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            else {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Spacer(Modifier.width(2.dp))
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = text
                )
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    backgroundColor:Color=Color.White,
    textColor:Color = Color.Magenta,
){
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ){
        Text(
            text=text,
            style = TextStyle(
                color = textColor,
                fontSize= 12.sp
            )
        )
    }
}

@Composable
fun RecipeCard(
    @DrawableRes imageResource: Int,
    title: String,
) {
    Card (
        modifier = Modifier
            .size(width = 215.dp, height = 326.dp)
            


    ){
        Box(
            modifier=Modifier.fillMaxWidth()
        ){
            Image(painter = painterResource(id = imageResource), contentDescription ="Image", modifier= Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Box(
                modifier= Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)

            )
            {
                Column() {
                    Text(
                        text=title,
                        style= TextStyle(
                            fontSize = 16.sp,
                            fontWeight= FontWeight.Bold,
                            color = Color.White

                        ))
                    Row(){
                        Chip("30 min")
                        Chip("4 ingredients")
                    }
                }

            }
        }
    }
}
@Composable
fun RecipeCategories() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "All",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Breakfast",
            isActive = currentActiveButton == 1,
            modifier = Modifier
                .weight(1f)
        ) {
            currentActiveButton = 1
        }
        TabButton("Lunch", currentActiveButton == 2, Modifier.weight(1f))
        {
            currentActiveButton = 2
        }
    }
}