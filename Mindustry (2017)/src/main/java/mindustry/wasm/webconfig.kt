package mindustry.web

data class WebConfig(
    val canvasId: String = "mindustry-canvas",
    val enableWebAudio: Boolean = true,
    val enablePersistence: Boolean = true,
    val preferredBackend: String = "webgl2"
)