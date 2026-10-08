#!/usr/bin/env bash

# exit if command fails
set -e

if [ -z "$1" ]
then
	echo 'Version parameter not given. Invoke as e.g. ./bump-version.sh v1.0.0'.
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
echo "Changing version to $VERSION"

echo 'Updating pom.xml...'
mvn versions:set -DnewVersion=$VERSION

echo 'Updating README.md...'
sed -i -e "s|<version>.*<\/version>|<version>$VERSION</version>|" README.md

if [ ! "$(yes_or_no 'Do you also want to add the version to CHANGELOG.md?')" ]
then
	changefrog -n $VERSION
fi

if [[ $VERSION == testrelease-* ]] ; then
	tagname=$VERSION
else
	tagname="$VERSION"
fi

if [ ! "$(yes_or_no Do you also want to commit the changes, create a git tag $tagname and push it?)" ]
then
	git add CHANGELOG.md README.md pom.xml
	git commit -m "Update version to $VERSION"
	git push origin
	git tag $tagname
	git push origin $tagname

	# Prepare for the next development cycle, so a local build is
	# distinguishable from the release.
	if [[ $VERSION != testrelease-* ]] ; then
		NEXT="${VERSION%.*}.$((${VERSION##*.} + 1))-SNAPSHOT"
		mvn versions:set -DnewVersion=$NEXT -DgenerateBackupPoms=false
		git add pom.xml
		git commit -m "Prepare for next development cycle"
		git push origin
	fi
fi
