package com.github.mazar1ni

import com.github.mazar1ni.core.di.mainModule
import com.github.mazar1ni.core.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


/*fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSecurity()
    configureRouting()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
}*/

object KoinPlugin : Plugin<Application, KoinApplication, Unit> {

    override val key: AttributeKey<Unit>
        get() = AttributeKey("Koin")

    override fun install(
        pipeline: Application,
        configure: KoinApplication.() -> Unit
    ) {
        val monitor = pipeline.environment.monitor
        val koinApplication = startKoin(appDeclaration = configure)
        monitor.raise(EventDefinition(), koinApplication)

        monitor.subscribe(ApplicationStopping) {
            monitor.raise(EventDefinition(), koinApplication)
            stopKoin()
            monitor.raise(EventDefinition(), koinApplication)
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 50000, host = "192.168.0.102") {
        install(KoinPlugin) {
            modules(mainModule)
        }
        configureRouting()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
    }.start(wait = true)
}
