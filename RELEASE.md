# Releasing grel-functions-java

Step-by-step instructions for publishing a release. [HANDBOOK.md](HANDBOOK.md) (Release process) explains how the tooling works.

## Before you start

- bash (Git Bash on Windows), Maven, Java 17 or later, and `changefrog` (`npm install -g changefrog`), which writes the version section of `CHANGELOG.md`.
- Push access to `origin` (https://gitlab.ilabt.imec.be/KNoWS/fno/lib/grel-functions-java).
- You are on `development`, up to date with `origin/development`, with a clean working tree.
- The tests pass: `mvn verify`.
- `## Unreleased` in `CHANGELOG.md` lists everything since the last release.
- Pick the version with [Semantic Versioning](https://semver.org/). `pom.xml` holds the next patch as `-SNAPSHOT` (e.g. `v0.10.2-SNAPSHOT`): release that patch, or a higher minor or major version when the changelog has new features or breaking changes.

## Release

1. Run `./bump-version.sh v0.10.2` (with your version) and answer `y` to both questions. The script
   - sets the version in `pom.xml` and the version in `README.md`;
   - turns `## Unreleased` into the version section of `CHANGELOG.md`;
   - commits "Update version to <version>", pushes `development`, and creates and pushes a tag named after the version (e.g. `v0.10.2`);
   - moves `pom.xml` to the next patch `-SNAPSHOT`, and commits and pushes "Prepare for next development cycle".
2. Move `master` to the release: `git push origin v0.10.2^{commit}:master`. `master` always points at the latest release; the push succeeds only as a fast-forward.
3. JitPack builds the release from the tag on GitHub the first time it is requested. Request it at https://jitpack.io/#FnOio/grel-functions-java/v0.10.2 ("Get it"), check that the build log is green, and that https://jitpack.io/com/github/fnoio/grel-functions-java/<version>/ serves the jar.

## After the release

- GitLab mirrors the branches and tags to GitHub (https://github.com/FnOio/grel-functions-java); check that the tag is there.
- Update the consumers: MappingWeaver-java (`grel-functions.version`), function-agent-java (test dependency, `grel.java.version`) and rmlmapper-java: update the version they use.

## When something goes wrong

- The script stops at the first failing command. When it stops before pushing, fix the cause, discard its changes (`git reset --hard origin/development`) and run it again. When it stops between pushing `development` and pushing the tag, finish by hand: create and push the tag on the version commit, then set the next `-SNAPSHOT` (`mvn versions:set -DnewVersion=<next>-SNAPSHOT -DgenerateBackupPoms=false`), commit and push.
- Once the tag is pushed, keep it: fix the cause and retry the failed pipeline job. When the released code itself is broken, release the next patch version.
