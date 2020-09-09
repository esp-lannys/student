package spring.kotlin.student

class StudentNotFoundException internal constructor(id: Long) : RuntimeException("ID not found : $id")