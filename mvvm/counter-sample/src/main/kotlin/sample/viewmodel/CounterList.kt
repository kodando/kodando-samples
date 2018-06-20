package sample.viewmodel

import kodando.mobx.action
import kodando.mobx.computed
import kodando.mobx.observable

class CounterList : Iterable<Counter> {
  val id = NextId()
  var counters by observable(arrayOf<Counter>())

  val size by computed {
    counters.size
  }

  val addCounter by action {
    counters += Counter()
  }

  val removeCounter by action { id: String ->
    counters = counters
      .filterNot { it.id == id }
      .toTypedArray()
  }

  override fun iterator(): Iterator<Counter> {
    return counters.iterator()
  }
}