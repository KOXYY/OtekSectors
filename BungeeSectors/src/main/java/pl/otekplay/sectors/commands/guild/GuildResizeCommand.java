package pl.otekplay.sectors.commands.guild;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.otekplay.sectors.data.Guild;
import pl.otekplay.sectors.data.User;
import pl.otekplay.sectors.enums.GroupType;
import pl.otekplay.sectors.managers.UserManager;
import pl.otekplay.sectors.packets.impl.guild.GuildReSizeRequestItemsPacket;
import pl.otekplay.sectors.storage.Settings;
import pl.otekplay.sectors.utils.CommandUtil;

public class GuildResizeCommand extends Command {
    private final GroupType type;
    public GuildResizeCommand(String name, GroupType type, String... aliases) {
        super(name, "", aliases);        CommandUtil.registerVariablesCommand(name,aliases);
        this.type = type;
    }

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
        if (args.length != 0) {
            pp.sendMessage(Settings.BAD_USAGE + "/powieksz");
            return;
        }
        if (!user.hasGuild()) {
            pp.sendMessage(Settings.GUILD_NO_GUILD);
            return;//OTEK LUBI W DUPE
        }
        Guild guild = user.getGuild();
        if (!guild.getLeader().equals(user)) {
            pp.sendMessage(Settings.GUILD_NO_LEADER);
            return;
        }
        if(guild.getLocation().getSize() == 50){
            pp.sendMessage(Settings.GUILD_SIZE_MAX);
            return;
        }
        user.getSector().getClient().sendPacket(new GuildReSizeRequestItemsPacket(user.getUuid().toString()));

    }
}
