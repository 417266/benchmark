#!/bin/bash
set -e
cd "$(dirname "$0")"
prettier --write --plugin="$(npm root -g)/@prettier/plugin-xml/src/plugin.js" "**/*.{js,ts,jsx,tsx,md,xml}"
ktfmt --kotlinlang-style .
swiftformat .
dart format .
