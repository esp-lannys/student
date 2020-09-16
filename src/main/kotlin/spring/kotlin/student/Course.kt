package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "bean__course")
@Serializable
@Data
data class Course (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long?,
        @Column(name = "course_name", columnDefinition = "varchar(255)")
        val courseName : String,
        @Column(name = "room", columnDefinition = "varchar(255)")
        val room : String
) {
        @ManyToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY)
        @JoinTable(
                name = "bean__course_student",
                joinColumns = [(JoinColumn(name = "id_course"))],
                inverseJoinColumns = [(JoinColumn(name = "id_student"))]
        )
        @JsonIgnore
        val student : MutableSet<Student> = HashSet()
        @ManyToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY)
        @JoinTable(
                name = "bean__course_teacher",
                joinColumns =  [(JoinColumn(name = "id_course"))],
                inverseJoinColumns = [(JoinColumn(name = "id_teacher"))]
        )
        @JsonIgnore
        val teacher : MutableSet<Teacher> = HashSet()

        @Column(name = "create_date", updatable = false) @field:CreationTimestamp @Temporal(TemporalType.TIMESTAMP) @Contextual val createdDate: Date = Date()

        @Column(name = "update_date") @field:UpdateTimestamp @Temporal(TemporalType.TIMESTAMP) @Contextual val updatedDate : Date = Date()
}
