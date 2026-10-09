# FnO GREL Functions

This library provides a standalone library for the [GREL] functions in JAVA.
It also serves as a 'best-practice' example of how to implement your own FnO function implementation in JAVA.

## Installation

Import as Maven dependency (Use [JitPack](https://www.jitpack.io/)):

```xml
<dependency>
    <groupId>com.github.fnoio</groupId>
    <artifactId>grel-functions-java</artifactId>
    <version>v0.10.2</version>
</dependency>
```
Or build it yourself (you'll need Maven + JDK >= 17):
```shell
mvn install
```

## Quick start

`src/main/java/io/fno/grel` contains the functions as public static methods, described as in [GREL].
`src/main/resources/grel_java_mapping.ttl` [maps](https://fno.io/spec/#ontology-concrete) them to the [FnO] function descriptions at <http://users.ugent.be/~bjdmeest/function/grel.ttl#>; [HANDBOOK.md](HANDBOOK.md) explains the mapping file.

## Testing

```shell
mvn test
```

## Best practices

### Use wrapper classes

Use wrapper classes (`Integer`, `Boolean`, ...) for parameters and return types: the FnO function handler handles classes only.

[FnO]: https://fno.io/spec/
[GREL]: https://docs.openrefine.org/manual/grelfunctions
