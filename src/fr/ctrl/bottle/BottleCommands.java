package fr.ctrl.bottle;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BottleCommands implements CommandExecutor {
	private String BOTTLE_NAME = Main.getInstance().getConfig().getString("bottle_name").replace('&', '§');
	private String BOTTLE_LORE = Main.getInstance().getConfig().getString("bottle_lore").replace('&', '§');

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("bottlexp")) {
			if (args.length == 0) {

				int count = p.getLevel();
				if (count != 0) {
					p.setExp(0.0F);
					p.setLevel(0);
					ItemStack stack = new ItemStack(Material.EXP_BOTTLE);
					ItemMeta meta = stack.getItemMeta();
					meta.setDisplayName(BOTTLE_NAME + count);
					meta.setLore(Arrays.asList(BOTTLE_LORE));
					stack.setItemMeta(meta);
					p.getInventory().addItem(stack);
				} else {
					p.sendMessage("§cVous n'avez plus de lvl d'xp !");
				}
			} else {
				if (args.length == 1) {
					int number = Integer.parseInt(args[0]);
					if (number != 0 && number >0) {
						if (p.getLevel() < number) {
							p.sendMessage("§cVous n'avez plus de lvl d'xp !");
						} else {
							p.setLevel(p.getLevel() - number);
							ItemStack stack = new ItemStack(Material.EXP_BOTTLE);
							ItemMeta meta = stack.getItemMeta();
							meta.setDisplayName(BOTTLE_NAME + number);
							meta.setLore(Arrays.asList(BOTTLE_LORE));
							stack.setItemMeta(meta);
							p.getInventory().addItem(stack);
						}
					}
				}
			}
		}
		return false;
	}
}