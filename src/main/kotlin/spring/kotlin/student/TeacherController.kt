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
class TeacherController (private val repository: TeacherRepostitory){
    @GetMapping("/teacher")
    fun getAllTeachers() = repository.findAll()

    @GetMapping("/teacher/{id}")
    fun findOneTeacher(@PathVariable id : Long) : ResponseEntity<Teacher> {
        return repository.findById(id).map { teacher -> ResponseEntity.ok(teacher) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/teacher")
    fun addNewTeacher(@Validated @RequestBody teacher: Teacher) : Teacher = repository.save(teacher)
    @PutMapping("/teacher/{id}")
    fun updateTeacherByID (@PathVariable id: Long, @RequestBody newTeacher: Teacher) : ResponseEntity<Teacher> {
        return repository.findById(id).map { existingTeacher ->
            val updatedTeacher : Teacher = existingTeacher.copy(
                    first_name = newTeacher.first_name,
                    last_name = newTeacher.last_name)
            ResponseEntity.ok().body(repository.save(updatedTeacher))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/teacher/{id}")
    fun deleteTeacherByID(@PathVariable id: Long) : ResponseEntity<Teacher> {
        return repository.findById(id).map { teacher ->
            repository.delete(teacher)
            ResponseEntity.ok(teacher) }.orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("/teacher/{id}")
    fun updateTeacher(@PathVariable id: Long, @RequestBody patch: JsonPatch): ResponseEntity<Teacher>? {
        return try {
            val teacher: Teacher = repository.findById(id).orElseThrow { EntityNotFoundException(id) }
            val teacherPatched: Teacher = applyPatchToTeacher(patch, teacher)
            repository.save(teacherPatched)
            ResponseEntity.ok<Teacher>(teacherPatched)
        } catch (e: JsonPatchException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Teacher>()
        } catch (e: JsonProcessingException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Teacher>()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build<Teacher>()
        }
    }

    @Throws(JsonPatchException::class, JsonProcessingException::class)
    private fun applyPatchToTeacher(
            patch: JsonPatch, targetTeacher: Teacher): Teacher {
        val objectMapper : ObjectMapper = ObjectMapper()
        val patched: JsonNode = patch.apply(objectMapper.convertValue(targetTeacher, JsonNode::class.java))
        return objectMapper.treeToValue(patched, Teacher::class.java)
    }
}