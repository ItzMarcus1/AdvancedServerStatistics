package me.itzmarcus.advserverstatistics;

import me.itzmarcus.advserverstatistics.utils.MyConfig;
import me.itzmarcus.advserverstatistics.utils.MyConfigManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Calendar;

public class Core extends JavaPlugin implements Listener {

    MyConfigManager configManager;
    MyConfig file;

    public static String currentDate = "";

    @Override
    public void onEnable() {
        configManager = new MyConfigManager(this);
        File statsFile = new File(getDataFolder(), "data.yml");
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        if(!statsFile.exists()) {
            file = configManager.getNewConfig("data.yml");
            String dateString = "day-" + year + "-" + month + "-" + day;
            file.set(dateString + ".most-players-online", 0);
            file.set(dateString + ".most-unique-login", 0);
            file.set(dateString + ".new-players", 0);
            file.saveConfig();
        } else {
            file = configManager.getNewConfig("data.yml");
        }

        currentDate = "";
    }
}
