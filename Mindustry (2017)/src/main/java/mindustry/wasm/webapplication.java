package mindustry.web;

import arc.*;
import arc.struct.*;

public final class WebApplication implements Application {

    private final Seq<ApplicationListener> listeners = new Seq<>();
    private final String canvasId;
    private final ApplicationListener root;

    private boolean running;

    public WebApplication(String canvasId, ApplicationListener root) {
        this.canvasId = canvasId;
        this.root = root;
    }

    public void start() {
        Core.app = this;

        Core.files = new WebFiles();
        Core.input = new WebInput(canvasId);
        Core.graphics = new WebGraphics(canvasId);
        Core.audio = new WebAudio();
        Core.settings = WebStorage.createSettings();

        listeners.add(root);

        running = true;

        init();

        WebBrowser.requestAnimationFrame(this::frame);
    }

    private void frame(double time) {
        if (!running) {
            return;
        }

        defaultUpdate();

        for (ApplicationListener listener : listeners) {
            listener.update();
        }

        WebBrowser.requestAnimationFrame(this::frame);
    }

    @Override
    public Seq<ApplicationListener> getListeners() {
        return listeners;
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.web;
    }

    @Override
    public void post(Runnable runnable) {
        WebBrowser.queueMicrotask(runnable);
    }

    @Override
    public void exit() {
        running = false;
        Vars.finishLaunch();
    }

    @Override
    public String getClipboardText() {
        return WebBrowser.clipboardRead();
    }

    @Override
    public void setClipboardText(String text) {
        WebBrowser.clipboardWrite(text);
    }
}