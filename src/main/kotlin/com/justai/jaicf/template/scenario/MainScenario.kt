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
            reactions.say("1. ${context.session["fileIdVse"]} + $fileId")
            if (context.session["fileIdVse"] != fileId) {
                context.session["fileIdVse"] = fileId
                reactions.say("2. ${context.session["fileIdVse"]}")
            }
            if (context.session["fileIdVse"] != null) {
                reactions.telegram?.sendVideo(context.session["fileIdVse"].toString())
            } else {
                reactions.say("Извини, я сделал что-то не то(")
                reactions.say("3. ${context.session["fileIdVse"]} + $fileId")
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