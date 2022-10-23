package pojlib.unity.install;

import com.google.gson.annotations.SerializedName;
import pojlib.unity.util.APIHandler;
import pojlib.unity.util.Constants;

public class QuiltMeta {

    private static final APIHandler handler = new APIHandler(Constants.QUILT_META_URL);

    public static class QuiltVersion {
        @SerializedName("version")
        public String version;
    }

    public static QuiltVersion[] getVersions() {
        return handler.get("versions/loader", QuiltVersion[].class);
    }

    public static QuiltVersion getLatestVersion() {
        QuiltVersion[] versions = getVersions();
        if (versions != null) return versions[0];
        return null;
    }

    public static VersionInfo getVersionInfo(QuiltVersion quiltVersion, MinecraftMeta.MinecraftVersion minecraftVersion) {
        return handler.get(String.format("versions/loader/%s/%s/profile/json", minecraftVersion.id, quiltVersion.version), VersionInfo.class);
    }
}