package board

import board.Direction.*
import java.lang.IllegalArgumentException

open class SquareBoardImpl(override val width: Int) : SquareBoard {

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i < 1 || j < 1 || i > width || j > width) return null
//        return cells[(i - 1) + width * (j - 1)]
        return Cell(i, j)
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i < 1 || j < 1 || i > width || j > width) throw IllegalArgumentException("Out of range!")
//        return cells[(i - 1) + width * (j - 1)]
        return Cell(i, j)
    }

    override fun getAllCells(): Collection<Cell> {
        val cells = mutableListOf<Cell>()
        for (i in 0 until width) {
            for (j in 0 until width) {
                cells[i + width * j] = Cell(i + 1, j + 1)
            }
        }
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val list = mutableListOf<Cell>()
        if (jRange.first < jRange.last) {
            if (jRange.last > width) {
                for (j in jRange.first..width) {
                    list.add(getCell(i, j))
                }
            }
        } else
            if (jRange.last < jRange.first) {
                if (jRange.first > width) {
                    for (j in width..jRange.last) {
                        list.add(getCell(i, j))
                    }
                }
            } else {
                for (j in jRange) {
                    list.add(getCell(i, j))
                }
            }
        return list
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val list = mutableListOf<Cell>()
        for (i in iRange) {
            list.add(getCell(i, j))
        }
        return list
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(this.i, this.j - 1)
            DOWN -> getCellOrNull(this.i, this.j + 1)
            LEFT -> getCellOrNull(this.i - 1, this.j)
            RIGHT -> getCellOrNull(this.i + 1, this.j)
        }
    }
}

class GameBoardImpl<T>(override val width: Int) : GameBoard<T>, SquareBoardImpl(width) {
    private val cellsMap = mutableMapOf<Cell, T?>()

    init {
        for (i in 1..width) {
            for (j in 1..width) {
                cellsMap[Cell(i, j)] = null
            }
        }
    }

    override fun get(cell: Cell): T? {
        return cellsMap[cell]
    }

    override fun set(cell: Cell, value: T?) {
        cellsMap[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        TODO("Not yet implemented")
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()

