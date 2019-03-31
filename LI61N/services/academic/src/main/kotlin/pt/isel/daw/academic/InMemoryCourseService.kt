package pt.isel.daw.academic

import org.springframework.stereotype.Component
import pt.isel.daw.academic.models.Course

@Component
class InMemoryCourseService : CourseService {
    private val map = HashMap<String, Course>()

    override fun getAll() = map.values.toList()

    override fun create(acr: String, name: String, description: String) =
        if (map.putIfAbsent(acr, Course(acr, name, description)) == null)
            Created(acr)
        else
            AlreadyExists()

    override fun getByAcronym(acr: String) = map[acr]

}