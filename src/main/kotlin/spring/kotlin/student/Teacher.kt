package spring.kotlin.student

import com.fasterxml.jackson.annotation.JsonIgnore
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
@Table(name = "bean__teacher")
@Serializable
@Data
data class Teacher (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,

        @Column(name = "first_name", columnDefinition = "varchar(255)") val first_name : String,

        @Column(name = "last_name", columnDefinition = "varchar(255)") val last_name : String,
) {

    @ManyToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY, mappedBy = "teacher") @JsonIgnore val course : MutableSet<Course> = HashSet()

    @OneToMany(cascade = [CascadeType.PERSIST,CascadeType.REMOVE], fetch = FetchType.LAZY, mappedBy = "teacher") @JsonIgnore val student : MutableSet<Student> = HashSet()

    @field:CreationTimestamp @Column(name = "create_date", updatable = false) @Temporal(TemporalType.TIMESTAMP) @Contextual  val createdDate: Date = Date()

    @field:UpdateTimestamp @Column(name = "update_date") @Temporal(TemporalType.TIMESTAMP) @Contextual val updatedDate : Date = Date()

}