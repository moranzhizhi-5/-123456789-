public class Theater {
    private String name;
    private int rows;
    private int seatsPerRow;
    private boolean[][] seating; // false 表示空闲，true 表示已售

    public Theater(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seating = new boolean[rows][seatsPerRow];
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public boolean[][] getSeating() {
        return seating;
    }

    public boolean isSeatAvailable(int row, int seat) {
        return !seating[row][seat];
    }

    public void bookSeat(int row, int seat) {
        seating[row][seat] = true;
    }

    public void releaseSeat(int row, int seat) {
        seating[row][seat] = false;
    }
}