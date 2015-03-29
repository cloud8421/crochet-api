.PHONY: build server test console

build:
	lein ring uberjar

server:
	lein ring server

test:
	lein test

console:
	lein repl
