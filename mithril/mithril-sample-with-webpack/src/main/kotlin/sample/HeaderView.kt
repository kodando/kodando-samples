package sample

import kodando.mithril.Applier
import kodando.mithril.Props
import kodando.mithril.content
import kodando.mithril.createProps
import kodando.mithril.elements.h1
import kodando.mithril.elements.head

/**
 * This is the definition of the props for our header component.
 * Prefer to define this type as 'external interfaces' so it won't be generated at runtime.
 */
interface PageHeader : Props {
    var title: String?
}

/**
 * This is our stateless component for the header. It will receive an applier function which is
 * just an extension function for our props.
 */
fun Props.pageHeader(applier: Applier<PageHeader>) {
    // Gets all configured properties and children
    val props = createProps(applier)
    val title = props.title


    // creating a head element
    head {
        // you can use conditionals normally, because of the extension methods!
        if (title != null) {
            // adding a h1 element to the head
            h1 {
                // adding the title from the props as the content
                content(props.title)
            }
        }
    }
}
