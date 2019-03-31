package pt.isel.daw.academic

import pt.isel.daw.academic.models.Course

interface CourseService {
    fun getByAcronym(acr: String): Course?
    fun getAll(): List<Course>
    fun create(acr: String, name: String, description: String): CreationResult
}