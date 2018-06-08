package sample

import kodando.react.dom.ReactDOM
import org.w3c.dom.Element
import kotlin.browser.document

fun main(args: Array<String>) {
  ReactDOM.render(
    application(),
    getContainerElement()
  )
}


fun getContainerElement(): Element {
  return document.querySelector("#application") ?: createContainerElement()
}

fun createContainerElement(): Element {
  return document.createElement("div").also {
    it.id = "application"
    document.body?.prepend(it)
  }
}
