# Releasing grel-functions-java

Step-by-step instructions for publishing a release. [HANDBOOK.md](HANDBOOK.md) (Release process) explains how the tooling works.

## Before you start

- Everything changed since the previous release is reviewed: the code for correctness, and the documentation and changelog for accuracy and brevity.
- bash (Git Bash on Windows), Maven, Java 17 or later, and `changefrog` (`npm install -g changefrog`), which writes the version section of `CHANGELOG.md`.
- Push access to `origin` (https://gitlab.ilabt.imec.be/KNoWS/fno/lib/grel-functions-java).
- You are on `development`, up to date with `origin/development`, with a clean working tree.
- The tests pass: `mvn verify`.
- `## Unreleased` in `CHANGELOG.md` lists everything since the last release.
- Pick the version with [Semantic Versioning](https://semver.org/), in the format `vX.Y.Z` (written `<version>` below). `pom.xml` holds the next patch as `-SNAPSHOT`: release that patch, or a higher minor or major version when the changelog has new features or breaking changes.

## Release

1. Run `./bump-version.sh <version>` and answer `y` to both questions. The script
   - sets the version in `pom.xml` and the version in `README.md`;
   - turns `## Unreleased` into the version section of `CHANGELOG.md`;
   - commits "Update version to <version>", pushes `development`, and creates and pushes the tag `<version>`;
   - moves `pom.xml` to the next patch `-SNAPSHOT`, and commits and pushes "Prepare for next development cycle".
2. Move `master` to the release: `git push origin <version>^{commit}:master`. `master` always points at the latest release; the push succeeds only as a fast-forward.
3. GitLab mirrors the branches and tags to GitHub (https://github.com/FnOio/grel-functions-java); check that the tag `<version>` is there.
4. JitPack builds the release from the GitHub tag the first time it is requested. Request it at `https://jitpack.io/#FnOio/grel-functions-java/<version>` ("Get it"), check that the build log is green, and that `https://jitpack.io/com/github/fnoio/grel-functions-java/<version>/` serves the jar.

## After the release

- Update the consumers: MappingWeaver-java (`grel-functions.version`), function-agent-java (test dependency, `grel.java.version`) and rmlmapper-java: update the version they use.

## When something goes wrong

- The script stops at the first failing command. When it stops before pushing, fix the cause, discard its changes (`git reset --hard origin/development`) and run it again. When it stops after pushing `development`, finish by hand from where it stopped: create the tag `<version>` on the version commit if it is missing, push it, then set the next `-SNAPSHOT` (`mvn versions:set -DnewVersion=<next>-SNAPSHOT -DgenerateBackupPoms=false`), commit and push.
- Once the tag is pushed, keep it: fix the cause and retry the failed pipeline job. When the released code itself is broken, release the next patch version.
