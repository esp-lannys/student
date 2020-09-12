package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
@Entity
@Table(name = "bean__teacher")
data class Teacher(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
        val first_name : String,
        val last_name : String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "teacher")
        @JsonIgnore
        val student : MutableSet<Student> = HashSet()
)