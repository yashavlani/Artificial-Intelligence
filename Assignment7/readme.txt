NAME: YASH AVLANI
UTA ID:1001670008

TOWEROFHANOI
Common facts:
(A Peg)		A is peg
(B Peg)		B is peg

(C Peg)		C is peg
(disk1 Disk)	disk1 is disk

(disk2 Disk)	disk2 is disk
(disk3 Disk)	disk3 is disk

(disk4 Disk)	disk4 is disk

(disk5 Disk)	disk5 is disk
(bigger disk2 disk1) (bigger disk3 disk1) (bigger disk4 disk1) (bigger disk5 disk1) (bigger disk3 disk2) (bigger disk4 disk2) (bigger disk5 disk2) (bigger disk4 disk3) (bigger disk5 disk3) (bigger disk5 disk4)


Terminology

(bigger X Y) : disk X is bigger than disk Y
(on x y) : disk x is on y(y can be a bigger disk or a peg)

OPERATIONS:
operator
 moveFromPegToDisk
 : moves one disk from peg to another bigger disk 
operator
 moveFromPegToPeg
 : moves one disk from peg to another peg
operator
 moveFromDiskToDisk
 : moves one disk from one disk to another bigger disk
operator
 moveFromDiskToPeg
 : moves one disk from disk to a peg.

7Puzzle
Common facts:
(tile_1 Tile)		tile_1 is a tile
(tile_2 Tile)		tile_2 is a tile
(tile_3 Tile)		tile_3 is a tile

(tile_4 Tile)		tile_4 is a tile
(tile_5 Tile)		tile_5 is a tile
(tile_6 Tile)
		tile_6 is a tile
(tile_7 Tile)		tile_7 is a tile
(pos_1 Position)	Position 1

(pos_2 Position)	Position 2
(pos_3 Position)
	Position 3
(pos_4 Position)
	Position 4
(pos_5 Position)
	Position 5
(pos_6 Position)	Position 6
(pos_7 Position)	Position 7
(pos_8 Position)	Position 8
(pos_9 Position)	Position 9
(neighbour pos_1 pos_2) (neighbour pos_1 pos_4) (neighbour pos_2 pos_1) (neighbour pos_2 pos_3) (neighbour pos_2 pos_5) (neighbour pos_3 pos_2) (neighbour pos_3 pos_6)
 (neighbour pos_4 pos_1) (neighbour pos_4 pos_5) (neighbour pos_4 pos_7) (neighbour pos_5 pos_2) (neighbour pos_5 pos_4) (neighbour pos_5 pos_6) (neighbour pos_5 pos_8) (neighbour pos_6 pos_3) (neighbour pos_6 pos_5) (neighbour pos_6 pos_9)
 (neighbour pos_7 pos_4) (neighbour pos_7 pos_8) (neighbour pos_8 pos_5) (neighbour pos_8 pos_7) (neighbour pos_8 pos_9) (neighbour pos_9 pos_6) (neighbour pos_9 pos_8)
 

Terminology

(neighbour x y)	position x and position y are neighbour
(on x y) tile x on position y

OPERATIONS:
operator
 MoveFromOneToOne
 : move tile from one position to other