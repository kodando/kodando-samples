package sample.counter

import kodando.mobx.action
import kodando.mobx.observable

class CounterList {
  var counters by observable(arrayOf<Int>())

  val addCounter by action {
    counters += 0
  }

  val decrementAt by action { index: Int ->
    counters = counters
      .mapIndexed { i, item ->
        if (i == index)
          item - 1
        else
          item
      }
      .toTypedArray()
  }

  val incrementAt by action { index: Int ->
    counters = counters
      .mapIndexed { i, item ->
        if (i == index)
          item + 1
        else
          item
      }
      .toTypedArray()
  }
}
