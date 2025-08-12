package com.tsf.valleorcohighlinefestival

data class Line(
    val id: Int,
    val name: String,
    val length: Int, // in metri
    val difficulty: String, // es. "Facile", "Media", "Difficile"
    val location: String // es. "Area Boulder", "Ponte della diga"
)
