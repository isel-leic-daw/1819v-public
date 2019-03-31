package pt.isel.daw.academic

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.academic.representations.*
import java.net.URI

@RestController
class CoursesController(val courseService: CourseService) {

    @GetMapping(Uri.courses)
    fun getList(): CourseListRepr =
            CourseListRepr(items = courseService
                    .getAll()
                    .map { it.toItemRepr() })

    @GetMapping(Uri.courseByAcr)
    fun getDetail(@PathVariable("acr") acr: String): ResponseEntity<CourseDetailRepr> =
            courseService.getByAcronym(acr)
                    ?.let { ResponseEntity.ok(it.toDetailRepr()) }
                    ?: ResponseEntity.notFound().build()

    @PostMapping(Uri.courses)
    fun createCourse(@RequestBody req: CourseCreationRepr): ResponseEntity<Any> =
            courseService.create(acr = req.acronym, name = req.name, description = req.description).let {
                when (it) {
                    is Created -> ResponseEntity.created(Uri.forCourseByAcr(it.id)).build()
                    is BadValues -> ResponseEntity.badRequest().build()
                    is AlreadyExists -> ResponseEntity.badRequest().build()
                    is NotAllowed -> ResponseEntity.status(HttpStatus.FORBIDDEN).build()
                }
            }


}