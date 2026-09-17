package mindustry.web;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

final class WebBrowser {

    @JSFunctor
    interface FrameCallback extends JSObject {
        void accept(double timestamp);
    }

    private WebBrowser() {
    }

    @JSBody(
        params = {"canvasId"},
        script = """
            const canvas = document.getElementById(canvasId);
            if (!canvas) {
                throw new Error("Canvas not found: " + canvasId);
            }
            return canvas;
        """
    )
    static native JSObject canvas(String canvasId);

    @JSBody(
        params = {"callback"},
        script = "requestAnimationFrame(callback);"
    )
    static native void requestAnimationFrame(FrameCallback callback);

    @JSBody(
        params = {"callback"},
        script = "queueMicrotask(callback);"
    )
    static native void queueMicrotask(Runnable callback);

    @JSBody(
        script = "return navigator.clipboard ? navigator.clipboard.readText() : '';"
    )
    static native String clipboardRead();

    @JSBody(
        params = {"text"},
        script = "if (navigator.clipboard) navigator.clipboard.writeText(text);"
    )
    static native void clipboardWrite(String text);
}