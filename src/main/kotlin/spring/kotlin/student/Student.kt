package spring.kotlin.student

import javax.persistence.*

@Entity
data class Student (
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
        val first_name : String,
        val last_name : String,
        val phone : String,
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "teacher_id")
        val teacher: Teacher
)