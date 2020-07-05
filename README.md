# automatic-lawnmower

## Subject
A company is developing an automatic lawnmower, for rectangular fields.

The lawnmower is represented by a coordinate pair (x,y) for its position and an orientation (N, S, E, W).
The field is represented by a grid, to simplify the problem.

For example, the lawnmower can be defined by `0, 0, N`, meaning she is at the bottom left case, and looking toward North.

To control the lawnmower, we can send `D`, `G`or `A`.
* `D` make it turn toward its right.
* `G` make it turn toward its left.
* `A` make it advance one case, without rotating.

If `A` make it advance outside the field, or on a case where another lawnmower is, the lawnmower will not move, ignore the instruction and go to the next instruction.

The project should be an app, exposing an endpoint accepting a payload as follows:
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA 
```
The first line represents the field's dimension.
Every two following lines represents:
* A lanwmower initial position
* The set of instruction for the above lawnmower

Each lawnmower is moved sequentially. Meaning that the second defined lawnmower will only move after the first lawnmower has finished its set of instructions, and stopped moving.

## Running the application locally
* If you have gradle installed and under linux/mac:
`gradle bootRun`

* If gradle is not installed, but still under linux/mac: `gradlew bootRun`

* And for windows without gradle: `gradlew.bat bootRun`

The server should be hosted on `http://localhost:8080`. You can check if it's up by pinging it on `http://localhost:8080/ping`.

## Execute the movement simulation

Make a POST request to `http://localhost:8080/moveSimulation` with a text/plain payload like:
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA 
```

You should receive this body in response:
```
1 3 N
5 1 E

```