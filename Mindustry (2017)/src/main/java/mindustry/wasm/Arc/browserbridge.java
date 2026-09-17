package mindustry.web;

import org.teavm.jso.JSBody;

public final class WebAudioBridge {

    @JSBody(
        script = """
            window.__mindustryAudio ??=
                new (window.AudioContext ||
                     window.webkitAudioContext)();
        """
    )
    public static native void init();

    @JSBody(
        script = """
            if (window.__mindustryAudio) {
                window.__mindustryAudio.resume();
            }
        """
    )
    public static native void resume();

    @JSBody(
        params = {"url"},
        script = """
            // Real implementation should fetch/decode the buffer
            // and return a Java-visible handle/object.
        """
    )
    public static native void load(String url);
}