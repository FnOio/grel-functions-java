#!/usr/bin/env bash

# Copies the FnO descriptions of GREL from a grel-functions-fno release into the jar.
#   ./sync-descriptions.sh <date>    copy release <date> (YYYY-MM-DD) to src/main/resources/functions_grel.ttl
#   ./sync-descriptions.sh --check   check that the file is the release its dcterms:modified names
# Exit code 0: copied or matching; 1: invalid arguments or differing; 2: the download failed.

set -e
cd "$(dirname "$0")"

LOCAL=src/main/resources/functions_grel.ttl
# The public GitHub mirror of grel-functions-fno; GitLab needs a login.
DIST=https://raw.githubusercontent.com/FnOio/grel-functions-fno/master/_dist

if [ "$1" = --check ]; then
	DATE=$(sed -n -E 's|.*dcterms:modified +"([0-9-]+)"\^\^xsd:date.*|\1|p' "$LOCAL" | head -1)
else
	DATE=$1
fi
if [[ ! $DATE =~ ^[0-9]{4}-[0-9]{2}-[0-9]{2}$ ]]; then
	echo 'Invoke as ./sync-descriptions.sh YYYY-MM-DD or ./sync-descriptions.sh --check'
	exit 1
fi

RELEASE=$(mktemp)
trap 'rm -f "$RELEASE"' EXIT
if ! curl -fsSL -o "$RELEASE" "$DIST/$DATE.ttl"; then
	echo "Downloading $DIST/$DATE.ttl failed."
	exit 2
fi

if [ "$1" = --check ]; then
	# Line endings depend on the checkout, so they are ignored.
	if diff -u --strip-trailing-cr --label "$DATE.ttl" --label "$LOCAL" "$RELEASE" "$LOCAL"; then
		echo "$LOCAL is release $DATE."
	else
		echo
		echo "$LOCAL differs from release $DATE: run ./sync-descriptions.sh $DATE."
		exit 1
	fi
else
	cp "$RELEASE" "$LOCAL"
	echo "Copied release $DATE to $LOCAL."
fi
