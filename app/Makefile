# Makefile
# Usage: gendiff [-hV] [-f=<format>] <filepath1> <filepath2>
FILEPATH1=src/test/resources/json/file1.json
FILEPATH2=src/test/resources/json/file2.json
FILEPATH3=src/test/resources/yaml/file1.yml
FILEPATH4=src/test/resources/yaml/file2.yml
FORMAT1?=stylish
FORMAT2?=plain
FORMAT3?=json

.PHONY: build

run-dist: build test lint test-report run # Build, check and run app

run: # Run app
	./build/install/app/bin/app -f $(FORMAT1) $(FILEPATH1) $(FILEPATH2)

run-json-stylish: # Run app
	./build/install/app/bin/app -f $(FORMAT1) $(FILEPATH1) $(FILEPATH2)

run-yaml-plain: # Run app
	./build/install/app/bin/app -f $(FORMAT2) $(FILEPATH3) $(FILEPATH4)

build: # Build app
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test-report: # Check code coverage
	./gradlew jacocoTestReport
