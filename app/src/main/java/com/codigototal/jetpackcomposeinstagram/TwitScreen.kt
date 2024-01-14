package com.codigototal.jetpackcomposeinstagram
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TwiteerScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff161c26))
    ) {
        Twit(); //caja azul
    }
}

@Composable
fun Twit() { //Caja principal
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ImageProfile(Modifier.weight(1f))
        Spacer(modifier = Modifier.width(5.dp))
        TwitPost(Modifier.weight(4f))
    }
}


@Composable
fun TwitPost(modifier: Modifier) {



    Column(
        modifier = modifier.padding(end = 20.dp, bottom = 20.dp) // Cambia el color del fondo a rojo
    ) {
        DataPost()
        TextPost()
        Spacer(modifier = Modifier.height(15.dp))
        ImagePost()
        Spacer(modifier = Modifier.height(15.dp))
        InteractiveVar();
    }
}

@Preview
@Composable
fun InteractiveVar() {
    var isMessageSelected by remember {
        mutableStateOf(false)
    }

    var isReloadSelected by remember {
        mutableStateOf(false)
    }

    var isLikeSelected by remember {
        mutableStateOf(false)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 50.dp)
    ) {
        IconInteractive(R.drawable.ic_chat, 1, isMessageSelected){isMessageSelected = it}
        IconInteractive(R.drawable.ic_rt, 1, isReloadSelected){isReloadSelected = it}
        IconInteractive(R.drawable.ic_like, 1, isLikeSelected){isLikeSelected = it}

    }
}
@Composable
fun IconInteractive(icon: Int, numero: Int, actionName: Boolean, onSelectedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { onSelectedChange(!actionName) }) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon Message",
                tint = when {
                    actionName -> Color.White // Color blanco cuando está seleccionado
                    else -> Color.Gray // Color gris cuando no está seleccionado
                }
            )
        }
        Text(text = "1", color = when {
            actionName -> Color.White // Color blanco cuando está seleccionado
            else -> Color.Gray // Color gris cuando no está seleccionado
        })
    }
}

@Composable
fun ImagePost() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Iamgen del post",
            modifier = Modifier // Ajusta el tamaño según tus necesidades
                .clip(RoundedCornerShape(40.dp))
        )
    }
}


@Composable
fun TextPost() {
    Column {
        Text(text = "Hola Hola", color = Color.White)
        Text(text = "Hola Hola", color = Color.White)
        Text(text = "Hola Hola", color = Color.White)
        Text(text = "Hola Hola", color = Color.White)

    }
}

@Composable
fun DataPost() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        UserDataProfile()
        Spacer(modifier = Modifier.width(140.dp))
        IconSettings();
    }


}

@Composable
fun IconSettings() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = ". . . ",
            tint = Color.White
        )
    }
}

@Composable
fun UserDataProfile() {
    Row(
    ) {
        Text(text = "Aris", fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "@Alannas", fontWeight = FontWeight.Normal, color = Color(0xff949494))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "4h", fontWeight = FontWeight.Normal, color = Color(0xff949494))
    }


}

@Composable
fun ImageProfile(modifier: Modifier) {

    Column(modifier = modifier.padding(15.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile icon",
            modifier = Modifier.clip(
                CircleShape
            )
        );
    }

}
