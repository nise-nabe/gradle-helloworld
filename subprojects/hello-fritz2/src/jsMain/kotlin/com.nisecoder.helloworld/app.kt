package com.nisecoder.helloworld

import dev.fritz2.dom.html.render

fun main() {
    render("#target") { // using id selector here, leave blank to use document.body
        h1 { +"My App" }
        div("some-fix-css-class") {
            p(id = "someId") {
                +"Hello World!"
            }
        }
    }
}
