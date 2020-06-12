package me.christo.permissionsgui.utils.menu;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Copy Right ©
 * This code is private
 * Owner: PerryPlaysMC
 * From: 11/3/19-2200
 * Package: me.christo.permissionsgui.utils.menu
 * Class: CustomGUI
 * <p>
 * Path: me.christo.permissionsgui.utils.menu.CustomGUI
 * <p>
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

@SuppressWarnings("all")
public class CustomGUI {


    private Inventory result;
    private InventoryClick click;
    private InventoryOpen open;
    private InventoryClose close;
    private InventoryDrag drag;
    private String name;
    private int size;
    private boolean ignoreClose = false;

    public CustomGUI(String name, int size) {
        name = ChatColor.translateAlternateColorCodes('&', name);
        this.name = name;
        this.size = getInventorySize(size);
        if(size == 5) {
            result = Bukkit.createInventory(null, InventoryType.HOPPER, (name));
        }else if(size == 1) {
            result = Bukkit.createInventory(null, InventoryType.DROPPER, (name));
        }else if(size == 2) {
            result = Bukkit.createInventory(null, InventoryType.DISPENSER, (name));
        }else
            result = Bukkit.createInventory(null, Math.min((size + ((size % 9) > 0 ? (9 - (size % 9)) : 0)), 54), (name));
        CustomGUIHandler.add(this);
    }



    public void remove() {
        CustomGUIHandler.remove(this );
    }

    public void onClick(InventoryClick click) {
        this.click = click;
    }

    public void onOpen(InventoryOpen open) {
        this.open = open;
    }

    public void onClose(InventoryClose close) {
        this.close = close;
    }

    public void onDrag(InventoryDrag drag) {
        this.drag = drag;
    }


    public boolean isIgnoreClose() {
        return ignoreClose;
    }



    public int getSize() {
        return size;
    }


    public CustomGUI setItem(int slot, ItemStack stack) {
        result.setItem(slot, stack);
        return this;
    }

    public CustomGUI addItem(ItemStack stack) {
        result.addItem(stack);
        return this;
    }

    public boolean containsItem(ItemStack stack) {
        for(ItemStack item : result.getContents()) {
            if(item==null)continue;
            if(item.isSimilar(stack))return true;
            ItemStack clone = item.clone();
            clone.setAmount(1);
            ItemStack clone2 = stack.clone();
            clone2.setAmount(1);
            if(item.isSimilar(stack))return true;
        }
        for(ItemStack item : result.getContents()) {
            if(item==null)continue;
            if(item.isSimilar(stack))return true;
            ItemStack clone = item.clone();
            clone.setAmount(1);
            ItemStack clone2 = stack.clone();
            clone2.setAmount(1);
            if(item.isSimilar(stack))return true;
        }
        return result.contains(stack);
    }

    public CustomGUI clear() {
        result.clear();
        return this;
    }

    public String getName() {
        return name;
    }

    public InventoryDrag getDrag() {
        return drag;
    }

    public InventoryClose getClose() {
        return close;
    }

    public InventoryOpen getOpen() {
        return open;
    }

    public InventoryClick getClick() {
        return click;
    }

    public Inventory getResult() {
        return result;
    }

    public int size() {
        return size;
    }

    public ItemStack getItem(int i) {
        return result.getItem(i);
    }

    public void setTitle(String title) {
        CustomGUIHandler.remove(this);
        Inventory inv = Bukkit.createInventory(null, result.getSize(),
                ChatColor.translateAlternateColorCodes('&', title));
        inv.setContents(result.getContents());
        inv.setContents(result.getContents());
        ignoreClose = true;
        int i = 0;
        List<HumanEntity> viewers = result.getViewers();
        for(HumanEntity he : viewers) {
            he.openInventory(inv);
            if(i==viewers.size()) {
                this.name = ChatColor.translateAlternateColorCodes('&', title);
                CustomGUIHandler.add(this);
                this.result = inv;
                ignoreClose = false;
                break;
            }
            i++;
        }
    }


    public void show(Player p) {
        p.openInventory(result);
    }



    private int getInventorySize(int size) {
        return size <= 5 ? 5
                : size <= 9 ? 9
                : size <= 18 ? 18
                : size <= 27 ? 27
                : size <= 36 ? 36
                : size <= 45 ? 45
                : 54;
    }



    public interface InventoryClick {
        void event(InventoryClickEvent e);
    }

    public interface InventoryOpen {
        void event(InventoryOpenEvent e);
    }

    public interface InventoryClose {
        void event(InventoryCloseEvent e);
    }

    public interface InventoryDrag {
        void event(InventoryDragEvent e);
    }

}
