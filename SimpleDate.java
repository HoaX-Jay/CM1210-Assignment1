/*
 * A simple class to represent a calendar date.
 * Uses a naive representation. This class does not verify that the month and
 * day values are valid.
 *
 * The month is represented by an integer between 1 (January) to 12 (December).
 * The day is represented by an integer, with 1 indicating the first day of
 * the month.
 *
 * This class should NOT be modified for Question 1 of the assessment.
 */
public class SimpleDate {
    private int year;
    private int month;
    private int day;


    public SimpleDate( int year, int month, int day ) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
