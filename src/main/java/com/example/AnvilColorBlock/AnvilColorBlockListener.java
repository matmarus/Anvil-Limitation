package com.example.anvilcolorblock;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class AnvilColorBlockListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        ItemStack firstInput = event.getInventory().getItem(0);

        if (result == null || firstInput == null) return;

        // Preserve color codes
        if (result.hasItemMeta() && result.getItemMeta().hasDisplayName()) {
            String coloredName = ChatColor.translateAlternateColorCodes('&', result.getItemMeta().getDisplayName());
            result.getItemMeta().setDisplayName(coloredName);
            event.setResult(result);
        }

        // Block renaming: if result name differs from input, cancel rename
        if (result.getItemMeta().hasDisplayName() &&
            firstInput.getItemMeta() != null &&
            firstInput.getItemMeta().hasDisplayName() &&
            !result.getItemMeta().getDisplayName().equals(firstInput.getItemMeta().getDisplayName())) {

            event.setResult(firstInput.clone());
        }
    }
}
