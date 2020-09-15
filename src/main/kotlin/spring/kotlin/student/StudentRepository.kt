package spring.kotlin.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    fun findStudentByCourseId(courseId: Long) : Student
}
