package mindustry.web;

import mindustry.core.Platform;

public final class WebPlatform implements Platform {

    @Override
    public String getUUID() {
        return WebStorage.uuid();
    }

    @Override
    public void showFileChooser(
        mindustry.ui.FileChooser.FileChooserParams params
    ) {
        WebFilePicker.open();
    }

    @Override
    public void shareFile(arc.files.Fi file) {
        WebShare.share(file);
    }
}