package me.christo.permissionsgui.utils;

import java.util.Arrays;

import me.christo.permissionsgui.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("all")
public abstract class CCommand extends Command implements PluginIdentifiableCommand{
	protected CommandSender sender;
	protected Main plugin;
	protected CCommand(String name) {
		super(name);
		plugin = Main.getPlugin(Main.class);
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}
	
	public abstract void run(CommandSender s, String cl, String[] args);

	@Override
	public boolean execute(CommandSender arg0, String arg1, String[] arg2) {
		this.sender = arg0;
		run(arg0, arg1, arg2);
		return true;
	}
	
	protected void sendMessage(String... messages) {
		Arrays.stream(messages)
		.forEach(message -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
	}

}
