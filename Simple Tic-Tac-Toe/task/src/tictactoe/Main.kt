package tictactoe

import kotlin.math.abs

fun main() {
    // write your code here
    val grid = listOf(
        mutableListOf('_', '_', '_'),
        mutableListOf('_', '_', '_'),
        mutableListOf('_', '_', '_'),
    )
//    readGrid(grid)
    showGrid(grid)
    play(grid)
//    analyzeGrid(grid)
}

fun play(grid: List<MutableList<Char>>) {
    var player = 'X'
    while (!analyzeGrid(grid)) {
        readInput(player, grid)
        if (player == 'X') player = 'O' else player = 'X'
        showGrid(grid)
    }
}

fun readInput(player: Char, grid: List<MutableList<Char>>) {
    while (true) {
        println("Enter the coordinates: ")
        val (val1, val2) = readLine()!!.split(" ")
        val x = val1.toIntOrNull()
        val y = val2.toIntOrNull()
        if (x == null || y == null) {
            println("You should enter numbers!")
            continue
        }
        if (x !in 1..3 || y !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        if (grid[x - 1][y - 1] != '_') {
            println("This cell is occupied! Choose another one!")
            continue
        }
        grid[x - 1][y - 1] = player
        break
    }
}

fun analyzeGrid(grid: List<MutableList<Char>>): Boolean {
    var countX = 0
    var countO = 0
    for (i in 0..2) {
        for (j in 0..2) {
            if (grid[i][j] == 'X') {
                countX++
            } else if (grid[i][j] == 'O') {
                countO++
            }
        }
    }
    if (abs(countX - countO) > 1) {
        println("Impossible")
        return true
    }
    val isXwins = checkWin('X', grid)
    val isOwins = checkWin('O', grid)

    if (isXwins && isOwins) {
        println("Impossible")
        return true
    }
    if (isXwins) {
        println("X wins")
        return true
    } else if (isOwins) {
        println("O wins")
        return true
    }
    if (countX + countO == 9) {
        println("Draw")
        return true
    }
    System.err.println("Game not finished")
    return false
}

fun checkWin(player: Char, grid: List<MutableList<Char>>): Boolean {
    for (i in 0..2) {
        if (grid[0][i] == player && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] ||
            grid[i][0] == player && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
            System.err.println("line/column wins")
            return true
        }
    }
    if (grid[1][1] == player &&
        (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] ||
        grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2])) {
        System.err.println("diagonal wins")
        return true
    }
    return false
}

fun readGrid(grid: List<MutableList<Char>>) {
    print("Enter cells: ")
    val cells = readLine()!!
    for (i in 0..2) {
        for (j in 0..2) {
            grid[i][j] = cells[3 * i + j]
        }
    }
}

fun showGrid(grid: List<MutableList<Char>>) {
    println("---------")
    for (line in grid) {
        println("| ${line.joinToString(" ")} |")
    }
    println("---------")
}
