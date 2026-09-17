package mindustry.web;

import arc.*;
import mindustry.*;

public final class WebLauncher extends ClientLauncher {

    public static void main(String[] args) {
        WebRuntime.start("mindustry-canvas", new WebLauncher());
    }

    @Override
    public void setup() {
        Vars.platform = new WebPlatform();

        super.setup();
    }

    @Override
    public void exit() {
    
        Vars.finishLaunch();
    }
}