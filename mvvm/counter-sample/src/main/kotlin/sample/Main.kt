package sample

import kodando.react.dom.ReactDOM
import kodando.react.dom.div
import kodando.react.root
import org.w3c.dom.Element
import sample.counter.CounterListViewModel
import sample.counter.autorun
import sample.counter.mobxDevTool
import kotlin.browser.document

fun main(args: Array<String>) {
  val application = root {
    div {
      mobxDevTool()
    }
  }

  ReactDOM.render(application, getContainerElement())

  val counters = CounterListViewModel()

  autorun {
    console.log("COUNTER CHANGED", counters.size)
  }

  autorun {
    console.log("COUNTER IS NEGATIVE", counters.counters.lastOrNull()?.isNegative)
  }

  console.log("------------------------------------")
  counters.addCounter()

  console.log("------------------------------------")
  counters.addCounter().setCount(-10)
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
