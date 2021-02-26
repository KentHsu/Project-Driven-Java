# Document Management System

## Requirements

This document management system should be able to:

* Import three different file types: *Report*, *Letter*, *Image*.
* Record file information
* Provide search function based on file information
* Handle other file types in future

Assumptions:

* Documents are all well classified with correct file extension

## Project Structure

#### ```MainApplication```
* Use String type for importFile public API to stay simple

#### ```Importer``` Interface
* Create an interface to support different file type
* Use java.io.File to minimize error
* Implement ```ImageImporter```, ```LetterImporter```, ```ReportImporter```

#### ```Document```
* Wrap Map collection
	* Domain Class: Ubiquitous language -> discoverability
	* Strong type to limit the misusage of Document class
	* Provide meaningful method name instead of just use map.get()
	* Immutability can support creating index and cache
	* Document can only be access within package

#### ```Attributes```
* Stringly type document attributes
	* Search function is based on pure string
	* Unify attribute formats
	* More complex type can be used if we want more functionality in future

#### ```Query```
* KISS
* Implement a Query Language
	* Document attribute key-value pair seperated by comma

## LSP (Liskov Substitution Principle)
```
Let Φ(x) be a property provable about objects x of type T.
Then Φ(y) should be true for objects y of type S where S is a subtype of T.
```

* Preconditions cannot be strengthened in a subtype
* Postconditions cannot be weakened in a subtype
* Invariants of the supertype must be preserved in a subtype
* The History Rule

## Extending and Reusing Code

#### Add *Invoice* file type

* Implement ```InvoiceImporter```

#### Code duplication when dealing with text files (*Letter*, *Report*, *Invoice*):

Solution options:

* Use a Utility class
	* Risk of creating a God class
* Use Inheritance
	* Terrible choice when inheritance can't model real world relation well
* Use a Domain class 
	* Create ```TextFile``` class to collect text manipulation method


## Test Hygiene
* Test Naming
* Behavior Not Implementation
* Don’t Repeat Yourself
* Good Diagnostics
* Testing Error Cases
* Constants