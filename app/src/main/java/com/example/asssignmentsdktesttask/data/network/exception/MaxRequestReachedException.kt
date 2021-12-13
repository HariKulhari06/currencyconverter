package com.example.asssignmentsdktesttask.data.network.exception

import java.io.IOException

class MaxRequestReachedException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "The maximum allowed API amount of monthly API requests has been reached."
}
