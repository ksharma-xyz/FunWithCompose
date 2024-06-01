Scroll to top functionality in Jetpack Compose android app.

this demo project is to showcase the usage of derivedStateOf() Side Effect


derivedStateOf: convert one or multiple state objects into another state
In Compose, recomposition occurs each time an observed state object or composable input changes. A state object or input may be changing more often than the UI actually needs to update, leading to unnecessary recomposition.

You should use the derivedStateOf function when your inputs to a composable are changing more often than you need to recompose. This often occurs when something is frequently changing, such as a scroll position, but the composable only needs to react to it once it crosses a certain threshold. derivedStateOf creates a new Compose state object you can observe that only updates as much as you need. In this way, it acts similarly to the Kotlin Flows distinctUntilChanged() operator

https://developer.android.com/develop/ui/compose/side-effects#derivedstateof

