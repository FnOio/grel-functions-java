#!/usr/bin/env bash

# exit if command fails
set -e

if [ -z "$1" ]
then
	echo 'Version parameter not given. Invoke as e.g. ./bump-version.sh 1.0.0'.
	exit 1
fi

# function to read `y` (yes) or `n` (no).
function yes_or_no {
	while true; do
		read -p "$* [y/n]: " yn
		case $yn in
			[Yy]*) return 1  ;;
			[Nn]*) echo "Aborted" ; return 0 ;;
		esac
	done
}


VERSION=$1

# A release version is X.Y.Z; the next development version is derived from it.
if [[ ! $VERSION =~ ^[0-9]+\.[0-9]+\.[0-9]+$ && $VERSION != testrelease-* ]]; then
	echo "Version must be X.Y.Z or testrelease-*, got: $VERSION"
	exit 1
fi

echo "Changing version to $VERSION"

echo 'Updating pom.xml...'
mvn versions:set -DnewVersion=$VERSION -DgenerateBackupPoms=false

echo 'Updating README.md...'
# JitPack names the version after the tag, so the README snippet carries the `v` prefix.
sed -i -e "s|<version>.*<\/version>|<version>v$VERSION</version>|" README.md

if [ ! "$(yes_or_no 'Do you also want to add the version to CHANGELOG.md?')" ]
then
	changefrog -n $VERSION
fi

tagname="v$VERSION"
if [ ! "$(yes_or_no "Do you also want to commit the changes, create a git tag $tagname and push it?")" ]
then
	git add CHANGELOG.md README.md pom.xml
	git commit -m "Update version to $VERSION"
	git push
	git tag $tagname
	git push origin $tagname

	# Prepare for the next development cycle, so a local build is
	# distinguishable from the release.
	if [[ $VERSION != testrelease-* ]] ; then
		NEXT="${VERSION%.*}.$((${VERSION##*.} + 1))-SNAPSHOT"
		mvn versions:set -DnewVersion=$NEXT -DgenerateBackupPoms=false
		git add pom.xml
		git commit -m "Prepare for next development cycle"
		git push
	fi
fi
