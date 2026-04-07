package dev.eagleware.qlin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform