package com.codigototal.jetpackcomposeinstagram.recivlerview

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codigototal.jetpackcomposeinstagram.R
import com.codigototal.jetpackcomposeinstagram.model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    //LazyColum and LazyRow, son los nuevos RecivclerView, reciven item o items (any type of list)
    val myList = listOf("CodigoTotal", "Palacios", "Tarrillo")
    LazyColumn {
        item { Text(text = "Headers") }
        items(myList) {
            Text(text = "Hola lola -> $it")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val contex = LocalContext.current
    //Pone el espaciado automaticamente, minimo le pone 100.dp a cada uno con adaptive
    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), content = {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) { superHeroSeleccionado ->
                Toast.makeText(
                    contex,
                    superHeroSeleccionado.superheroName,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }, contentPadding = PaddingValues(10.dp))

}


@Composable
fun SuperHeroViewSpecialControl() {
    val contex = LocalContext.current
    val rsState = rememberLazyListState(); //controlara el scroll
    val corutinesScope = rememberCoroutineScope();
    Column {
        LazyColumn(
            state = rsState, verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero) { superHeroSeleccionado ->
                    Toast.makeText(
                        contex,
                        superHeroSeleccionado.superheroName,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        /* No se retomienda val showButton  by remember {
            rsState.firstVisibleItemIndex > 0
        }*/
        val showButton by remember { //Para la recomposiscion de la vista
            derivedStateOf {
                rsState.firstVisibleItemIndex > 0 //Despues de pasar el primer item se invoba
            }
        }
        if (showButton) {
            //Tiene que ir e n una corrutina
            Button(
                onClick = { corutinesScope.launch { rsState.animateScrollToItem(0) } },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            ) {
                Text(text = "Soy un boton cool")
            }
        }
    }
}

@Composable
fun SuperHeroView() {
    val contex = LocalContext.current
    //Pone el espaciado automaticamente
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) { superHeroSeleccionado ->
                Toast.makeText(
                    contex,
                    superHeroSeleccionado.superheroName,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroViewSticky() {
    val contex = LocalContext.current

    val superhero: Map<String, List<Superhero>> =
        getSuperheroes().groupBy { it.publisher }; //Ordena
    //Pone el espaciado automaticamente
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (publisher, mySuperHero) ->
            
            stickyHeader { 
                Text(text = publisher)
            }
            
            items(mySuperHero) { superhero ->
                ItemHero(superhero = superhero) { superHeroSeleccionado ->
                    Toast.makeText(
                        contex,
                        superHeroSeleccionado.superheroName,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}


//Pinta el item como tal
@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    //! Literarlamente clos componentes ya biene n con el modififer un metodo para hacer click sobre ellos
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(superhero) }
            .padding(top = 8.dp, bottom = 8.dp)) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Super Hero: ${superhero.superheroName}",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "PeterParker", "Marvel", R.drawable.spiderman),
        Superhero("Wolerine", "PeterParker", "Marvel", R.drawable.logan),
        Superhero("Batman", "PeterParker", "DC", R.drawable.batman),
        Superhero("Thor", "PeterParker", "Marvel", R.drawable.thor),
        Superhero("Flash", "PeterParker", "DC", R.drawable.flash),
        Superhero("GreenLanter", "PeterParker", "DC", R.drawable.green_lantern),
        Superhero("WonderWoman", "PeterParker", "DC", R.drawable.wonder_woman),
    )
}