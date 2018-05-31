package fr.ctrl.bottle;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BottleListener implements Listener {

	public BottleListener() {
		// TODO Auto-generated constructor stub
	}
	private  String BOTTLE_NAME = Main.getInstance().getConfig().getString("bottle_name").replace('&', '§');
	@EventHandler
	public void onBottleUse(PlayerInteractEvent e) {

		if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			ItemStack is = e.getItem();
			if (is == null) {
				return;
			}
			if ((e.getItem().getType() == Material.EXP_BOTTLE) && (e.getItem().getItemMeta().getDisplayName() != null)
					&& (e.getItem().getItemMeta().getDisplayName().startsWith(BOTTLE_NAME))) {
				e.setCancelled(true);
				String str = e.getItem().getItemMeta().getDisplayName().replace(BOTTLE_NAME, "");
				int value = Integer.parseInt(str);
				if(!(value > 0)){
					return;
				}
				e.getPlayer().setLevel(e.getPlayer().getLevel() + value);
				if (e.getPlayer().getItemInHand().getAmount() > 1) {
					e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
					e.getPlayer().setItemInHand(e.getPlayer().getItemInHand());
				} else {
					e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				}
			}
		}
	}
}