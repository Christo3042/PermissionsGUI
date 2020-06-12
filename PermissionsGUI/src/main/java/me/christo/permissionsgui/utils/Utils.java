package me.christo.permissionsgui.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static String chat(String s ) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack createItem(XMaterial materialId, String displayName, String... loreString) {
        return createItem(materialId, 1, displayName, loreString);
    }

    public static ItemStack createItem(XMaterial materialId, int amount, String displayName, String... loreString) {
        ItemStack item = materialId.parseItem(amount);
        if(item.hasItemMeta() || item.getItemMeta()!=null) {
            List<String> lore = new ArrayList<>();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Utils.chat(displayName));
            for(String s : loreString) {
                lore.add(Utils.chat(s));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }






}
