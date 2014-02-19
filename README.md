##BKTrees

[![Build Status](https://travis-ci.org/joshdickson40/BKTrees.png?branch=master)](https://travis-ci.org/joshdickson40/BKTrees)

#### Overview

This projects illustrates the usage of a BK (Burkhard Keller) Tree as a means for partial matching a query with some library of data. BK trees have a number of real world usages, such as spell check.

The advantage of utilizing a BK tree to hold a data store comes in that queries into the library do not require a complete traversal of the data set. Common implementations may only query a small percentage of their libraries, depending on the maximum allowed edit distance allowed for the query.

We implement our BK Tree with generics such that an implementor need only define the type of node contained in the tree, along with providing an implementation of a Comparator capable of returning the edit distance between nodes.

#### License

This software is released under the MIT license http://opensource.org/licenses/MIT.

#### Contact

Forward any feedback to the author at josh dot dickson at wpi dot edu.



