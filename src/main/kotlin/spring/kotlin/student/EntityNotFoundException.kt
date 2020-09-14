package spring.kotlin.student

class EntityNotFoundException internal constructor(id: Long) : RuntimeException("ID not found : $id")