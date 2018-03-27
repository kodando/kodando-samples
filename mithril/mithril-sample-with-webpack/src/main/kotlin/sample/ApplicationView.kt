package sample

import kodando.mithril.Props
import kodando.mithril.VNode
import kodando.mithril.View
import kodando.mithril.elements.div
import kodando.mithril.elements.section
import kodando.mithril.root

/**
 * This is the object that defines the application view.
 */
object ApplicationView : View<Props> {
    /**
     * This methods renders the view as mithril elements.
     */
    override fun view(vnode: VNode<Props>): VNode<*>? {
        // since we define all elements as extension methods, we need to call root here to return the first of its children
        return root {
            // adding normal html elements
            section {
                // inserting out custom stateless component
                pageHeader {
                    title = "Hello Mithril Counters with Kotlin"
                }

                // adding normal html elements with a predefined class (CSS)
                div("counters-panel") {
                    // adding our counter view and configuring it here
                    counter {
                        initialCount = 0
                    }

                    // adding another counter view
                    counter {
                        initialCount = 10
                    }
                }

                // inserting out custom stateless component
                pageFooter()
            }
        }
    }
}