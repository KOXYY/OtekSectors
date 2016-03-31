package pl.otekplay.sectors.commands.normal;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.otekplay.sectors.data.User;
import pl.otekplay.sectors.enums.GroupType;
import pl.otekplay.sectors.managers.UserManager;
import pl.otekplay.sectors.packets.impl.user.UserOpenMenuPacket;
import pl.otekplay.sectors.storage.Settings;
import pl.otekplay.sectors.utils.CommandUtil;

public class DropCommand extends Command {
    private final GroupType type;
    public DropCommand(String name, GroupType type, String... aliases) {
        super(name, "", aliases);
        CommandUtil.registerVariablesCommand(name,aliases);
        this.type = type;
    }
    //OTEK LUBI W DUPE
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer pp = (ProxiedPlayer) commandSender;
        User user = UserManager.getUser(pp.getUniqueId());
        if (commandSender instanceof ProxiedPlayer) {
            if (!user.can(type)) {
                pp.sendMessage(Settings.NO_PERM);
                return;
            }
        }
        user.getSector().getClient().sendPacket(new UserOpenMenuPacket(pp.getUniqueId().toString(),1));
    }
}
