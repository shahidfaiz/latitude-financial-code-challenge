# Latitide Financial Coding Challenge

## System design

Very straightforward implementation.

Logic of creating fixed width file and parsing to CSV are separated from IO to facilitate testing(Test the intent, not the side-effects).

A bit confused about the InputEncoding of `ANSI` and OutputEncoding of `UTF-8`. We can simply convert it to Unicode since
it is a superset.

Not considering creating/parsing large files(can't fit into memory) in the current implementation. It can be achieved via batching/streaming.

IO related files(`Main.scala`) is not tested in this implementation(listed as a future improvement).

## Building

To compile the source and tests run:

```
sbt test:compile
```

## Tests

Currently only core search functions are coverred.

To run the tests use:

```
sbt test
```

## Running

To run the main application (net.allan.test.Main) invoke:

```
sbt run
```

`2` files would be created under `/data`.
`input.txt` is the generated fixed width file; `output.csv` is the parsed csv file.

### Running in Docker

```
docker build -t lf-code-challenge . && docker run -it lf-code-challenge
```

Note: SBT is simply too slow to resolve dependencies(even in docker when building the layer).
To resolve this, we can switch to use `Gradle` or create custom base Dockerfile to cache `.ivy` dependencies.

## Coverage

Coverage can be enabled by doing the following:

1. Instrument code and tests with:

```
sbt clean coverage test
```

2. Generate coverage reports with:

```
sbt coverageReport
```

Reports can be found under target/scala-2.12/scoverage-report/index.html__.

### Current test coverage:
```
[info] Statement coverage.: 100.00%
[info] Branch coverage....: 100.00%
[info] Coverage reports completed
[info] Coverage is above minimum [100.00% > 99.0%]
[info] All done. Coverage was [100.00%]
```

## Future enhancements

1. Implement E2E test
1. Apply property-based testing with [ScalaCheck](https://www.scalacheck.org/)
