package me.christo.permissionsgui;

import me.christo.permissionsgui.commands.CommandPerms;
import me.christo.permissionsgui.commands.ItemListener;
import me.christo.permissionsgui.utils.CCommand;
import me.christo.permissionsgui.utils.menu.CustomGUIHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main extends JavaPlugin {

    private static SimpleCommandMap scm;
    private SimplePluginManager spm;


    @Override
    public void onEnable() {
        registerCommand(new CommandPerms());
        registerListeners(new CustomGUIHandler(), new ItemListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerListeners(Listener... listeners) {
        if(scm==null)setupSimpleCommandMap();
        Arrays.stream(listeners).forEach(listener-> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public void registerCommand(CCommand... commands) {
        if(scm==null)setupSimpleCommandMap();
        Arrays.stream(commands).forEach(command-> scm.register("permissionsgui", command));
    }

    private void setupSimpleCommandMap() {
        spm = (SimplePluginManager) this.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return scm;
    }
}
