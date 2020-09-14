package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "bean__student")
data class Student (
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
        val first_name : String,
        val last_name : String,
        val phone : String,
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "id_teacher")
        @JsonIgnore
        val teacher: Teacher,

        @ManyToMany(fetch = FetchType.LAZY, mappedBy = "student")
        val course : MutableSet<Course> = HashSet()
)