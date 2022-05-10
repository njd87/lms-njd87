# LMS Koika

An early experiment.

## Install
- in parent directory: `git clone https://github.com/TiarkRompf/lms-clean.git`
- `cd lms-clean`
- `git remote add namin git@github.com:namin/lms-clean.git`
- `git fetch --all`
- `git checkout vscode`
- `sbt publishLocal`

## Run
- `sbt test`

## Troubleshoot
- It works in Java v1.8. In Mac OS X: `export JAVA_HOME=$(/usr/libexec/java_home -v1.8)`
