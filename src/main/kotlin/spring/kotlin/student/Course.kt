package spring.kotlin.student

import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "bean__course")
data class Course (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long?,
        val courseName : String,
        val room : String,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(
                name = "bean__course_student",
                joinColumns = [(JoinColumn(name = "id_course"))],
                inverseJoinColumns = [(JoinColumn(name = "id_student"))]
        )
        val student : MutableSet<Student> = HashSet(),
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinTable(
                name = "bean__course_teacher",
                joinColumns = arrayOf(JoinColumn(name = "id_course")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "id_teacher"))
        )
        val teacher : MutableSet<Teacher> = HashSet()
)
