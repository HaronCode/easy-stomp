package ru.haroncode.easy.stomp.core.generator

import ru.haroncode.easy.stomp.core.core.IdGenerator
import java.util.UUID

class UuidGenerator : IdGenerator {

    override fun generateId() = UUID.randomUUID().toString()
}