public final class WebFiles implements Files {

    @Override
    public Fi get(String path, FileType type) {

        return switch (type) {
            case classpath, internal ->
                new WebResourceFi(path);

            case local, external ->
                new WebStorageFi(path);

            case absolute ->
                new WebStorageFi(path);
        };
    }

    @Override
    public String getExternalStoragePath() {
        return "indexeddb://mindustry";
    }

    @Override
    public boolean isExternalStorageAvailable() {
        return true;
    }

    @Override
    public String getLocalStoragePath() {
        return "indexeddb://mindustry-local";
    }

    @Override
    public boolean isLocalStorageAvailable() {
        return true;
    }
}