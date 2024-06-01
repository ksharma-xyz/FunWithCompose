# SideEffects - derivedStateOf()

This demo project to showcase the usage of `derivedStateOf()` Side Effect by building scroll to top functionality.

## derivedStateOf:convert one or multiple state objects into another state
In Compose, recomposition occurs each time an observed state object or composable input changes. A state object or input may be changing more often than the UI actually needs to update, leading to unnecessary recomposition.

In this code, `firstVisibleItemIndex` changes any time the first visible item changes. As you scroll, the value becomes 0, 1, 2, 3, 4, 5, etc. However, recomposition only needs to occur if the value is greater than 0. This mismatch in update frequency means that this is a good use case for `derivedStateOf`.

You should use the derivedStateOf function when your inputs to a composable are changing more often than you need to recompose. This often occurs when something is frequently changing, such as a scroll position, but the composable only needs to react to it once it crosses a certain threshold. `derivedStateOf` creates a new Compose state object you can observe that only updates as much as you need. In this way, it acts similarly to the Kotlin Flows `distinctUntilChanged()` operator

Read More: https://developer.android.com/develop/ui/compose/side-effects#derivedstateof
