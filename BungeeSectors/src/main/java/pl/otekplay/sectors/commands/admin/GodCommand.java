package pl.otekplay.sectors.commands.admin;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.otekplay.sectors.data.User;
import pl.otekplay.sectors.enums.GroupType;
import pl.otekplay.sectors.managers.SectorManager;
import pl.otekplay.sectors.managers.UserManager;
import pl.otekplay.sectors.packets.impl.user.UserGodUpdatePacket;
import pl.otekplay.sectors.storage.Settings;
import pl.otekplay.sectors.utils.CommandUtil;

/**
 * Created by Administrator on 2/18/2016.
 */
public class GodCommand extends Command {
    private final GroupType type;
    public GodCommand(String name, GroupType type, String... aliases) {
        super(name, "", aliases);
        CommandUtil.registerVariablesCommand(name,aliases);
        this.type = type;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer pp = (ProxiedPlayer)commandSender;
        User user = UserManager.getUser(pp.getUniqueId());
        if (!user.can(type)) {
            pp.sendMessage(Settings.NO_PERM);
            return;
        }
        user.setGod(!user.isGod());
        SectorManager.sendPacket(new UserGodUpdatePacket(pp.getUniqueId().toString()));
    }
}
