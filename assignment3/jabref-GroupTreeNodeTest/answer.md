# Analysis of GroupTreeNodeTest

To assess whether the test must be refactored, I use the list of test smells
in chapter 10 of the book.

## Excessive duplication

The tests are generally concise, but the setGroup* tests reuse a similar setup.
This can be improved with a nested test and a shared setup.
(Example for a nested test for a class with many responsibilities in its methods can be found here: 
[StatsAnalyzerTest](https://github.com/BacLuc/fs22-effective-testing/commit/5671e70e6161273069da3572e51571a69c8d0f8e))
But it's not so bad that an immediate refactoring is needed.

## Unclear assertions

The assertions in the tests are clear.
In getSearchRuleForRefiningGroupReturnsParentAndGroupAsMatcher, one has to think a little what is asserted,
but the domain of matchers for BibTex sources graphs is a little complex.

## Bad Handling of complex external resources

It is a unit test, so external resources like databases, files or web requests are not used and
must not be reset to an initial state or resources freed.

## Fixtures that are too general

The test uses two shared fixtures:
```
    private final List<BibEntry> entries = new ArrayList<>();
    private BibEntry entry;
```

They are filled with a minimal set of data in the setup method:
```
    @BeforeEach
    void setUp() throws Exception {
        entries.clear();
        entry = new BibEntry();
        entries.add(entry);
        entries.add(new BibEntry().withField(StandardField.AUTHOR, "author1 and author2"));
        entries.add(new BibEntry().withField(StandardField.AUTHOR, "author1"));
    }
```

When needed, they are extended with the helper methods
```
    getNodeInComplexTree
    getNodeInSimpleTree
    getNodeInSimpleTree
```

but they also contain a minimal sample, and thus are currently not hard to maintain. 

## Sensitive assertions

The assertions relay on the implementation of Object::equals. Maybe asserting certain properties
of the resulting object would make the test fail with a clearer message. But considering the wide
use of this approach in this project (and with java programmers in general), I won't change this to
keep the code base consistent.

# Summary

GroupTreeNodeTest is reasonable. Improvements are possible, but not necessary.
