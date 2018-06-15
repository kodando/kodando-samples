package sample

import kodando.mobx.devtool.mobxDevTool
import kodando.react.dom.ReactDOM
import kodando.react.dom.div
import kodando.react.root
import org.w3c.dom.Element
import sample.counter.CounterList
import sample.counter.counterListView
import kotlin.browser.document

fun main(args: Array<String>) {
  val counterList = CounterList()

  val application = root {
    div {
      counterListView(counterList)
      mobxDevTool()
    }
  }

  ReactDOM.render(application, getContainerElement())
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
