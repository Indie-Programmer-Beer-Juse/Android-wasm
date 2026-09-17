const loading = document.getElementById("loading");
const progress = document.getElementById("loading-bar");

function setProgress(value) {
    if (progress) {
        progress.style.width = `${Math.max(0, Math.min(100, value))}%`;
    }
}

function hideLoading() {
    if (loading) {
        loading.style.display = "none";
    }
}

async function startMindustry() {
    try {
        setProgress(10);

        const module = await import("./mindustry-web.js");

        setProgress(40);

        if (typeof module.main === "function") {
            module.main([]);
        } else if (typeof module.default === "function") {
            await module.default();
        } else {
            throw new Error(
                "TeaVM entry point was not found in mindustry-web.js"
            );
        }

        setProgress(100);
        hideLoading();

    } catch (error) {
        console.error("wasm failed", error);

        if (loading) {
            loading.innerHTML = `
                <div style="
                    max-width: 700px;
                    padding: 32px;
                    color: white;
                    font-family: system-ui, sans-serif;
                ">
                    <h1>Mindustry Web failed to start</h1>
                    <pre style="
                        white-space: pre-wrap;
                        color: #ff8080;
                    ">${escapeHtml(String(error?.stack || error))}</pre>
                </div>
            `;
        }
    }
}

function escapeHtml(value) {
    return value
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

window.addEventListener("load", () => {
    setProgress(5);
    startMindustry();
});