/*
 * © 2018 Match Group, LLC.
 */
package ru.haroncode.easy.stomp.core.core

import okhttp3.Request
import okhttp3.WebSocketListener

/**
 * An factory for create STOMP WebSocket connection.
 */
interface WebSocketFactory {

    fun createWebSocket(request: Request, listener: WebSocketListener)
}
