# Autocompletion
Optimizing autocompletion GUI using binary search and hash list methods.

## Overview: Method Implementations
 
1. Implemented the `compare` method in the `PrefixComparator` class that is used in the `BinarySearchAutocomplete` class. Test with `TestTerm`.
2. Implemented two methods in `BinarySearchLibrary`: `firstIndex` and `lastIndex`, both of which will use the `PrefixComparator` in step #1. Test with `TestBinarySearchLibrary`. 
3. Implemented `BinarySearchAutocomplete` that extends `Autocompletor` by completing the `topMatches` method. This will use the `firstIndex` and `lastIndex` methods from the previous step and the code in `BruteAutocomplete.topMatches`  as a model. Test with `TestBinarySearchAutocomplete` and running `AutocompleteMain` using `BinarySearchAutocomplete`.
4. Created and implemented a new class `HashListAutocomplete` that implements interface `Autocompletor`. Test by running `AutocompleteMain` using `HashListAutocomplete`.

## Implementation of the `compare` method in `PrefixComparator`

A `PrefixComparator` object is obtained by calling `PrefixComparator.getComparator` with an integer argument `r`, the size of the prefix for comparison purposes. The value is stored in the instance variable `myPrefixSize` as you'll see in the code. This class is used in `BinarySearchAutocomplete`.

## Implementation of `BinarySearchLibrary`

The class `BinarySearchLibrary` stores static utility methods used in the implementation of the `BinarySearchAutocomplete` class. We implemented two methods: `firstIndex` and `lastIndex`. Both are variants on the Java API [`Collections.binarySearch(list, key, c)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html#binarySearch(java.util.List,T,java.util.Comparator)) method that, in addition to returning an index `dex` such that `c.compare(list.get(dex), key)==0`, also guarantee to find the first or last such index respectively. 

`BinarySearchAutocomplete` will use these methods along with `PrefixComparator` to efficiently determine the *range of possible completions of a given prefix of a word typed so far*.

## Implementation of `topMatches` in `BinarySearchAutocomplete`

Implement code for `topMatches` in the `BinarySearchAutocomplete` class -- a method required as specified in the `Autocompletor` interface. 

Code in static methods `firstIndexOf` and `lastIndexOf` is written to use the API exported by `BinarySearchLibrary`. The `Term[]` parameter to these methods is transformed to a `List<Term>` since that's the type of parameter that must be passed to `BinarySearchLibrary` methods. 

There is a `Term` object called `dummy` created from the `String` passed to `topMatches`. The weight for the `Term` doesn't matter since only the `String` field of the `Term` is used in `firstIndex` and `lastIndex` calls.

## Implementation of`HashListAutocomplete`

This third implementation will be based on the use of a `HashMap` instead of the binary search algorithm. This class will provide an `O(1)` implementation of `topMatches` --- with a tradeoff of requiring more memory.

*Project completed for the Spring 2024 Data Structures class at Duke University.*
