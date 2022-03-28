package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.TelegramEvent
import com.justai.jaicf.channel.telegram.telegram

val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            reactions.run {
                sayRandom(
                    "Ассалам Алейкум, я Чмоня.",
                    "Привет, я Чмоня, ваш кот-помощник",
                )
            }
        }
    }

    state("sendVse") {
        activators {
            intent("Vse")
            event(TelegramEvent.VIDEO)
        }
        action {
            val fileId = request.telegram?.message?.video?.fileId
            if (fileId != null) {
                reactions.telegram?.sendVideo(fileId)
            } else {
                reactions.say("Lalala")
            }
        }
    }

    state("bye") {
        activators {
            intent("Bye")
        }
        action {
            reactions.sayRandom(
                "Я никуда не уйду.",
                "Я буду жить вечно!"
            )
        }
    }
}