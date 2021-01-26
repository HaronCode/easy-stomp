/*
 * © 2018 Match Group, LLC.
 */
package ru.haroncode.easy.stomp.core.core

interface IdGenerator {

    /**
     * Generate a new identifier.
     */
    fun generateId(): String
}