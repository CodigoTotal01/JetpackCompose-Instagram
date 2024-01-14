package com.codigototal.jetpackcomposeinstagram.model

import androidx.annotation.DrawableRes

data class Superhero(
    var superheroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int //Hacemos referencia a que sera un recurso de drawable
)
