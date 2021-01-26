/*
 * © 2018 Match Group, LLC.
 */
package ru.haroncode.easy.stomp.core.core

import ru.haroncode.easy.stomp.core.models.StompHeader
import ru.haroncode.easy.stomp.core.models.StompMessage

typealias StompListener = (StompMessage) -> Unit

/**
 * Interface use for subscribe and unsubscribe to STOMP queue.
 */
interface StompSubscriber {

    /**
     * Subscribe to given destination with headers.
     */
    fun subscribe(destination: String, headers: StompHeader?, listener: StompListener)

    /**
     * Unsubscribe from given destination.
     */
    fun unsubscribe(destination: String)
}