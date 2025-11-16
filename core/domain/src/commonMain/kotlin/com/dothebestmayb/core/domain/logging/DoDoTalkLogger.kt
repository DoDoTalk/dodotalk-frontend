package com.dothebestmayb.core.domain.logging

interface DoDoTalkLogger {
    fun debug(message: String)
    fun info(message: String)
    fun warn(message: String)
    fun error(message: String, throwable: Throwable? = null)
}
