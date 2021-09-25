package cn.lliiooll.bot

import cn.lliiooll.bot.ai.XiaoIceFaceModule
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import java.net.URL

object FaceCommand : SimpleCommand(
    XiaoIceFace.INSTANCE,
    "face",
    "颜值检测",
    description = "颜值检测"
) {

    @Handler
    suspend fun CommandSender.handle(img: Image) { // 这个参数会被作为指令参数要求
        val respones = XiaoIceFaceModule(img.queryUrl()).process()
        sendMessage(respones.content.text)
        if (respones.content.metadata.reportImgUrl != null) {
            this.subject?.sendImage(
                URL(respones.content.metadata.reportImgUrl).openConnection().getInputStream()
            )
        }
    }
}