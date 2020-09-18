package spring.kotlin.student

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fge.jsonpatch.JsonPatch
import com.github.fge.jsonpatch.JsonPatchException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController @CrossOrigin(origins = arrayOf("http://localhost:4200"))
class StudentController(@Autowired private val repository: StudentRepository) {

    @GetMapping("/student")
    fun getAllStudents() = repository.findAll()

    @GetMapping("student/course/{id}")
    fun getStudentByCourse(@PathVariable id : Long) : List<Student> = repository.findStudentsByCourseId(id)

    @GetMapping("/student/{id}")
    fun findOneStudent(@PathVariable id : Long) : ResponseEntity<Student> {
        return repository.findById(id).map { student -> ResponseEntity.ok(student) }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/student")
    fun addNewStudent(@Validated @RequestBody student: Student) : Student = repository.save(student)

    @PutMapping("/student/{id}")
    fun updateStudentByID (@PathVariable id: Long , @RequestBody newStudent: Student) : ResponseEntity<Student> {
        return repository.findById(id).map { existingStudent ->
            val updatedStudent : Student = existingStudent.copy(
                                                                firstName = newStudent.firstName,
                                                                lastName = newStudent.lastName,
                                                                phone = newStudent.phone)
            ResponseEntity.ok().body(repository.save(updatedStudent))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/student/{id}")
    fun deleteStudentByID(@PathVariable id: Long) : ResponseEntity<Student> {
        return repository.findById(id).map { student ->
            repository.delete(student)
            ResponseEntity.ok(student) }.orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("/student/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody patch: JsonPatch): ResponseEntity<Student>? {
        return try {
            val student: Student = repository.findById(id).orElseThrow({ EntityNotFoundException(id) })
            val studentPatched: Student = applyPatchToStudent(patch, student)
            repository.save(studentPatched)
            ResponseEntity.ok<Student>(studentPatched)
        } catch (e: JsonPatchException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Student>()
        } catch (e: JsonProcessingException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build<Student>()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build<Student>()
        }
    }

    @Throws(JsonPatchException::class, JsonProcessingException::class)
    private fun applyPatchToStudent(
            patch: JsonPatch, targetStudent: Student): Student {
        val objectMapper : ObjectMapper = ObjectMapper()
        val patched: JsonNode = patch.apply(objectMapper.convertValue(targetStudent, JsonNode::class.java))
        return objectMapper.treeToValue(patched, Student::class.java)
    }
}