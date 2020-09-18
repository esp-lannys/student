package spring.kotlin.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
interface StudentRepository : JpaRepository<Student, Long> {
    @Query("select s from Course c inner join c.student s where c.id = :id_course")
    fun findStudentsByCourseId(@Param("id_course") id_course: Long) : List<Student>
}
