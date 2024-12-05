# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## Unreleased

### Fixed
- `DateFunctions#toData` should be public.
- `ArrayFunctions#get` takes a `List` as parameter and returns a `List` as described in `grel.ttl`, not `Object`s.

## [0.9.1] - 2022-12-15

### Fixed
- Allow the separator of `ArrayFunctions.join()` to be `null` which is considered to be the empty string.  

## [0.9.0] - 2022-12-08

### Added
- Possibility to apply a format in `StringFunctions.toString()` function
- Documentation in code

### Changed
- Require Java 11+
- Updated JUnit from 4.13.2 to 5.9.1
- Updated commons-lang 2.6 to commons-lang3 3.12.0
- Added commons-text 1.10.0 (some classes originating from commons-lang are put in here)
- Removed unused dependency on jackson-databind
- let `StringFunctions.split()` return an array instead of a list.

### Fixed
- `DateFunctions.now()` returns the current time in UTC as defined by the GREL funtions (not in local timezone).
- `StringFunctions.replace()` now performs string replace as well as regex replace.
- `StringFunctions.match()`: fixed GREL regex matching
- `StringFunctions.toNumber()`: expanded conversion to `Number`, basically covering all numbers, not just integers.
- `StringFunctions.phonetic()`: the right encoder was not selected; the metaphone and double methaphone encoders were not configured correctly.
- `StringFunctions.reinterpret()`: add target encoding which is necessary to perform the right conversion.
- `StringFunctions.unicode()`: use code points instead of uri encoding.
- `OtherFunctions.type()`: return the name of the type instead of the Class of the type.

## [0.8.2] - 2022-09-26

### Added
- Implementation of Math functions

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

[0.9.1]: https://github.com/FnOio/grel-functions-java/compare/v0.9.0..v0.9.1
[0.9.0]: https://github.com/FnOio/grel-functions-java/compare/v0.8.2..v0.9.0
[0.8.2]: https://github.com/FnOio/grel-functions-java/compare/v0.7.3..v0.8.2
[0.7.3]: https://github.com/FnOio/grel-functions-java/compare/v0.7.2..v0.7.3
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
