# Task Definition 

## ‘Pagan Razz Mule’ - Anagram puzzle

 We supply a dictionary file

  -          A text file of English words
  -          Each word on a new line

You supply the code which: 

  -          Asks for user input on the command line (processes a-Z only and ignores all other characters)
  -          Provides a list of anagrams from the dictionary file

### Rules

  -         All input letters must be used, just once (repeated letters in the input should be each be used once, so an input of ‘Ramsay’ should find anagrams containing 2 ‘A’s)
  -         Only words from the dictionary can be output
  -         Repeated output should be filtered
    -        ‘Linen Olive’ and ‘Olive Linen’ are the same and should be filtered
  -         Multiple threads must be used to search the dictionary (either the file or the data-structure it is loaded into)
  -         Unit tests must be supplied
  -         JavaDoc expected
  -         We are not interested in the command-line code, so it can be ‘just enough’ (no need to use apache-CLI etc)
  -         No external libraries to be used – only JDK code and your own code (other than JUnit, Mockito etc)
  -         JDK 7 compatible
  -         Should be a maven project

### Examples

| Input | Output |
|-------|--------|
| Benedict Cumberbatch | Babe Bench Duct Metric |
| | Cab Cub Bedtime Trench |
| Live online | Enliven Oil | 
| | Linen Olive | 
| | Evil Lone In |


### Resources
Dictionary file: https://raw.githubusercontent.com/eneko/data-repository/master/data/words.txt

Web example: http://wordsmith.org/anagram/


### Time
We expect you to spend 3 - 4 hours on this exercise. 

We are not looking for the ‘right’ answer, 
we are looking for a deliverable that we feel demonstrates your coding abilities, 
an efficient algorithm and above all code which we would be happy to maintain. 

Your submission does not necessarily need to be complete. 
It does need to convince us that given enough extra time you would be able to 
successfully complete it. 
I will expect a reply within 24 hours of you receiving this email. 
I will accept and consider late submissions but I will bear it in mind that 
you have had more time than others in that case.

### Deliverables
Please return a zip file containing all of your project files and 
instructions on how to run the application. 
If you’re not completely satisfied with your submission please say why and 
what your next steps would be to improve/complete it.

