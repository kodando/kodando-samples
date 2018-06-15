package sample.counter

import kodando.mobx.react.observing
import kodando.react.Configurer
import kodando.react.ReactProps
import kodando.react.add
import kodando.react.createProps
import kodando.react.dom.button
import kodando.react.dom.div

fun ReactProps.counterListView(counterList: CounterList) {
  observing {
    div {
      className = "counter-list-view"

      for (index in counterList.counters.indices) {
        counterView {
          this.index = index
          this.counterList = counterList
        }
      }

      button {
        type = "button"
        onClick = {
          counterList.addCounter()
        }

        add("Add button")
      }
    }
  }
}

interface CounterProps : ReactProps {
  var index: Int
  var counterList: CounterList
}

fun ReactProps.counterView(configurer: Configurer<CounterProps>) {
  observing {
    val props = createProps(configurer)
    val counterList = props.counterList
    val index = props.index
    val count = counterList.counters[index]

    div {
      className = "counter"
      add(count)

      div {
        button {
          type = "button"
          add("-")
          onClick = { counterList.decrementAt(index) }
        }

        button {
          type = "button"
          onClick = { counterList.incrementAt(index) }
          add("+")
        }
      }
    }
  }
}
