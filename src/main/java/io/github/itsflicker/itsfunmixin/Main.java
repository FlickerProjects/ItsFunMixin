package io.github.itsflicker.itsfunmixin;

import cn.apisium.papershelled.annotation.Mixin;
import cn.apisium.papershelled.plugin.PaperShelledPlugin;
import cn.apisium.papershelled.plugin.PaperShelledPluginDescription;
import cn.apisium.papershelled.plugin.PaperShelledPluginLoader;
import io.github.itsflicker.itsfunmixin.helpers.DeadCoralToSandHelper;
import io.github.itsflicker.itsfunmixin.mixin.*;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

import java.io.File;

@SuppressWarnings("unused")
@Plugin(name = "ItsFunMixin", version = "0.0.1")
@Description("A simple PaperShelled plugin.")
@Author("ItsFlicker")
@Website("https://github.com/FlickerProjects/ItsFunMixin")
@ApiVersion(ApiVersion.Target.v1_13)
@Mixin({
        CommandsMixin.class,
        ConnectionMixin.class,
        DeadCoralMixin.class,
//        FallingBlockEntityMixin.class,
        GuardianMixin.class,
        MinecraftServerMixin.class,
        PlayerListMixin.class,
        ServerPlayer_actionPackMixin.class,
        ServerPlayer_scarpetEventMixin.class
})
public class Main extends PaperShelledPlugin {

    public static String serverName;
    public static Boolean guardianThunderTransfer = false;

    public Main(PaperShelledPluginLoader loader, PaperShelledPluginDescription paperShelledDescription, PluginDescriptionFile description, File file) {
        super(loader, paperShelledDescription, description, file);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reload();
    }

    public void reload() {
        serverName = getConfig().getString("server-name");
        guardianThunderTransfer = getConfig().getBoolean("guardian-thunder-transfer", false);
        DeadCoralToSandHelper.BREAK_CHANCE = getConfig().getDouble("coral-sand-dupe", -1D);

        if (serverName != null) {
            getLogger().info("Modified server name to " + serverName);
        }
    }

}
