package xyz.mangal.example.command;

import net.weavemc.loader.api.command.Command;
import xyz.mangal.example.utils.Utils;
import xyz.mangal.example.Main;
import xyz.mangal.example.config.Config;

public class Command1 extends Command {
    public Command1() {
        super("owo");
    }

    @Override
    public void handle(String[] args) {
        if (args.length == 0) {
            Utils.sendMessage("&dowo");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("text")) {
                Config.text = args[1];
                Utils.sendMessage("&eText set to &b" + Config.text + "&e.");
                Main.saveConfig();
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("pos")) {
                if (args[1].isEmpty() || args[2].isEmpty()) {
                    Utils.sendMessage("&cInvalid command usage.");
                }
                if (!args[1].isEmpty() && !args[2].isEmpty()) {
                    Config.posX = Integer.parseInt(args[1]);
                    Config.posY = Integer.parseInt(args[2]);
                    Utils.sendMessage("&eHUD coordinates set to &b" + Config.posX + "&e, &b" + Config.posY + "&e.");
                    Main.saveConfig();
                }
            }
        }
    }
}
