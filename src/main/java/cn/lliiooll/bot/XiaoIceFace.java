package cn.lliiooll.bot;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class XiaoIceFace extends JavaPlugin {
    public static final XiaoIceFace INSTANCE = new XiaoIceFace();

    private XiaoIceFace() {
        super(new JvmPluginDescriptionBuilder("cn.lliiooll.bot.XiaoIceFace", "0.0.1")
                .name("XiaoIceFace")
                .author("lliiooll")
                .build());
    }

    @Override
    public void onEnable() {
        CommandManager.INSTANCE.registerCommand(FaceCommand.INSTANCE,true);
        getLogger().info("加载完毕");
    }
}