public class Ticket {
    private String ticketId;
    private Screening screening;
    private int row;
    private int seat;

    public Ticket(Screening screening, int row, int seat) {
        this.ticketId = "T" + String.format("%08d", (int)(Math.random() * 100000000));
        this.screening = screening;
        this.row = row;
        this.seat = seat;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Screening getScreening() {
        return screening;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }
}