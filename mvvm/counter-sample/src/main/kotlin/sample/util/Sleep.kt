package sample.util

import kotlin.browser.window
import kotlin.js.Promise

fun sleep(timeout: Int) = Promise<Boolean> { resolve, _ ->
  window.setTimeout({ resolve(true) }, timeout)
}
