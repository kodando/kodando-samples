package sample.view

import kodando.mobx.react.ObserverView
import kodando.react.*
import kodando.react.dom.button
import kodando.react.dom.div
import kodando.react.dom.strong
import sample.viewmodel.Counter

object CounterView : ObserverView<CounterView.Props>() {

  interface Props : ReactProps {
    var counter: Counter
  }

  override fun render(props: Props): ReactNode? {
    val counter = props.counter
    val count = counter.value
    val children = props.children
    val loadingClass = if (counter.busy) "loading" else ""

    return root {
      div {
        className = "ui secondary segment $loadingClass counter"
        copy(props, ignore = arrayOf(Props::counter.name, Props::children.name, Props::key.name))

        div {
          className = "ui orange left ribbon label"
          add("Counter: ")

          strong {
            add(count)
          }
        }

        div {
          className = "ui buttons"

          button {
            type = "button"
            className = "ui button"
            disabled = counter.busy
            onClick = { counter.decrement() }
            add("-")
          }

          button {
            type = "button"
            className = "ui button"
            disabled = counter.busy
            onClick = { counter.increment() }
            add("+")
          }

          button {
            type = "button"
            className = "ui yellow button"
            disabled = counter.busy
            onClick = { counter.incrementLater(2000) }
            add("++")
          }

          add(children)
        }
      }
    }
  }
}

fun ReactProps.counterView(counter: Counter, configurer: Configurer<CounterView.Props>) {
  addView(CounterView) {
    this.counter = counter
    configurer()
  }
}
