package me.christo.permissionsgui.utils.menu;

import me.christo.permissionsgui.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class CustomGUIHandler implements Listener {

    private static List<CustomGUI> guis = new ArrayList<>();

    public static void add(CustomGUI gui) {
        guis.add(gui);
    }

    public static void remove(CustomGUI gui) {
        guis.remove(gui);
    }

    List<Player> click = new ArrayList<>();

    @EventHandler
    void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if(e.getClickedInventory()==e.getView().getTopInventory())
                for(CustomGUI gui : guis) {
                    if(removeColor(e.getView().getTitle()).equalsIgnoreCase(removeColor(gui.getName()))) {
                        if(!(e.getClickedInventory()!=null&&e.getClickedInventory().getType() == gui.getResult().getType())) return;
                        if(click.contains(p)) {
                            e.setCancelled(true);
                            p.updateInventory();
                            return;
                        }
                        else click.add(p);
                        (new BukkitRunnable(){public void run() { click.remove(p); }}).runTaskLater(Main.getPlugin(Main.class), 5);
                        if(gui.getClick()!=null)
                            gui.getClick().event(e);
                        if(e.isCancelled())
                            p.updateInventory();
                    }
                }
        }catch (java.util.ConcurrentModificationException ignored){}
    }

    List<Player> close = new ArrayList<>();

    @EventHandler
    void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(close.contains(p)) return;
        else close.add(p);
        (new BukkitRunnable(){public void run() { close.remove(p); }}).runTaskLater(Main.getPlugin(Main.class), 5);
        try {
            for (CustomGUI gui : guis) {
                if(removeColor(e.getView().getTitle()).equalsIgnoreCase(removeColor(gui.getName()))) {
                    if(!(e.getInventory().getType() == gui.getResult().getType())) return;
                    if (gui.getClose() != null && !gui.isIgnoreClose())
                        gui.getClose().event(e);
                    p.updateInventory();
                    (new BukkitRunnable(){public void run() { p.updateInventory(); }}).runTaskLater(Main.getPlugin(Main.class), 5);
                }
            }
        }catch (java.util.ConcurrentModificationException ignored){}
    }

    List<Player> open = new ArrayList<>();

    @EventHandler
    void onOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        if(open.contains(p)) return;
        else open.add(p);
        (new BukkitRunnable(){public void run() { open.remove(p); }}).runTaskLater(Main.getPlugin(Main.class), 5);
        try{
            if(e.getInventory()==e.getView().getTopInventory())
                for(CustomGUI gui : guis) {
                    if(removeColor(e.getView().getTitle()).equalsIgnoreCase(removeColor(gui.getName()))) {
                        if(!(e.getInventory().getType() == gui.getResult().getType())) return;
                        if(gui.getOpen()!=null)
                            gui.getOpen().event(e);
                    }
                }
        }catch (java.util.ConcurrentModificationException ignored){}
    }

    List<Player> drag = new ArrayList<>();

    @EventHandler
    void onDrag(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(drag.contains(p)) return;
        else drag.add(p);
        (new BukkitRunnable(){public void run() { drag.remove(p); }}).runTaskLater(Main.getPlugin(Main.class), 5);
        try{
            if(e.getInventory()==e.getView().getTopInventory())
                for(CustomGUI gui : guis) {
                    if(removeColor(e.getView().getTitle()).equalsIgnoreCase(removeColor(gui.getName()))) {
                        if(!(e.getInventory().getType() == gui.getResult().getType())) return;
                        if(gui.getDrag()!=null)
                            gui.getDrag().event(e);
                    }
                }
        }catch (java.util.ConcurrentModificationException ignored){}
    }

    private String removeColor(String str) {
        String r = ChatColor.translateAlternateColorCodes('&', str);
        for(ChatColor c : ChatColor.values()) {
            if(r.contains("§"+c.getChar()))
                r = r.replace("§" + c.getChar(), "");
            else if(r.contains("&"+c.getChar()))
                r = r.replace("&" + c.getChar(), "");
        }
        return r;
    }

}
