# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## Unreleased

### Fixed

- array functions should use `List`, not `[]`

## [0.7.3] - 2022-03-29

### Fixed

- Implementation mappings in `grel_java_mapping.ttl` used class `fnoi:Mapping` instead of `fno:Mapping` (see [GitLab issue #2](https://gitlab.ilabt.imec.be/fno/lib/grel-functions-java/-/issues/2))

## [0.7.2] - 2022-03-24

### Fixed

- Added missing descriptions for coalesce anf indexOf functions.
- Simplified `BooleanFunctions.java`.

## [0.7.1] - 2021-08-24

### Fixed

- updated dependencies

## [0.7.0] - 2021-08-24

### Added

- a _lot_ more GREL functions

## [0.6.1] - 2021-03-02

### Fixed

- updated metadata

## [0.6.0] - 2021-03-02

### Added

- toTitlecase, lastIndexOfMapping, sha1, md5

## [0.5.2] - 2020-06-23

### Fixed

- metadata mixup (#2)

## [0.5.1] - 2020-06-19

### Fixed

- chomp subject resource

## [0.5.0] - 2020-03-30

### Added

- Function: ArrayFunctions.get, StringFunctions.chomp

## [0.4.0] - 2020-03-03

### Changed

- Keep old GREL prefix `http://users.ugent.be/~bjdmeest/function/grel.ttl#`

## [0.3.1] - 2020-02-27

### Fixed

- Only use JAVA classes, no primitives

## [0.3.0] - 2020-02-18

### Fixed

- removed deprecated GREL functions

[0.7.3]: https://github.com/FnOio/grel-functions-java/compare/v0.7.1..v0.7.3
[0.7.2]: https://github.com/FnOio/grel-functions-java/compare/v0.7.1..v0.7.2
[0.7.1]: https://github.com/FnOio/grel-functions-java/compare/v0.7.0..v0.7.1
[0.7.0]: https://github.com/FnOio/grel-functions-java/compare/v0.6.1..v0.7.0
[0.6.1]: https://github.com/FnOio/grel-functions-java/compare/v0.6.0..v0.6.1
[0.6.0]: https://github.com/FnOio/grel-functions-java/compare/v0.5.2..v0.6.0
[0.5.2]: https://github.com/FnOio/grel-functions-java/compare/v0.5.1..v0.5.2
[0.5.1]: https://github.com/FnOio/grel-functions-java/compare/v0.5.0..v0.5.1
[0.5.0]: https://github.com/FnOio/grel-functions-java/compare/v0.4.0..v0.5.0
[0.4.0]: https://github.com/FnOio/grel-functions-java/compare/v0.3.1..v0.4.0
[0.3.1]: https://github.com/FnOio/grel-functions-java/compare/v0.3.0..v0.3.1
[0.3.0]: https://gitlab.ilabt.imec.be/fno/lib/grel-functions-java/compare/v0.2.0...v0.3.0
