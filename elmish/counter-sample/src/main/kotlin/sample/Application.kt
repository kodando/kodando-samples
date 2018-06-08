package sample

import kodando.react.*

class Application : PureComponent<ReactProps, Application.State>() {

  class State(
    val model: Int = 0
  )

  sealed class Message {
    class ToCounter(val message: Counter.Message) : Message()
  }

  init {
    state = State()
  }

  private fun update(state: State, message: Message): State {
    return when (message) {
      is Message.ToCounter -> State(model = Counter.update(state.model, message.message))
    }
  }

  private fun dispatch(message: Message) {
    val newState = update(state, message)

    setState(newState)
  }

  override fun render(): ReactNode? {
    return Counter.render(state.model) {
      dispatch(Message.ToCounter(it))
    }
  }
}

fun application(): ReactNode? {
  return root {
    addComponent(Application::class)
  }
}
