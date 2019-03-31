package pt.isel.daw.academic.representations

import pt.isel.daw.academic.models.Course

data class CourseDetailRepr(
        val acronym: String,
        val name: String,
        val description: String) {
}

fun Course.toDetailRepr() = CourseDetailRepr(
        acronym = acronym,
        name = name,
        description = description
)

fun Course.toItemRepr() = CourseItemRepr(
        acronym = acronym,
        name = name
)
