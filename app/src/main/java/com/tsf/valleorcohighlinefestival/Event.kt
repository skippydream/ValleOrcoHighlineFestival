package com.tsf.valleorcohighlinefestival

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val day: String // es: "Venerdì", "Sabato", ecc.
)
