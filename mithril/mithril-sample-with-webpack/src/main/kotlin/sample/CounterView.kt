package sample

import kodando.mithril.*
import kodando.mithril.elements.button
import kodando.mithril.elements.div
import kodando.mithril.elements.span

/**
 * The counter component properties.
 */
interface CounterProps : Props {
    var initialCount: Int?
}

/**
 * Our counter view. While we are defining an object, each view will be added as independent components and
 * the object properties will be defined as the state of these components. Read more about this in the Mithril docs.
 */
object CounterView : View<CounterProps>, OnInit<CounterProps> {
    // defining our initial state
    var counter = 0
        private set // restricting the access to it!

    /**
     * Increment the counter
     */
    private fun increment() {
        counter++
    }

    /**
     * Decrement the counter.
     */
    private fun decrement() {
        counter--
    }

    /**
     * Called when the component is created for the first time.
     */
    override fun onInit(vnode: VNode<CounterProps>) {
        // Here, we will copy the props to our state, so we can changed it later.
        counter = vnode.attrs?.initialCount ?: 0
    }

    /**
     * Renders the component.
     */
    override fun view(vnode: VNode<CounterProps>): VNode<*>? {
        return root {
            div("counter-view") {
                button("trigger") {
                    type = "button"
                    // I tried to use closures in properties, but apparently that won't work with Mithril views.
                    // So, let's just call our method here.
                    onClick = { decrement() }
                    content("-")
                }

                span("display") {
                    content(counter)
                }

                button("trigger") {
                    type = "button"
                    onClick = { increment() }
                    content("+")
                }
            }
        }
    }
}

/**
 * Adds a counter extension method so that we can use it do add our counter view to other props.
 * Yes, the components will go in the props, but they are extracted and normalized later.
 */
fun Props.counter(applier: Applier<CounterProps>) {
    // The CounterView is just an object, so we need to tell Mithril to use that object as a component base
    // and we need to add that to our current props scope.
    addChild(CounterView, applier)
    // This is same as bellow:
    // addChild(createElement(CounterView, createProps(applier)))
}
