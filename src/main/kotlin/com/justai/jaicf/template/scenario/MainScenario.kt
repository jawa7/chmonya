package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario
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
        }
        action {
            reactions.telegram?.sendVideo("https://www.youtube.com/watch?v=iEF9pTCHUOk")
            reactions.telegram?.say("Все")
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