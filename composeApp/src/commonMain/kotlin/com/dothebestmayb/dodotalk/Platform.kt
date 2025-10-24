package com.dothebestmayb.dodotalk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform