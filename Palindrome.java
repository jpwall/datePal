// Jesse Walling
// February 10th, 2020

/**
 * datePal - A simple program that checks for palindrome dates over a specified range.
 */

import java.util.*;

public class Palindrome {
    private static final Map<Integer, Integer> DATES;
    static {
	DATES = new HashMap<>();
	DATES.put(1, 31);
	DATES.put(2, 28);
	DATES.put(3, 31);
	DATES.put(4, 30);
	DATES.put(5, 31);
	DATES.put(6, 30);
	DATES.put(7, 31);
	DATES.put(8, 31);
	DATES.put(9, 30);
	DATES.put(10, 31);
	DATES.put(11, 30);
	DATES.put(12, 31);
    }
    
    private ArrayList<String> palindromes;

    /**
     * Takes two arguments as int values, begin and end. These MUST be four digits long,
     * i.e. 1000 to 9999. These dates are inclusive. 2020 to 2021 will include both 
     * 02/02/2020 and 12/02/2021.
     * These are the year values that the program will search through
     * following the mm/dd/yyyy format. The data is stored internally within this object 
     * and populates the internal data structure with the dates that are palindromes.
     */
    public Palindrome(int begin, int end) {
	// Set initial ArrayList capacity to 350 to account for matches from 1000 to 9999
	palindromes = new ArrayList<>(350);
	for (int i = begin; i <= end; i++) {
	    String month = getMonth(i);
	    String day = getDay(i);
	    if (isValidDay(month, day, i)) {
		palindromes.add(month + "/" + day + "/" + i + "  -  " + month + day + i);
	    }
	}
    }

    /**
     * Gets the corresponding month to a given year. For the year 2021, this would be
     * December since 21 flipped is 12. This method performs the flipping for checking
     * a palindrome day. Returns a String value. It takes one int parameter that
     * represents the given year.
     */
    private String getMonth(int i) {
	String year = i + "";
	String two = year.substring(2, 3);
	String one = year.substring(3);
	return one + two;
    }

    /**
     * Gets the corresponding day to a given year. For the year 2020, this would be the
     * second day of the month (02) since it is 20 flipped. This method performs the
     * flipping for checking a palindrome day. Returns a String value. It takes one int
     * parameter that represents the given year.
     */
    private String getDay(int i) {
	String year = i + "";
	String two = year.substring(0, 1);
	String one = year.substring(1, 2);
	return one + two;
    }

    /**
     * While going through all the possible years, it uses the prior methods to determine
     * whether or not the date created by swapping places is valid. Returns a boolean
     * value. It takes three arguments - The month as a String from getMonth, the day as a
     * String from getDay, and the year to be checked as an int.
     */
    private boolean isValidDay(String month, String day, int year) {
	int monthNum = Integer.parseInt(month);
	int dayNum = Integer.parseInt(day);
	if (monthNum > 0 && monthNum <= 12) {
	    int max = DATES.get(monthNum);
	    // Check if February AND leap year
	    if (monthNum == 2 && leapYear(year)) {
		max++;
	    }
	    // Check to see if the day of month is valid
	    if (dayNum <= max) {
		return true;
	    }
	    return false;
	}
	return false;
    }

    /**
     * Checks whether or not a given year is a leap year. This is important for finding
     * palindrome dates. This method does so by checking whether or not the int value
     * argument is divisible by four and not divisible by 100. Returns a boolean value.
     */
    private boolean leapYear(int year) {
	boolean ret = false;
	if (year % 4 == 0 && year % 100 != 0) {
	    ret = true;
	}
	return ret;
    }

    /**
     * toString method that prints out the internal data structure of confirmed palindrome
     * dates. Returns a String and takes no parameters.
     * Outputs the palindrome dates with their written notation first then just the numbers
     * to make it easier to see the palindrome.
     */
    public String toString() {
	String ret = "";
	for (int i = 0; i < palindromes.size(); i++) {
	    ret += palindromes.get(i);
	    if (i != palindromes.size() - 1) {
		ret += "\n";
	    }
	}
	return ret;
    }
}
