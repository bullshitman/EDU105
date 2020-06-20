package com.bullshitman.edu105.model

data class Semester(
    val discipline: Discipline,
    val half: Int,
    val id: Int,
    val professor: Professor,
    val year: Int
)