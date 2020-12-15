package week4.board

import board.Cell
import board.Direction
import board.Direction.*
import board.GameBoard
import board.SquareBoard

class SquareBoardImpl(override val width: Int) : SquareBoard {
    private val cells = mutableSetOf<Cell>()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                cells.add(Cell(i, j))
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i < 1 || j < 1 || i > width || j > width) return null
        return cells.first { cell -> cell.i == i && cell.j == j }
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i < 1 || j < 1 || i > width || j > width) throw IllegalArgumentException("Out of range!")
        return cells.first { cell -> cell.i == i && cell.j == j }
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val list = mutableListOf<Cell>()
        for (j in jRange) {
            try {
                list.add(getCell(i, j))
            } catch (e: java.lang.IllegalArgumentException) {
                continue
            }
        }
        return list
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val list = mutableListOf<Cell>()
        for (i in iRange) {
            try {
                list.add(getCell(i, j))
            } catch (e: java.lang.IllegalArgumentException) {
                continue
            }
        }
        return list
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(this.i - 1, this.j)
            DOWN -> getCellOrNull(this.i + 1, this.j)
            LEFT -> getCellOrNull(this.i, this.j - 1)
            RIGHT -> getCellOrNull(this.i, this.j + 1)
        }
    }
}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

