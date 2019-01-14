import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ConvertDoubleToTime {

	/**
	 * Recursive method that will convert a double to an Arraylist that contains
	 * hour (first index), minutes (second index), second (third) and
	 * millisecond (fourth)
	 * 
	 * @param time
	 *            = the double value that we wanna convert to time format
	 * @param temp
	 *            = ArrayList of string that will store the time
	 * @return temp, the ArrayList that have stored the time through all
	 *         recursive steps
	 */
	static ArrayList<String> convertDoubleToTime(Double time, ArrayList<String> temp) {
		// Convert the double value to string
		String stringTime = time.toString();
		// Split the double previously converted to string
		String[] numbers = stringTime.split("\\.");
		// Store the number before the "." from the double value
		int firstUnit = Integer.parseInt(numbers[0]);
		Double newValue = 0.0;
		// If the number before the point is more than 60, it means that we have
		// hours.
		if (time >= 60) {
			time = time / 60;
			String hourUnit = (Double.toString(time)).split("\\.")[0];
			temp.add(hourUnit);
			newValue = time - Double.parseDouble(hourUnit);
		} else {
			// If the number before the point is equal 0, it means that we have
			// only second
			if (firstUnit == 0) {
				temp.add(0, null);
				temp.add(1, null);
			} else {
				// If the number before the point is less than 60 and not equal
				// 0, we have no hours but have minutes
				if (temp.size() < 1)
					temp.add(0, null);
				temp.add(Integer.toString(firstUnit));
				newValue = time - firstUnit;
			}
		}
		// The final condition
		if (newValue == 0) {
			// If there is millisecond
			if (temp.size() == 4)
				// Round the second to the nearest unit
				if (firstUnit > 30) {
					int plus = Integer.parseInt(temp.get(2)) + 1;
					temp.set(2, Integer.toString(plus));
					temp.set(3, null);
				}
			if(temp.size()==1){
				temp.add(null);
				temp.add(null);
			}
			if(temp.size()==2){
				temp.add(null);
			}
			return temp;
		}
		// Round big decimal to two digit after the point (ex : 0.63333333333 =>
		// 0.63)
		BigDecimal bd = new BigDecimal(newValue * 60);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		temp = convertDoubleToTime(bd.doubleValue(), temp);
		return temp;
	}

	/**
	 * Function that will display the data in the ArrayList returned by the convertDoubleToTime to Time Format.
	 * @param String
	 *            table that stored the time calculated in the
	 *            convertDoubelToTime function
	 * @return "HH:MM:SS" time format
	 */
	static String displayTime(ArrayList<String> time) {
		String timeToDisplay = "";
		// Go through hour, minute and second (not millisecond at index 3 of the
		// arraylist)
		for (int i = 0; i < 3; i++)
			// IF the current value is null we will display "00"
			if (time.get(i) == null)
				timeToDisplay += "00:";
			else {
				// IF the value has one digit, a "0" is added before the digit
				// (ex : if "1" => "01")
				if (time.get(i).length() == 1)
					time.set(i, "0" + time.get(i));
				timeToDisplay += time.get(i) + ":";
			}
		// Remove the last ":"
		timeToDisplay = timeToDisplay.substring(0, 8);
		// milliseconds are not displayed
		return timeToDisplay;
	}

	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.out.println(displayTime(convertDoubleToTime(50.00, new ArrayList<String>())));
	}

}
