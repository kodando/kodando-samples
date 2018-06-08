package sample

import kodando.elmish.Dispatch
import kodando.react.add
import kodando.react.dom.div
import kodando.react.root

object Counters {

  sealed class Message {
    class AddCounter : Message()
    class ToCounter(val index: Int) : Message()
  }

  fun update(counters: Array<Int>, message: Message) =
    when (message) {
      is Message.AddCounter -> counters + 0
    }

  fun render(counters: Array<Int>, dispatch: Dispatch<Message>) =
    root {
      div {
        className = "counters"

        for (counter in counters) {
          val counterView = Counter.render(counter) {

          }

          add(counterView)
        }
      }
    }

}
