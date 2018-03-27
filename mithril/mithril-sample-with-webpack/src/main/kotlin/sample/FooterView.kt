package sample

import kodando.mithril.Props
import kodando.mithril.content
import kodando.mithril.elements.footer
import kodando.mithril.elements.p

/**
 * This way we defined a page footer component by just grouping mithril elements into an
 * extension method. This works very fine to create stateless components and you can pass
 * the parameters that you need.
 */
fun Props.pageFooter() {
    footer {
        p {
            content("Mithril samples using kodando!")
        }
    }
}
