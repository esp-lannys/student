package spring.kotlin.student

import javax.persistence.*
@Entity
@Table(name = "bean__teacher")
data class Teacher(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
        val first_name : String,
        val last_name : String,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "teacher")
        val student : MutableSet<Student> = HashSet()
)