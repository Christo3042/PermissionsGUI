package me.christo.permissionsgui.commands;

import me.christo.permissionsgui.utils.menu.CustomGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.christo.permissionsgui.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class CommandPerms extends CCommand {

    public CommandPerms() {
        super("cperms");
    }

    public FileConfiguration playerscfg;
    public File playersfile;

    @Override
    public void run(CommandSender sender, String label, String[] args) {



        Player p = (Player) sender;
        if(!(p.hasPermission("*"))) {
            return;
        }
        String guiName = args[0];

        mainPerms(guiName).show(p);
    }

    public CustomGUI mainPerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Perms"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());


        inv.setItem(0, Utils.createItem(XMaterial.NAME_TAG, "&5Player: &d" + Player));
        inv.setItem(2, Utils.createItem(XMaterial.ENCHANTED_BOOK, "&5(!) &7Set &dDonator &7Permissions", "&7Add the player to a donator group!"));
        inv.setItem(4, Utils.createItem(XMaterial.ENCHANTED_BOOK, "&5(!) &7Set &dStaff &7Permissions", "&7Add the player to a staff group!"));
        inv.setItem(6, Utils.createItem(XMaterial.RAIL, "&5(!) &7Set &dTracks", "&7Promote/Demote the user based on a track!"));
        inv.setItem(8, Utils.createItem(XMaterial.REDSTONE, "&5(!) &cRemove &7Player groups!", "&7Remove the user from staff or donator groups"));


        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&5(!) &7Set &dDonator &7Permissions"))) {
                donatorPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&5(!) &7Set &dStaff &7Permissions"))) {
                staffPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&5(!) &7Set &dTracks"))) {
                userTracks(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&5(!) &cRemove &7Player groups!"))) {
                p.sendMessage(Utils.chat("&f&l* &4&LNO!"));
            }
        });
        return inv;
    }

    public CustomGUI donatorPerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Donator Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());


        inv.setItem(0, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &e&LGLADIATOR", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(1, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &9&lPOSEIDON", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(2, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &a&lHADES", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(3, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &6&lZEUS", "", "&7&o((Click to set the player to this group))"));
        /*
        inv.setItem(4, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &d&LDONATOR 5", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(5, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &d&LDONATOR 6", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(6, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &d&LDONATOR 7", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(7, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &d&LDONATOR 8", "", "&7&o((Click to set the player to this group))"));
        */
        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));


        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &e&lGLADIATOR"))) {
                p.performCommand("lp user " + Player + " parent set Gladiator");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &9&lPOSEIDON"))) {
                p.performCommand("lp user " + Player + " parent set Poseidon");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &a&lHADES"))) {
                p.performCommand("lp user " + Player + " parent set Hades");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &6&lZEUS"))) {
                p.performCommand("lp user " + Player + " parent set Zeus");
            }
        });
        return inv;
    }

    public CustomGUI staffPerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &3Staff Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());


        inv.setItem(0, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &a&lHELPER", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(1, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &3&lMOD", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(2, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &d&lSRMOD", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(3, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &c&lADMIN", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(4, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &c&lHEAD-ADMIN", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(5, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &6&lMANAGER", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(6, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &b&lOWNER", "", "&7&o((Click to set the player to this group))"));
        //inv.setItem(7, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &3&lSTAFF 8", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));


        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &a&lHELPER"))) {
                p.performCommand("lp user " + Player + " parent set helper");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &3&lMOD"))) {
                p.performCommand("lp user " + Player + " parent set mod");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lSRMOD"))) {
                p.performCommand("lp user " + Player + " parent set srmod");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &c&lADMIN"))) {
                p.performCommand("lp user " + Player + " parent set admin");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &c&lHEAD-ADMIN"))) {
                p.performCommand("lp user " + Player + " parent set headadmin");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &6&lMANAGER"))) {
                p.performCommand("lp user " + Player + " parent set manager");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &b&lOWNER"))) {
                p.performCommand("lp user " + Player + " parent set owner");
                //
            }
        });
        return inv;
    }
    public CustomGUI userTracks(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Donator Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());


        inv.setItem(0, Utils.createItem(XMaterial.RAIL, "&f&l* &a&lDONATOR", "", "&7&o((Click to promote the user on this track))"));
        inv.setItem(2, Utils.createItem(XMaterial.RAIL, "&f&l* &c&lDONATOR", "", "&7&o((Click to demote the user on this track))"));
        inv.setItem(4, Utils.createItem(XMaterial.RAIL, "&f&l* &a&lSTAFF", "", "&7&o((Click to promote the user on this track))"));
        inv.setItem(6, Utils.createItem(XMaterial.RAIL, "&f&l* &c&lSTAFF", "", "&7&o((Click to demote the user on this track))"));
        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));


        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &a&lDONATOR"))) {
                p.performCommand("lp user " + Player + " promote donator");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &c&lDONATOR"))) {
                p.performCommand("lp user " + Player + " demote donator");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &a&lSTAFF"))) {
                p.performCommand("lp user " + Player + " promote staff");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &c&lSTAFF"))) {
                p.performCommand("lp user " + Player + " demote staff");
            }
            //
        });
        return inv;
    }
    public CustomGUI removePerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Donator Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());


        inv.setItem(3, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &d&LREMOVE DONATOR GROUPS", "", "&7&o(Click me)"));
        inv.setItem(5, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&f&l* &3&LREMOVE STAFF GROUPS", "", "&7&o(Click me)"));

        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));


        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&LREMOVE DONATOR GROUPS"))) {
                removeDonatorPerms(Player).show(p);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &3&LREMOVE STAFF GROUPS"))) {
                removeStaffPerms(Player).show(p);
            }
        });
        return inv;
    }
    public CustomGUI removeDonatorPerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Donator Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());

        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));
        inv.setItem(0, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &e&LGLADIATOR", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(1, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &9&lPOSEIDON", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(2, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &a&lHADES", "", "&7&o((Click to set the player to this group))"));
        inv.setItem(3, Utils.createItem(XMaterial.PURPLE_STAINED_GLASS_PANE, "&f&l* &6&lZEUS", "", "&7&o((Click to set the player to this group))"));





        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &e&lGLADIATOR"))) {
                p.performCommand("lp user " + Player + " parent remove Gladiator");
                //
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &9&lPOSEIDON"))) {
                p.sendMessage("nigger");
                p.performCommand("lp user " + Player + " parent remove Poseidon");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &a&lHADES"))) {
                p.performCommand("lp user " + Player + " parent remove Hades");
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &6&lZEUS"))) {
                p.performCommand("lp user " + Player + " parent remove Zeus");
            }

        });
        return inv;
    }
    public CustomGUI removeStaffPerms(String Player) {

        CustomGUI inv = new CustomGUI(Utils.chat("&5&l(&d&l!&5&l) &8Staff Groups"), 9);

        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());

        inv.setItem(0, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 1", "", "&7&o(Click to remove player from this group"));
        inv.setItem(1, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 2", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(2, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 3", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(3, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 4", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(4, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 5", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(5, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 6", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(6, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 7", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(7, Utils.createItem(XMaterial.CYAN_STAINED_GLASS_PANE, "&3&LREMOVE STAFF 8", "", "&7&o(Click to remove player from this group)"));
        inv.setItem(8, Utils.createItem(XMaterial.WHITE_WOOL, "&f&l* &d&lGo back", "", "&7&o(Click to go back to main menu)"));





        inv.onClick(e -> {
            Player p = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.chat("&f&l* &d&lGo back"))) {
                mainPerms(Player).show(p);
            }
        });
        return inv;
    }
}

