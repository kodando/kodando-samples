package sample

import kodando.elmish.Dispatch
import kodando.react.ReactNode
import kodando.react.add
import kodando.react.dom.HtmlNodeProps
import kodando.react.dom.button
import kodando.react.dom.div
import kodando.react.root

object Counter {

  sealed class Message {
    class Increment : Message()
    class Decrement : Message()
  }

  fun update(counter: Int, message: Message): Int {
    return when (message) {
      is Message.Increment ->
        counter + 1

      is Message.Decrement ->
        if (counter > 0)
          counter - 1
        else
          counter
    }
  }

  fun render(counter: Int, dispatch: Dispatch<Message>): ReactNode? {
    fun HtmlNodeProps<*>.sendOnClick(message: Message) {
      this.onClick = {
        dispatch(message)
      }
    }

    return root {
      div {
        add("Counter $counter")

        div {
          button {
            type = "button"
            sendOnClick(Message.Increment())
            add("Incrementar")
          }

          button {
            type = "button"
            sendOnClick(Message.Decrement())
            add("Decrementar")
          }
        }
      }
    }
  }

}
