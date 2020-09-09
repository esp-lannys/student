package spring.kotlin.student


import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.json.JSONArray
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
class StudentControllerIntegrationTest () {

    @Autowired
    lateinit var controller: StudentController

    @Autowired
    lateinit var testRestTemplate : TestRestTemplate


    @Test
    fun testStudentController () {

        val result = testRestTemplate.getForEntity("/student",String::class.java)
        assertNotNull(result);
        assertEquals(result.statusCode,HttpStatus.OK)
    }
}