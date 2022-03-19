package ru.itmo.wp.web.page;

import ru.itmo.wp.model.Cell;
import ru.itmo.wp.model.Phases;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    static final int BOARD_SIZE = 3;
    static final int INLINE_TO_WIN = 3;

    private static State state;
    //=========================================================//
    public static class State {
        private final int size;
        private final Cell[][] cells;
        private Phases phase;
        private boolean crossesMove;
        private int emptyCells;
        //=========================================================//
        public State(final int size) {
            this.size = size;
            this.cells = new Cell[size][size];
            this.phase = Phases.RUNNING;
            this.crossesMove = true;
            this.emptyCells = size * size;
        }
        //=========================================================//
        private Cell getCurrentPlayer() {
            if (crossesMove) {
                return Cell.X;
            }
            return Cell.O;
        }

        private void setPhase(Cell player) {
            if (player == Cell.X) {
                phase = Phases.WON_X;
            } else if (player == Cell.O) {
                phase = Phases.WON_O;
            } else if (player == Cell.NO_ONE) {
                if (emptyCells == 0) {
                    phase = Phases.DRAW;
                } else {
                    phase = Phases.RUNNING;
                }
            }
        }

        private boolean isLegalMove(int row, int column) {
            return row >= 0 && row < size &&
                    column >= 0 && column < size &&
                    cells[row][column] == null;
        }

        private void makeMove(int row, int column, Cell player) {
            if (state.isLegalMove(row, column)) {
                cells[row][column] = player;
                emptyCells--;
                if (checkWin(row, column, player)) {
                    setPhase(player);
                    return;
                }
                state.crossesMove = !state.crossesMove;
            }
            setPhase(Cell.NO_ONE);
        }

        private int streakInLine(int row, int column, int deltaX, int deltaY, Cell currentPlayer) {
            int streak = 0;
            while (row >= 0 && row < size && column >= 0 && column < size &&
                    cells[row][column] != null && cells[row][column].equals(currentPlayer))
            {
                streak++;
                row += deltaX;
                column += deltaY;
            }
            return streak;
        }

        private boolean checkLine (int row, int column, int deltaX, int deltaY, Cell currentPlayer) {
            return streakInLine(row, column, deltaX, deltaY, currentPlayer) +
                    streakInLine(row, column, -deltaX, -deltaY, currentPlayer) - 1 >= INLINE_TO_WIN;
        }

        private boolean checkWin (int row, int column, Cell currentPlayer) {
            return checkLine(row, column, 0, 1, currentPlayer) ||
                    checkLine(row, column, 1, 0, currentPlayer) ||
                    checkLine(row, column, 1, 1, currentPlayer) ||
                    checkLine(row, column, -1, 1, currentPlayer);
        }
        //=========================================================//
        public int getSize() {
            return size;
        }

        public Cell[][] getCells() {
            return cells;
        }

        public Phases getPhase() {
            return phase;
        }

        public boolean getCrossesMove() {
            return crossesMove;
        }
    }
    //=====================================================//
    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (state == null) {
            newGame(request, view);
        }
        view.put("state", state);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        state = new State(BOARD_SIZE);
        view.put("state", state);
        throw new RedirectException(request.getRequestURI());
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        if (state.phase == Phases.RUNNING) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            int row = -1, column = -1;

            for (int i = 0; i < state.size; i++) {
                for (int j = 0; j < state.size; j++) {
                    if (parameterMap.containsKey("cell_" + i + j)) {
                        row = i;
                        column = j;
                    }
                }
            }
            state.makeMove(row, column, state.getCurrentPlayer());
        }
        view.put("state", state);
        throw new RedirectException(request.getRequestURI());
    }
}