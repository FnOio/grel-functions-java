# grel-functions-java Handbook

Written for a CS student who wants to understand grel-functions-java as code.

## Contents

- [Preface](#preface)
- [Agent request contract (for AI agents/LLMs)](#agent-request-contract-for-ai-agentsllms)
- [Architecture](#architecture)
- [FnO description and mapping files](#fno-description-and-mapping-files)
- [Build and test](#build-and-test)
- [Release process](#release-process)

## Preface

grel-functions-java is a standalone Java library that implements the [GREL](https://docs.openrefine.org/manual/grelfunctions) (General Refine Expression Language, from OpenRefine) functions as plain Java methods. It is published as the Maven artifact `com.github.fnoio:grel-functions-java` through JitPack. It also serves as a best-practice example of an [FnO](https://fno.io/spec/) function implementation in Java: each method is linked to an FnO function description through an FnO mapping, so that an FnO function handler (such as the one used by RML mapping engines) can discover and call it.

The project is a library only. It has no command-line interface, no expression parser and no evaluator of GREL expressions; callers invoke the individual functions directly or through an FnO function handler.

Where things live:

- `src/main/java/io/fno/grel/` holds the function implementations, one class per GREL function category.
- `src/main/resources/functions_grel.ttl` holds the FnO descriptions of the GREL functions, and `src/main/resources/grel_java_mapping.ttl` the FnO mappings from those descriptions to the Java classes and methods. The jar ships both.
- `src/test/java/io/fno/grel/` holds the JUnit 5 tests, one test class per implementation class.
- `pom.xml` is the Maven build manifest; `.gitlab-ci.yml` runs the tests in CI; `bump-version.sh` performs a release; `sync-descriptions.sh` copies the FnO descriptions from a grel-functions-fno release.
- `README.md` describes installation and usage; `CHANGELOG.md` records changes in Keep a Changelog format.

## Agent request contract (for AI agents/LLMs)

<!-- software-handbook contract: 2026-10-09 -->

Every implementation request handled by an AI agent/LLM follows these constraints:

- If the request is a feature or bugfix:
  - fix the specific failing case or issue named in the request;
  - preserve existing passing behavior unless explicitly asked not to;
  - add or update a regression test when needed.
- Leave staging and committing to the maintainer. Edit staged files freely: the maintainer stages what they reviewed, so the unstaged diff shows the new changes.
- Make the smallest coherent patch. A documentation error found along the way is fixed in the same patch.
- Leave the code leaner after every request: remove what the change makes redundant (duplicate tests, parameters and options that no longer do anything, helpers that duplicate each other, comments that only repeat the code), and reuse shared functionality instead of adding a local variant. Use SpotBugs (`mvn -B compile spotbugs:check`), compiler warnings (`mvn compile`) and IDE inspections to find unused code.
- Fix a transient environment problem (a stale PATH, a shell or editor that needs a restart) in the environment, by restarting or reconfiguring it; add no code that works around it.
- **Push back** when a request would violate an established principle (e.g. breaking test hermeticity). Explain the principle and suggest a documentation-only fix instead of silently implementing the harmful change.
- Update this handbook so the change is documented as well as implemented.
  - Document only the latest state, integrated in the surrounding narrative (principles, behavior, rationale), including the choices made and why.
  - This contract holds only general rules for handling a request; project-specific guidance goes in the chapter on that topic.
- Do not stop at making tests green; align the implementation with the specification or intended design, and document the semantic reason in this handbook.
- Never remove or change existing tests (code or fixtures) without explicit permission. A change to an existing fixture (expected output, input, or data) is validated by the maintainer before it is kept, also when a tool writes it: propose the change with its reason, and keep it only after approval.
- Update `CHANGELOG.md` for every change, internal ones included (tests, CI, refactoring, removed code): keep `## Unreleased` a short summary of what changed since the last release. A feature that is new since the last release is one Added line, which later fixes update instead of getting lines of their own. Each line covers one concern; changes of the same concern share a line (such as editorial changes, or updated dependency versions). A line states what is true now, without the old behavior; a fix that only removes a divergence that should not have existed (one part behaving unlike the rest) gets a few words in the line of its concern at most.
- Before a release, propose a review of everything changed since the previous release: the code for correctness, and the documentation and changelog for accuracy and brevity.
- Check whether `README.md` needs updates for user-visible behavior or workflow changes, and update it when needed.
- Write documentation (this handbook, READMEs, `TODO.md`, `CHANGELOG.md`, code comments) as plain positive statements: say what is true and leave out the contrast ("X, not Y"). Keep a negative only when it is the point itself, such as a prohibition, a warning, or a known limitation.
- If there are difficulties during fulfillment, document them in the most appropriate existing handbook location (create a new chapter only when truly necessary) so future requests start with better context.
- A preference or principle that the maintainer states while handling a request is documented so that every later request follows it: a general one in this contract (and in the software-handbook skill it comes from), a project-specific one in the handbook chapter it belongs to. When it is unclear which, ask.
- When a request is a list of feedback (such as a `TODO.md`), clean up after handling it: remove the items that are done, keep every open item as a clear task (an open question or an offered follow-up is an open item), and remove temporary files created along the way.

## Architecture

All code lives in the single package `io.fno.grel`. Each class groups the functions of one GREL category:

| Class | Content |
|---|---|
| `ArrayFunctions` | array functions such as `get`, `join`, `length`, `slice` |
| `BooleanFunctions` | boolean operators |
| `ControlsFunctions` | control functions (`ifThenElse`) |
| `DateFunctions` | date parsing and formatting |
| `MathFunctions` | numeric functions |
| `StringFunctions` | string functions (the largest class) |
| `OtherFunctions` | `type`, `hasField`, `coalesce` |

Design principles:

- Every function is a `public static` method, so an FnO function handler can call it without instantiating a class.
- Parameters and return types use Java wrapper classes (`Integer`, `Boolean`, ...) instead of primitives, because the FnO function handler handles classes only.
- Function semantics and Javadoc descriptions follow the GREL documentation.
- The two-argument `ControlsFunctions.ifThenElse` overload exercises function-agent's overload resolution: function-agent selects it when a call to `grel:controls_if` omits the optional `eFalse` parameter.

Runtime dependencies are Apache `commons-lang3`, `commons-text` and `commons-codec`.

## FnO description and mapping files

The jar ships two Turtle files at the root of its classpath. An FnO function handler loads both: the descriptions say what a function expects and returns, the mappings say which Java method implements it.

`src/main/resources/functions_grel.ttl` describes each GREL function (`fno:Function`, in the `grel:` namespace `http://users.ugent.be/~bjdmeest/function/grel.ttl#`) with its parameters (`fno:expects`) and output (`fno:returns`). Each description follows the Java method it maps to:

- the parameter list matches the method's parameters, in order; an optional parameter (`fno:required false`) at the end covers a shorter overload, as in `grel:string_toString` with its optional `format`;
- the output type follows the Java return type: `Double` and `Number` give `grel:output_decimal` (`xsd:decimal`), `Integer` and `Long` give `grel:output_number` (`xsd:integer`), an array gives `grel:output_array` (`rdf:List`).

An engine uses the output's `fno:type` as the datatype of the literal it generates, so the output type is visible in the engine's output.

The descriptions are maintained in [grel-functions-fno](https://github.com/FnOio/grel-functions-fno), whose `grel.ttl` is the document behind the `grel:` namespace (<https://users.ugent.be/~bjdmeest/function/grel.ttl>). This file is a copy of one of its dated releases (`_dist/<date>.ttl`), the one its `dcterms:modified` names, so a description is fixed there and reaches the jar through a new release. Engines load it from this jar instead of keeping a copy.

`src/main/resources/grel_java_mapping.ttl` connects the descriptions to this implementation:

- one `fnoi:JavaClass` resource per implementation class, with its `fnoi:class-name` (for example `io.fno.grel.ArrayFunctions`);
- one `fno:Mapping` per function, linking the GREL function (`fno:function`, in the `grel:` namespace `http://users.ugent.be/~bjdmeest/function/grel.ttl#`) to the Java class (`fno:implementation`) and the method name (`fno:methodMapping` with `fnom:method-name`), plus parameter and return mappings.

Adding or renaming a public function requires its description in `functions_grel.ttl` and the matching `fno:Mapping` in `grel_java_mapping.ttl`, so that the method stays reachable through FnO. `FnODescriptionsTest` checks that every mapped function is described exactly once.

`sync-descriptions.sh <date>` copies release `<date>` into `functions_grel.ttl`; `sync-descriptions.sh --check` checks that the file is the release its `dcterms:modified` names, ignoring line endings (exit code 0 when it is, 1 when it differs, with the diff, and 2 when the download fails). Both read the release from the public GitHub mirror of grel-functions-fno, because its GitLab origin needs a login.

## Build and test

The build uses Maven with a plain `pom.xml`:

- `mvn install` builds the library jar and installs it locally.
- `mvn test` runs the JUnit 5 (Jupiter) tests.

The compiler release level is Java 17 (`maven.compiler.release`, applied by the pinned `maven-compiler-plugin`); CI tests on `maven:3-eclipse-temurin-17-alpine`. The GitLab CI pipeline has a single `test` stage that runs `mvn $MAVEN_CLI_OPTS test`.

SpotBugs is the linter. The setup (`spotbugs-maven-plugin` 4.10.3.0 with SpotBugs 4.10.3) lives in `<pluginManagement>` of `pom.xml`, mirrors MappingWeaver-java and is bound to no phase. Run it with `mvn -B compile spotbugs:check`. Known state: 0 findings.

Tests are plain unit tests: each test class (for example `StringFunctionsTest`) calls the static functions directly and asserts on the result. `FnODescriptionsTest` reads the two Turtle files from the classpath and compares their function IRIs with a regular expression, which keeps the project free of an RDF library.

## Release process

[RELEASE.md](RELEASE.md) holds the steps, performed with `bump-version.sh`.

`pom.xml` holds the plain version (`X.Y.Z`); the tag carries a `v` prefix (`vX.Y.Z`). JitPack builds the artifact from the GitHub tag with the JDK set in `jitpack.yml` and names the version after the tag, so consumers depend on `vX.Y.Z`.
