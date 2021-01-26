/*
 * © 2018 Match Group, LLC.
 */
package ru.haroncode.easy.stomp.core.core

import ru.haroncode.easy.stomp.core.models.StompMessage

interface MessageHandler {

    /**
     * Convert given raw data byte array to stomp message
     */
    fun handle(data: ByteArray): StompMessage?
}