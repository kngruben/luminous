package kng.ruben.luminous;

import kng.ruben.luminous.driver.MongoDBDriver;
import kng.ruben.luminous.spigot.commands.PermissionCommand;
import kng.ruben.luminous.spigot.listener.ConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Luminous extends JavaPlugin {
    private static Luminous instance;
    private MongoDBDriver mongoDriver;

    @Override
    public void onEnable() {
        instance = this;

        initMongoDB();

        var pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(), this);
        getCommand("permission").setExecutor(new PermissionCommand());
    }

    private void initMongoDB() {
        logInfo("Connecting to mongodb database.");

        var config = this.getConfig();
        var connectionURI = config.getString("mongodb.connectionString");
        var database = config.getString("mongodb.database");

        mongoDriver = new MongoDBDriver(connectionURI, database);

        logInfo("Connected to mongodb database.");
    }

    @Override
    public void onDisable() {
        mongoDriver.close();
    }

    private void logInfo(String info) {
        Bukkit.getLogger().log(Level.INFO, info);
    }

    public static Luminous getInstance() {
        return instance;
    }
}
