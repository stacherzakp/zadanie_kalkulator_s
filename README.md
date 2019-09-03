# Salaries calculator

An application is a basic net salary calculator based on daily income in given country. Calculation mechanism works with configurable parameters like:

  - countries and their income tax and fixed costs
  - working days in month
  - calculation currency

### Installation

Salaries calculator requires [JDK Java 8](https://www.oracle.com/technetwork/java/javaee/downloads/java-ee-sdk-downloads-3908423.html) and [Maven](https://maven.apache.org/download.cgi) to build and run.
Once they will be installed, follow instruction below.

```sh
$ cd cloned_project_dir
$ mvn -U clean install
```

If project was sucessfully builded, take a jar file `salaries-calculator-1.0.0-RELEASE` from `target` directory and put it your favourite directory for running an application.

Finally - create a configuration file `application.yml` and put it inside this directory (that one with your generated jar). You can take this file from `src/main/resources` directory or you can create it by your own.

Once everything is ready, run an application:

```sh
$ java -jar salaries-calculator-1.0.0-RELEASE
```

### Configuration (application.yml)

```yaml
salaries:
  calculationCurrency: PLN          #system calculation currency
  workingDaysInMonth: 22            #the number of working days in month
  countryRates:
    - countryName: Poland           #country name for UI
      currencyCode: PLN             #country's currency code
      incomeTaxPercentage: 19       #percentage value of income tax
      fixedCost: 1200               #fixed costs
    - countryName: England
      currencyCode: GBP
      incomeTaxPercentage: 25
      fixedCost: 600
    - countryName: Germany
      currencyCode: EUR
      incomeTaxPercentage: 20
      fixedCost: 800

exchange-rates:
  online:
    base-url: https://api.exchangeratesapi.io/latest   #currency exchange api url
```

### Usage
Once the application is UP and running, take your application url and open an `index.html` page. By default the address will be `http://localhost:8080/index.html`.

Enjoy the salaries calculator.