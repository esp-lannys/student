package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
@Table(name = "bean__teacher")
data class Teacher (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,

        @Column(name = "first_name", columnDefinition = "varchar(255)") val first_name : String,

        @Column(name = "last_name", columnDefinition = "varchar(255)") val last_name : String,

        @OneToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY, mappedBy = "teacher") @JsonIgnore val student : MutableSet<Student> = HashSet(),

        @Column(name = "create_date", updatable = false) @field:CreationTimestamp val createdDate: Date = Date(),

        @Column(name = "update_date") @field:UpdateTimestamp val updatedDate : Date = Date(),

        @ManyToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY, mappedBy = "teacher") val course : MutableSet<Course> = HashSet()

) {

}