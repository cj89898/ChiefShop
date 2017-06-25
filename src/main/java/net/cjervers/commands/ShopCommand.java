package net.cjervers.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import net.cjervers.utilities.Utils;

public class ShopCommand implements CommandExecutor {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player p = (Player) src;
			if (args.hasAny("shop name")) {
				Utils.openShop(p, args.<String>getOne("shop name").get());
			} else {
				Utils.openShop(p, "default");
			}
			return CommandResult.success();
		} else {
			src.sendMessage(Text.of("You must be a player to use this command!"));
			return CommandResult.success();
		}
	}
	
}
