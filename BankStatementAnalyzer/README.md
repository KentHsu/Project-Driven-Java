# Bank Statement Analyzer

## KISS

Create a simple BankStatementAnalyzer

#### Questions:

* God class:
	* Implement _Single Responsibility Principle (SRP)_
	* Create a **Domain Object** to align BankStatement concept: ```BankTransaction```
	* Create parser class: ```BankStatementCSVParser``` 
	* Refactor BankStatementAnalyzer with above 2 classes: ```MainBankStatementAnalyzer```
* Code Duplication:
	* Copy code when adding new file format parser (ex: JSON)
	* Copy code when adding new analysis method
	* DRY (Don't Repeat Yourself)


## Cohesion

Cohesion is a measure of the degree to which the elements of the module are functionally related.

Target: high cohesion at class and method level

* Calculation methods in MainBankStatementAnalyzer is an example of low cohesion

* Create and move calculation methods into ```BankStatementProcessor```


#### Class Level Cohesion
* Functional
* Sequential
* Communicational
* Procedural
* Temporal
* Logical
* Coincidental


#### Method Level Cohesion
* Low cohesion methods are hard to test
* Be award of multiple if/else section


## Coupling
Coupling is the measure of the degree of interdependence between the modules.

Target: low coupling between different classes

* Introduce an interface for parsing: ```BankStatementParser```
* Refactor BankStatementCSVParser to impletment parser interface
* Decouple analyzer and parser with this interface
* Add another main programm to put this class together: ```MainApplication```


## Testing

#### JUnit


## Open/Close Principle

* Decouple iteration operation and business logic by impleting interface: ```BankTransactionFilter```
* Avoid interface gotchas by introducing ```BankTransactionSummarizer```
* Add domain object ```SummaryStatistics``` for export functionality with ```Exporter``` interface and ```HtmlExporter```

#### Interface Gotchas
* God interface
* Too granular

#### Explicit & Implicit API
* Explicit API: Implement findTransactionInMonth for easier usage
* Implicit API: Implement findTransaction to provide a unified interface

#### Define domain class
* Warp primirive type with a domain class can further decouple domain concept and provide more flexibility

#### Define domain object
* Define a domain object to decouple modules generating the domain object and moduel utilizing the domain object


## Exception Handling

#### Pattern and anti-patterns

* Notation pattern

#### Guidelines
#### Alternatives


## Build Tools
#### Maven
#### Gradle
