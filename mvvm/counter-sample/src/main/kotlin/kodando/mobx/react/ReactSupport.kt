package kodando.mobx.react

import kodando.react.*

class ObserverComponent : Component<ObserverComponent.Props, Unit>() {
  interface Props : ReactProps {
    var render: ReactProps.() -> Unit
  }

  override fun render(): ReactNode? {
    val render = props.render

    return root {
      render(this)
    }
  }

  companion object {
    internal val type = MobxReact.observer(ObserverComponent::class.js)
  }
}

fun ReactProps.observing(render: ReactProps.() -> Unit) {
  addComponent(ObserverComponent.type) {
    this.render = render
  }
}

@JsModule("mobx-react")
external object MobxReact {
  fun <T : Any> observer(type: JsClass<T>): JsClass<T>
}
