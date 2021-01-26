/*
 * © 2018 Match Group, LLC.
 */
package ru.haroncode.easy.stomp.core.client

import ru.haroncode.easy.stomp.core.core.Connection
import ru.haroncode.easy.stomp.core.core.MessageHandler
import ru.haroncode.easy.stomp.core.models.StompMessage
import ru.haroncode.easy.stomp.core.support.StompMessageDecoder
import ru.haroncode.easy.stomp.core.support.StompMessageEncoder
import okhttp3.WebSocket
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Okhttp websocket based implementation of {@link Connection}.
 */
class WebSocketConnection(
    private val webSocket: WebSocket,
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
) : Connection, MessageHandler {

    @Volatile
    private var lastReadTime: Long = -1

    @Volatile
    private var lastWriteTime: Long = -1

    private val messageEncoder = StompMessageEncoder()
    private val messageDecoder = StompMessageDecoder()

    /**
     * {@inheritDoc}
     */
    override fun sendMessage(message: StompMessage): Boolean {
        val lastWriteTime = lastWriteTime
        if (lastWriteTime != -1L) {
            this.lastWriteTime = System.nanoTime()
        }
        val encodedMessage = messageEncoder.encode(message).toString(Charsets.UTF_8)
        return webSocket.send(encodedMessage)
    }

    /**
     * {@inheritDoc}
     */
    override fun onReceiveInactivity(duration: Long, runnable: () -> Unit) {
        check(duration > 0) { "Duration must be more than 0" }
        lastReadTime = System.nanoTime()
        executor.scheduleWithFixedDelay({
            if ((System.nanoTime() - lastReadTime) > TimeUnit.MILLISECONDS.toNanos(duration)) {
                runnable.invoke()
            }
        }, 0, duration / 2L, TimeUnit.MILLISECONDS)
    }

    /**
     * {@inheritDoc}
     */
    override fun onWriteInactivity(duration: Long, runnable: () -> Unit) {
        check(duration > 0) { "Duration must be more than 0" }
        lastWriteTime = System.nanoTime()
        executor.scheduleWithFixedDelay({
            if ((System.nanoTime() - lastWriteTime) > TimeUnit.MILLISECONDS.toNanos(duration)) {
                runnable.invoke()
            }
        }, 0, duration / 2L, TimeUnit.MILLISECONDS)
    }

    /**
     * {@inheritDoc}
     */
    override fun forceClose() {
        webSocket.cancel()
        executor.shutdown()
    }

    /**
     * {@inheritDoc}
     */
    override fun close() {
        webSocket.close(WebSocketCode.CLOSE_NORMAL.code, WebSocketCode.CLOSE_NORMAL.reason)
        executor.shutdown()
    }

    /**
     * {@inheritDoc}
     */
    override fun handle(data: ByteArray): StompMessage? {
        val lastReadTime = lastReadTime
        if (lastReadTime != -1L) {
            this.lastReadTime = System.nanoTime()
        }
        return messageDecoder.decode(data)
    }
}