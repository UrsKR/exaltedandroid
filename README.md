Get the app on Google Play: https://play.google.com/store/apps/details?id=anathema.android

This application collects various random generators for Exalted, most created by Joseph Meyer (http://lovethelabyrinth.blogspot.com).

### To contribute results to the random tables.
1. Check the tables in https://github.com/UrsKR/exaltedandroid/tree/master/random/src/main/assets
2. Send me some strings that fit the patterns you find there.


### Examples

A simple String:
"Now, forge your destiny."

A String with lookup in ``anotherTable.json`` in the same directory:
"You hail from #anotherTable#."  

A String with lookup in ``anotherTable.json`` in the same directory, picking the object's attribute ``adjective``:
"His dagger has a #anotherTable,adjective# handle."

A String with named placeholders ``personal`` and ``possessive`` to be replaced with named value given by the engine:
"~personal~ was the predominant hero of ~possessive~ age."

A String which calls the Java method ``rollDirection`` back in the triggering class:
"You grew up in the %rollDirection%."
