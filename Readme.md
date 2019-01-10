EX 04
=====
Part 1
------------------------------------------
game explanation
1. First we create game in the server, ny calling the class Play.
2. Upload the game csv file to the server.
3. Give your player his first position in GPS coordinates.
4. To play the game you must use the rotate function.
5. The rotate fuction in moving the "robots" on the server.
6. The goal is to eat fruits before the other pacmans eat them.
   and avoid tuckle with the borders.
7. The game is runing for 100 seconds and the score of the player is the weight of every fruit.

Part2
-------------------------------------------



Part 3 - indipendence algorithm for pacman to win the game.
-------------------------------------------
1. First the pacman need to be the placed in his first position near as much possible to the
   biggest group of fruits.
2. Then the algorithm computes the shortest path to the other fruits, computes the speed and the distance of the pacman and 
   creating the shortest path for the player.
3. The algorithm must include dijkstra algorithm to take the shortset path after calculating various of paths
   include the angles of the borders.
4. The algorithm looking at the edges of any border and return the shortest path by calculating it.   