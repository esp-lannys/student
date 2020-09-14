package spring.kotlin.student

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fge.jsonpatch.JsonPatch
import com.github.fge.jsonpatch.JsonPatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class CourseController(private val repository: CourseRepository) {
    @GetMapping("/course")
    fun getAllCourse() = repository.findAll()

    @GetMapping("/course/{id}")
    fun findOneStudent(@PathVariable id : Long) : ResponseEntity<Course> {
        return repository.findById(id).map { course -> ResponseEntity.ok(course) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/course")
    fun addNewStudent(@Validated @RequestBody course: Course) : Course = repository.save(course)

    @PutMapping("/course/{id}")
    fun updateStudentByID (@PathVariable id: Long, @RequestBody newCourse: Course) : ResponseEntity<Course> {
        return repository.findById(id).map { existingCourse ->
            val updatedCourse : Course = existingCourse.copy(
                    courseName = newCourse.courseName,
                    room = newCourse.room)
            ResponseEntity.ok().body(repository.save(updatedCourse))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/course/{id}")
    fun deleteStudentByID(@PathVariable id: Long) : ResponseEntity<Course> {
        return repository.findById(id).map { course ->
            repository.delete(course)
            ResponseEntity.ok(course) }.orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("/course/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody patch: JsonPatch): ResponseEntity<Course>? {
        return try {
            val course: Course = repository.findById(id).orElseThrow({ EntityNotFoundException(id) })
            val coursePatched: Course = applyPatchToCourse(patch, course)
            repository.save(coursePatched)
            ResponseEntity.ok<Course>(coursePatched)
        } catch (e: JsonPatchException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Course>()
        } catch (e: JsonProcessingException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Course>()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build<Course>()
        }
    }

    @Throws(JsonPatchException::class, JsonProcessingException::class)
    private fun applyPatchToCourse(
            patch: JsonPatch, targetCourse: Course): Course {
        val objectMapper : ObjectMapper = ObjectMapper()
        val patched: JsonNode = patch.apply(objectMapper.convertValue(targetCourse, JsonNode::class.java))
        return objectMapper.treeToValue(patched, Course::class.java)
    }
}