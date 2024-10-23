package insider.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class ConfigHandler {

    private final String browserPath = "browsers";

    private JsonObject parseJsonObject() {
        try {
            return JsonParser.parseReader(new FileReader("src/test/resources/config.json")).getAsJsonObject();
        } catch (IOException e) {
            return new JsonObject();
        }
    }

    public long getImplicitTimeout() {
        return parseJsonObject().getAsJsonObject("timeouts").get("implicitTimeout").getAsLong();
    }

    public Map<String, String> getEnvironments(String env) {

        Type type = new com.google.gson.reflect.TypeToken<HashMap<String, String>>() {
        }.getType();
        return new Gson().fromJson(parseJsonObject().getAsJsonObject("environments").getAsJsonObject(env), type);

    }

    public String getEnvironment() {
        if (!(System.getenv("environment").isEmpty())) {
            return System.getenv("environment");
        }
        return "production";
    }

    public String getBrowserType() {
        String browser = System.getenv("browser");
        if (browser !=null && !browser.isEmpty()) {
            return browser;
        }
        return "chrome";
    }

    public String[] getChromeProperties() {
        return new Gson().fromJson(parseJsonObject().getAsJsonArray(browserPath).get(0).getAsJsonObject().get("chrome"), String[].class);
    }

    public FirefoxProfile getFirefoxProperties() {
        Type type = new com.google.gson.reflect.TypeToken<HashMap<String, ?>>() {
        }.getType();
        HashMap<String, String> preferences = new Gson().fromJson(parseJsonObject().getAsJsonArray(browserPath).get(1).getAsJsonObject().get("firefox"), type);

        FirefoxProfile profile = new FirefoxProfile();

        preferences.keySet().forEach(key -> {
            Object value = preferences.get(key);
            profile.setPreference(key, value);
        });
        return profile;
    }
}