package mindustry.web;

import arc.*;

public final class WebRuntime {

    private static WebApplication application;

    private WebRuntime() {
    }

    public static void start(String canvasId, ApplicationListener listener) {
        application = new WebApplication(canvasId, listener);
        application.start();
    }

    public static WebApplication application() {
        return application;
    }
}