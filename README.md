# ShortestPath

For this problem, given x number of nodes, the nodes themselves, and any connections/paths between those nodes, determine if there is a path between given node A and given node B, and if there is, find the shortest path between A and B.

This was accomplished through using an adjacency list to represent the undirected graph and then performing breadth first search until node B was found.

Example input:
4,W,X,Y,Z,W-Y,Y-X,Y-Z,W-X

Example: path between W and Z
W-Y -> Y-Z
or
W-X -> X-Y -> Y-Z

Example output:
WYZ
