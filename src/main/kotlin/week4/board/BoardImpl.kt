package week4.board

import board.Cell
import board.Direction
import board.Direction.*
import board.GameBoard
import board.SquareBoard

open class SquareBoardImpl(final override val width: Int) : SquareBoard {
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

class GameBoardImpl<T>(width: Int) : GameBoard<T>, SquareBoardImpl(width) {
    private val cellsMap = mutableMapOf<Cell, T?>()

    init {
        for (cell in getAllCells()) {
            cellsMap[cell] = null
        }
    }

    override fun get(cell: Cell): T? {
        return cellsMap[cell]
    }

    override fun set(cell: Cell, value: T?) {
        cellsMap[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        val values = cellsMap.filterValues(predicate)
        return values.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        val filtered = filter(predicate)
        if (filtered.isEmpty()) return null
        return filtered.first()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        val filtered = filter(predicate)
        if (filtered.isNotEmpty()) return true
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        val filtered = filter(predicate)
        if (filtered.size == cellsMap.size) return true
        return false
    }

}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)

