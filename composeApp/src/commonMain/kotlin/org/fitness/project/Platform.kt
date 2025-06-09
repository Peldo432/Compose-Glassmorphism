package org.fitness.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform