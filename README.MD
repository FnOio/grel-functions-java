# FnO GREL Functions

This library provides a standalone library for the [GREL] functions in JAVA.
It also serves as a 'best-practice' example of how to implement your own FnO function implementation in JAVA.

## Installation

```shell
mvn install
```

## Quick start

`src/main/java/io/fno/grel` contains code to actual functions.
Note how they currently are all public and static.
Their description is taken directly from [GREL].
These implementations are [mapped](https://fno.io/spec/#ontology-concrete) to [FnO] descriptions.
How these descriptions look like, you can find in `src/main/resources/grel_java_mapping.ttl`.
Using the `fno:function` predicate, they are linked to Function descriptions.
The function descriptions, you can find at <http://users.ugent.be/~bjdmeest/function/grel.ttl#>.

## Testing

```shell
mvn test
```

## Best practices

### Use JAVA Classes, not primites

So `Integer` instead of `int`, etc.

> The Function Handler for the moment only handles Classes, not primitives.

[FnO]: https://fno.io/spec/
[GREL]: https://github.com/OpenRefine/OpenRefine/wiki/GREL-Functions
