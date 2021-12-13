package com.example.asssignmentsdktesttask.data.network.exception

import java.io.IOException

class NetworkConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No internet found. Check your connection or try again."
}
