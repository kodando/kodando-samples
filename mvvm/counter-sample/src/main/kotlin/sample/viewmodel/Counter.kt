package sample.viewmodel

import kodando.mobx.action
import kodando.mobx.asyncAction
import kodando.mobx.await
import kodando.mobx.observable
import sample.util.sleep


class Counter(value: Int = 0) {
  val id = NextId()
  var value by observable(value)
  var busy by observable(false)

  val increment by action {
    this.value += 1
  }

  val incrementLater by asyncAction { time: Int ->
    try {
      busy = true
      sleep(time).await()
      this.value++

    } finally {
      busy = false
    }
  }

  val decrement by action {
    this.value -= 1
  }
}