# Analysis of CrossrefTest

To assess whether the test must be refactored, I use the list of test smells
in chapter 10 of the book.

## Excessive duplication

Excessive duplication is already reduced with parametrized tests.

## Unclear assertions

The assertions compare Optional<String>, which is already clear.

## Bad Handling of complex external resources

CrossrefTest is a UnitTest. The BibDatabase is just called Database, but uses a
javafx.collections.ObservableList (Why ever the ObservableList from javafx, a gui library is used)
as datastore, so no DBMS is behind the database. Files or resources on the web are not used.

## Fixtures that are too general 

The tests work with the same fixtures, a parent BibEntry and a child BibEntry in a BibDatabase.
The individual tests then change the only field which seems to have influence on the tested method
getResolvedFieldOrAlias, the type.

## Sensitive assertions

The assertions compare Optional<String>, which is already clear and only fails when it should fail.

# Summary

CrossrefTest is reasonable. Improvements are possible, but not necessary.

# Part 2: Consider how to augment this test class...

I did not take the time to add test methods, because compiling jabref and the basic analysis
(Does it compile, what does it cover, what lines does it cover, what does the test test)
already took long enough. Too much time would have been necessary to find 4 reasonable tests to add and
implement them.
