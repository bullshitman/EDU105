package com.bullshitman.edu105.model

data class ResponseData(
    val half: Int,
    val semesters: List<Semester>,
    val semesters_prev: List<SemestersPrev>,
    val votes: List<Vote>,
    val year: String
)