package sample

import kodando.mithril.mount
import org.w3c.dom.Element
import kotlin.browser.document

fun main(args: Array<String>) {
    val container = getOrCreateApplicationElement()

    mount(container, ApplicationView)
}

private fun getOrCreateApplicationElement(): Element {
    val id = "application"

    return document.getElementById(id) ?: createElement(id)
}

private fun createElement(id: String): Element {
    return document.createElement("div").also {
        it.id = id
        document.body?.prepend(it)
    }
}

