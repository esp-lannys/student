package spring.kotlin.student

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepostitory : JpaRepository<Teacher, Long>