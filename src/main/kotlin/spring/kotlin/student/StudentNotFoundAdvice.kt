package spring.kotlin.student

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class StudentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StudentNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun greetingNotFoundHandler(ex: StudentNotFoundException): String? {
        return ex.message
    }
}