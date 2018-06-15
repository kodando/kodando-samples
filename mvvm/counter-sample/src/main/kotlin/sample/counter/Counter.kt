package sample.counter

import kodando.react.Component
import kodando.react.ReactProps
import kodando.react.addComponent
import kotlin.js.*
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Counter {
  val count: Int
}

class CounterModel(
  override val count: Int = 0
) : Counter

class CounterViewModel {
  var count by observable(0)
    private set

  val isNegative by computed {
    count < 0
  }

  val setCount by action { value: Int ->
    count = value
  }
}

class CounterListViewModel {
  var counters by observable(arrayOf<CounterViewModel>())

  val size by computed {
    counters.size
  }

  val description by computed {
    when {
      counters.isEmpty() -> "None"
      counters.size == 1 -> "One"
      counters.size == 2 -> "Two"
      else -> "Too much"
    }
  }

  val addCounter by action {
    val counter = CounterViewModel()
    counters += counter
    counter
  }
}

class MobxPropertyProvider<T>(val value: T) {
  operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadWriteProperty<Any, T> {
    val propertyName = property.name

    js("delete thisRef[propertyName]")
    Mobx.extendObservable(thisRef, json(propertyName to value))

    return undefined.unsafeCast<ReadWriteProperty<Any, T>>()
  }
}

class MobxBoxedPropertyProvider<T>(val value: T) {
  operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadWriteProperty<Any, T> {
    val options = json("deep" to true, "name" to property.name)
    val box = Mobx.Observable.box(value, options)

    return object : ReadWriteProperty<Any, T> {
      override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return box.get()
      }

      override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        box.set(value)
      }
    }
  }
}

class MobxComputedProvider<T>(val getter: () -> T) {
  operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadWriteProperty<Any, T> {
    val propertyName = property.name
    val getter = this.getter

    js("""
        // delete thisRef.__proto__[propertyName];
        delete thisRef[propertyName];

        Object.defineProperty(thisRef, propertyName, {
          enumerable: true,
          configurable: true,
          get: function() {
            return getter();
          }
        });
    """)

    Mobx.decorate(thisRef, json(propertyName to Mobx.computedDecorator))

    return undefined.unsafeCast<ReadWriteProperty<Any, T>>()
  }
}

class MobxBoxedComputedProvider<T>(val getter: () -> T) {
  operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadOnlyProperty<Any, T> {
    val options = json("name" to property.name)
    val box = Mobx.computed(getter, options)

    return object : ReadOnlyProperty<Any, T> {
      override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return box.get()
      }
    }
  }
}

class MobxActionProvider<TFunction : Function<R>, R>(val action: TFunction) {
  operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadWriteProperty<Any, TFunction> {
    val propertyName = property.name
    val action = Mobx.action("${thisRef::class.simpleName}::${property.name}", this.action)

    js("""
        delete thisRef[propertyName];

        Object.defineProperty(thisRef, propertyName, {
          value: action,
          enumerable: true,
          configurable: true
        });
    """)

    return undefined.unsafeCast<ReadWriteProperty<Any, TFunction>>()
  }
}

fun <T> observable(value: T) = MobxBoxedPropertyProvider(value)
fun <T> computed(getter: () -> T) = MobxBoxedComputedProvider(getter)
fun <TFunc : Function<R>, R> action(handler: TFunc) = MobxActionProvider(handler)

fun autorun(
  delay: Int? = undefined,
  name: String? = undefined,
  runnable: () -> Unit): MobxDisposer {

  return Mobx.autorun(runnable, json("delay" to delay, "name" to name))
}

typealias MobxDisposer = () -> Unit

@JsModule("mobx")
external object Mobx {

  interface Decorator

  interface ComputedObservable<T> {
    fun get(): T
  }

  interface ObservableValue<T> {
    fun get(): T
    fun set(value: T)
  }

  fun get(thing: Any, key: String): Any?
  fun set(thing: Any, key: String, value: Any?)
  fun extendObservable(target: Any, newProperties: Json)
  fun decorate(target: Any, decorators: Json)
  fun autorun(closure: () -> Unit): MobxDisposer
  fun autorun(closure: () -> Unit, options: Json): MobxDisposer

  fun <T> computed(getter: () -> T, options: Json = definedExternally): ComputedObservable<T>

  @JsName("action")
  fun <TFunc : Function<R>, R> action(handler: TFunc): TFunc

  @JsName("action")
  fun <TFunc : Function<R>, R> action(name: String, handler: TFunc): TFunc

  @JsName("computed")
  val computedDecorator: Decorator

  @JsName("action")
  val actionDecorator: Decorator

  @JsName("observable")
  object Observable {

    @JsName("box")
    fun <T> box(value: T): ObservableValue<T>

    @JsName("box")
    fun <T> box(value: T, options: Json): ObservableValue<T>

  }

}

@JsModule("mobx-react-devtools")
external object MobxDevTool {

  @JsName("default")
  val DevTool: JsClass<Component<DevToolProps, Unit>>

  interface DevToolProps : ReactProps {
    var position: Any
  }

}

fun ReactProps.mobxDevTool() {
  addComponent(MobxDevTool.DevTool)
}
