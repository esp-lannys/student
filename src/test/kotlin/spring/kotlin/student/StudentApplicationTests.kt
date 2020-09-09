package spring.kotlin.student

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat


@SpringBootTest
@RunWith(SpringRunner::class)
class StudentApplicationTests(@Autowired private val controller: StudentController) {

	@Test
	fun contextLoads() {
		assertThat(controller).isNotNull
	}

}
