package pt.isel.daw.academic

import org.springframework.web.util.UriTemplate
import java.net.URI


object Uri {
    fun forCourses() = URI(courses)
    fun forCourseByAcr(acr: String) = courseByAcrTemplate.expand(acr)

    const val courses = "/courses"
    const val courseByAcr = "/courses/{acr}"
    val courseByAcrTemplate = UriTemplate("/courses/{acr}")
}