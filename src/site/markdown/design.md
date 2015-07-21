# Design Considerations

# Implementation approach

The use of [Maven](http://maven.apache.org) is specified. 

Maven is used to build, test and package the application; 
it is also used to generate the application's documentation. 

The system will be coded using a Test Driven approach. 
At the completeion of a Red-Green-Refactor loop IntelliJ's coverage and analysis 
tools are used to ensure redundant code has been removed and style is maintained.
When faking a method it is always marked with a TODO tag so that it is easy to check 
that there are no faked methods remaining at completion.


# Problem Analysis

##### Definition of an anagram
 From [WikiPedia](https://en.wikipedia.org/wiki/Anagram)
> An anagram is a type of word play, the result of rearranging the letters of a word or phrase to produce a new word or phrase, using all the original letters exactly once;

##### A command line interface is specified
 The unit tests will run against the class but a bash test harness is also supplied.
 
##### The data file is 2.4Mb in size, containing 235886 words on different lines. 
 This is not big by modern standards but a threaded search of the data is specified. 

##### The data file contains proper names and duplicates
The initial few lines of the data file: 
```
A
a
aa
aal
aalii
aam
Aani
```
Any capitalised word will be omitted as a proper name during the data load.
This reduces the number of entries from 215843 to 195763.
 
##### Input format
 The system takes a single line of ascii characters as input. The system treats upper and 
lower case characters as the same.
*Note* that only ascii characters are catered for, accented unicode characters are not handled.

##### Output format 
 The system outputs each word with initial letter capitalised. 

##### Problem decomposition
 The problem can be broken into three parts: 
 finding all anagrams of a word and finding all possible sub-divisions of a string and 
 lastly implementing the search in parallel.  
 
###### Finding all anagrams of a string
 We are given a word list and can build a dictionary from that which is keyed upon a 
 sorted copy of the string and has a list of all words with the same key. 
 To ensure that the Lists holding the valid words for a key are not resized the maximum number of 
 words to a key was discovered using a test (it is 10).

###### Finding all possible ways of sub dividing a string
 The problem is to generate all permutations of a string and then all possible word boundaries for each permutation. 
 We hit the combinatorial explosion fairly early! _Benedict Cumberbatch_ is 19 characters 
 of which there are 19 factorial (121645100408832000) permutations for each one of which 
 there are a further 19 factorial ways of splitting them into words. 

 We need to use the dictionalry to cut the search space down.
 The recommended technique for dealing with this size problem is to use a data structure 
 called a trie, a use of which is described in the following references: 
 
   - [Programming Pearls - Column 2](http://www.it.iitb.ac.in/~deepak/deepak/placement/Programming_pearls.pdf).
   - [The Trie: A Neglected Data Structure](http://www.toptal.com/java/the-trie-a-neglected-data-structure)
   - [Android Anagrammer](https://github.com/bconniff/Anagram)

 We need to know the longest word in the disctionary to establish the fan out of the trie, 
 which can be discovered by a test.
 
 To cut down the possibility space we will load a Trie with all the strings in the dictionary 
 and then query that for each generated permutation, pruning branches where there is no entry in 
 the trie. 
 
 
 
###### Parrallelisation
The tests we have developed so far should still pass after parallelisation.

