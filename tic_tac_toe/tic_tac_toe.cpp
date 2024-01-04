#include <iostream>
#include <vector>

class Board {
public:
    Board() : board(3, std::vector<char>(3, ' ')) {}

    void display() const {
        std::cout << "  0 1 2\n";
        for (int i = 0; i < 3; ++i) {
            std::cout << i << ' ';
            for (int j = 0; j < 3; ++j) {
                std::cout << board[i][j] << ' ';
            }
            std::cout << '\n';
        }
    }

    bool placeMarker(int row, int col, char marker) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false;  // Invalid move
        }

        board[row][col] = marker;
        return true;  // Valid move
    }

    bool checkWin(char marker) const {
        // Check rows and columns
        for (int i = 0; i < 3; ++i) {
            if (board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) {
                return true;  // Row win
            }
            if (board[0][i] == marker && board[1][i] == marker && board[2][i] == marker) {
                return true;  // Column win
            }
        }

        // Check diagonals
        if (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) {
            return true;  // Diagonal win
        }
        if (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker) {
            return true;  // Diagonal win
        }

        return false;  // No win
    }

    bool isFull() const {
        for (const auto& row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;  // Board is not full
                }
            }
        }
        return true;  // Board is full
    }

private:
    std::vector<std::vector<char>> board;
};

class Game {
public:
    Game() : currentPlayer('X'), gameOver(false) {}

    void play() {
        while (!gameOver) {
            board.display();
            getPlayerMove();
            if (board.checkWin(currentPlayer)) {
                board.display();
                std::cout << "Player " << currentPlayer << " wins!\n";
                gameOver = true;
            } else if (board.isFull()) {
                board.display();
                std::cout << "It's a draw!\n";
                gameOver = true;
            } else {
                switchPlayer();
            }
        }
    }

private:
    void getPlayerMove() {
        int row, col;
        std::cout << "Player " << currentPlayer << ", enter your move (row and column): ";
        std::cin >> row >> col;
        if (board.placeMarker(row, col, currentPlayer)) {
            // Valid move
        } else {
            std::cout << "Invalid move. Try again.\n";
            getPlayerMove();
        }
    }

    void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    Board board;
    char currentPlayer;
    bool gameOver;
};

int main() {
    Game ticTacToe;
    ticTacToe.play();

    return 0;
}
