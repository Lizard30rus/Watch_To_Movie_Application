package com.example.whatch_to_movie_application

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()


fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}