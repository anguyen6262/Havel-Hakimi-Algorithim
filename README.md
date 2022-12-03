# Havel-Hakimi-Algorithm
## Overview:
The graph realization problem (GRP) is as follows: "Given a finite sequence of nonnegative integers, is there a simple graph that has that sequence as its degree sequence (disregarding order)? The Havel-Hakimi algorithm is one way to answer this question. Given a sequence, it breaks the sequence down one value at a time until it is a list of zeroes. It does this by assuming the existence of a graph with that degree sequence where the vertex of maximal degree n is adjacent to the next n highest-degree vertices. It then removes this vertex of maximal degree and the edges attached to it. This process repeats until all vertices are removed (in which case the answer to the GRP is yes) or a contradiction is found (so the answer is no). These contradiction include the following:
1. The sum of degrees of vertices is odd. This is not allowed because the degree sum is twice the number of edges, and the number of edges is a nonnegative integer.
2. The maximal degree is greater than or equal to the number of vertices. If this is the case, there must be repeated edges, which is not allowed in a simple graph.

## Instructions:
To begin, type in a list of natural numbers as follows: a, b, c, d... using only nonnegative integers separated by commas. If the sequence is graphical (there exists a graph with that degree sequence), you will be shown [unsure right now what will display at start]. "Next" and "Previous" allow you to scroll through the graph reconstruction process, and you are free at any time to enter another degree sequence to check.

