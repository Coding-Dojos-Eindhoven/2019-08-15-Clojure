# Clojure Coding Dojo: Tic Tac Toe

This project was created using [Nightcode](https://sekao.net/nightcode/) version 2.7.0.

Start by looking at [game.clj](./src/tic_tac_toe/game.clj).

Note: This assignment was heavily inspired by episodes 2-5 of the
["Functional Design in Clojure" podcast](https://clojuredesign.club).

Below is the content of the Gist that was used during the Coding Dojo:

<hr />

# Welcome to the Clojure Coding Dojo

> Programming-language syntax is a lot like politics: very few people do it for a living but virtually everyone has an opinion. And many have strong opinions. 

*Russ Olsen, "Getting Clojure", The Pragmatic Programmers (2018), p. 229*

We wish you an open mind and an insightful evening! 

## Pointers

* A good place to find Clojure core API documentation is [clojuredocs.org](https://clojuredocs.org).
* If you want to make a server, have a look at [Aleph](https://github.com/ztellman/aleph).
* Book (free to read online): [Clojure for the Brave and True](https://www.braveclojure.com). Supposedly littered with humor. But I read:
* Book: [Getting Clojure](https://pragprog.com/book/roclojure/getting-clojure). Explicitly addresses how things are done in practice and pitfalls by including a section "Staying out of Trouble" and "In the Wild" in every chapter.

## Unguided Track

Implement the game Tic Tac Toe.

Start by implementing (only) the game's logic:

* Create a representation of the game board and logic needed to update it.
* Create logic to play 9 moves of a game that ends in a tie (so don't worry about detecting winners yet).
* Detect that somebody has won and stop the game at that point.

All of that can be done completely in the REPL, no need to run ANYTHING!

When you have reached this point, there's several things that you can do depending on what you think is most fun:

* Not only print _who_ won, but also _why_.
* Allow the two players to play against each other remotely. You can use [Aleph](https://github.com/ztellman/aleph) for the HTTP handling. As UI you can use CURL: this way you can still very easily read the desired location on the board, and output the board like you did initially as the HTTP response, so that CURL prints it for you.
* Extend the server so that it supports multiple parallel games.
* Error handling: board position is already taken, trying to place outside the board, etc.
* Implements a GUI for the client
* Make your own game rules :)

## Guided Track

Make sure you have this gist open and just follow along.
