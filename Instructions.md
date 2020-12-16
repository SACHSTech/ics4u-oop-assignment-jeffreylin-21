# ICS4U OOP Assignment

1. [Choose a System](#part-1---choose-a-system) 
2. [Class Design & Diagram](#part-2---class-design--diagram) 
3. [Class Implementation](#part-3---class-implementation)
4. [Main Program](#part-4---main-program)

In this assignment, you will demonstrate your knowledge of OOP by applying object-oriented design to implement a system chosen by you.

## Part 1 - Choose a System
Think about the applications you interact with or the structures you participate in on a daily basis.  Within your home, school, work, on your phone, on your computer. For example:
* A shopping cart checkout system for an online retailer
* A Netflix user's "My List" of movies and shows and the recommendations that derive from it
* A Spotify user's saved songs, albums and podcasts.
* The organization of student membership to clubs and teams within a school
* The hierarchy of staff at a company
* The organization of shoes in a closet and the process of deciding what shoes to wear
* An instagram post and the resulting likes and comments 
* Uber ride service
* A menu and checkout system for a food delivery service i.e UberEats, DoorDash

With any of these systems, ask yourself:
* what are the *things* in this system?
* what are the properties of these *things*?
* what do these *things* do?
* what are the relationships between these *things*?
 * Are some things made up of other things (aggregation)?
 * Are some things more specific versions of other things (inheritance)?

Decide on a system to model with object-oriented design to be implemented in java. Consider something that consist of at least three objects such that relationships exist between them.

## Part 2 - Class Design & Diagram
Decide on the objects that make up your system and for each, specify:
* the object properties (instance variables)
* the object methods
* the relationships between the objects (aggregation, inheritance)

Use Diagram.net to create a class diagram of your system.  Follow the examples given in class and be sure to indicate the properties, methods, and relationships between classes.

| Level  |  Criteria |
|---|---|
| Level 3  | 3 Classes and an aggregate relationship  |
| Level 4  | 3+ Classes, aggregate relationship, inheritance/abstract relationship  |

### README.md
Add your class diagram to the README.md document of this assignment and include a summary of the system you are modelling.


## Part 3 - Class Implementation
Implement your classes in java.  Be sure to apply encapsulation practices here:
* all instance variables are private
* appropriate getter and setter methods defined
* appropriate helper (private) methods defined

If implementing inheritance relationships or abstract classes, remember that parent (super) classes or abstract class should be more generic than the child classes.  Child classes should not duplicate properties or methods defined at parent level (unless you are overriding methods i.e polymorphism).


## Part 4 - Main Program
Create an executable main program that simulates your chosen system and demonstrates the usage (instantiation) of your defined classes and their relationships.  

For example, when modelling a classroom environment, a main program could:
* set the teacher for a classroom
* add students to the classroom
* assign marks to the student
* print a list of student marks 
* print the average mark for the class

| Level  |  Criteria |
|---|---|
|  3  | Demonstration of system that includes instantiation of all classes, demonstration of relationships |
|  4  | Demonstration of system that includes instantiation of all classes, demonstration of relationships.  Demonstration includes user interaction|



## .replit doc
To make complilation and execution as smooth as possible, update your run command in your `.replit` document to include the proper compilation (`javac` command) and running of your program (`java` command).




