# My CBE Parser
================

A Kotlin Android app for parsing CBE SMS inbox and displaying a graph of maximum remaining balance for each month.

## Features

* Parses CBE SMS inbox using `SmsParser` class
* Displays a bar chart of maximum remaining balance for each month
* Uses `MPAndroidChart` library for charting


## Technical Details

* Written in Kotlin
* Uses AndroidX and Jetpack components
* Utilizes `Fragment` architecture for graph display
* Leverages `MPAndroidChart` library for charting

## Code Structure

* `com.yk.mycbeparser.ui.graph`: GraphFragment.kt (main graph display fragment)
* `com.yk.mycbeparser`: SmsParser.kt (SMS parsing logic)
* `res/layout`: fragment_graph.xml (layout for graph fragment)

## Dependencies

* `com.github.mikephil.charting:charting-library:3.1.0` (MPAndroidChart)
* `androidx.core:core-ktx:1.3.2` (AndroidX Core)

## Screenshots

Splash Screen
![ScreenShot](/screenshots/splash_screen.jpg)

Home Screen
![ScreenShot](/screenshots/home.jpg)

Graph Screen
![ScreenShot](/screenshots/graph.jpg)
