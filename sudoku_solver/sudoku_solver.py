class Sudoku:
    def __init__(self, puzzle):
        self.grid = [list(row) for row in puzzle]

    def display(self):
        for row in self.grid:
            print(" ".join(row))

    def is_valid_move(self, row, col, num):
        # Check if 'num' is not present in the same row or column
        if num in self.grid[row] or num in [self.grid[i][col] for i in range(9)]:
            return False

        # Check if 'num' is not present in the 3x3 subgrid
        start_row, start_col = 3 * (row // 3), 3 * (col // 3)
        for i in range(3):
            for j in range(3):
                if self.grid[start_row + i][start_col + j] == num:
                    return False

        return True

    def find_empty_location(self):
        for i in range(9):
            for j in range(9):
                if self.grid[i][j] == ' ':
                    return i, j
        return None

    def solve(self):
        empty_location = self.find_empty_location()

        # If there are no empty locations, the puzzle is solved
        if not empty_location:
            return True

        row, col = empty_location

        # Try placing a number from 1 to 9
        for num in map(str, range(1, 10)):
            if self.is_valid_move(row, col, num):
                self.grid[row][col] = num

                # Recursively solve the rest of the puzzle
                if self.solve():
                    return True

                # If the current configuration doesn't lead to a solution, backtrack
                self.grid[row][col] = ' '

        # No number from 1 to 9 can be placed, backtrack
        return False


# Example puzzle (0 or ' ' represents empty cells)
puzzle = [
    "53  7    ",
    "6  195   ",
    " 98    6 ",
    "8   6   3",
    "4  8 3  1",
    "7   2   6",
    " 6    28 ",
    "   419  5",
    "    8  79",
]

sudoku_solver = Sudoku(puzzle)
print("Sudoku Puzzle:")
sudoku_solver.display()

if sudoku_solver.solve():
    print("\nSolved Sudoku:")
    sudoku_solver.display()
else:
    print("\nNo solution exists.")
