package com.jean.moviesarchitectcoders.utils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {

    companion object {
        private const val MOCKWEBSERVER_PORT = 8080
    }

    lateinit var mockWebServer: MockWebServer

    override fun starting(description: Description?) {
        mockWebServer = MockWebServer()
        mockWebServer.start(MOCKWEBSERVER_PORT)
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
    }

}