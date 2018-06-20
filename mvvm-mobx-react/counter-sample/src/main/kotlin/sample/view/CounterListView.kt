package sample.view

import kodando.mobx.react.ObserverView
import kodando.react.*
import kodando.react.dom.button
import kodando.react.dom.div
import sample.viewmodel.CounterList

object CounterListView : ObserverView<CounterListView.Props>() {
  interface Props : ReactProps {
    var counterList: CounterList
  }

  override fun render(props: Props): ReactNode? {
    val counterList = props.counterList

    return root {
      div {
        className = "counter-list-view"

        for (counter in counterList.counters) {
          counterView(counter) {
            key = counter.id

            button {
              className = "ui red button"
              type = "button"
              onClick = { counterList.removeCounter(counter.id) }
              add("Remove")
            }
          }
        }

        button {
          type = "button"
          className = "ui button"
          onClick = {
            counterList.addCounter()
          }

          add("Add button")
        }
      }
    }
  }
}

fun ReactProps.counterListView(counterList: CounterList) {
  addView(CounterListView) {
    this.counterList = counterList
  }
}
