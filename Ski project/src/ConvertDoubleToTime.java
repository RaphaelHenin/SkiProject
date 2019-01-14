import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertDoubleToTime {

	/**
	 * 
	 * @param time = the double value that we wanna convert to time format
	 * @param temp = Table string that will store the time
	 * @param i
	 * @return temp, the string table that have stored the time through all recursive
	 */
	static String[] convertDoubleToTime(Double time, String[] temp, int i) {
		String stringTime = time.toString();
		String[] numbers = stringTime.split("\\.");
		int firstUnit = Integer.parseInt(numbers[0]);
		if (time > 60) {
			time = time / 60;
			String hourUnit = (Double.toString(time)).split("\\.")[0];
			temp[i] = hourUnit;
			Double newValue = time - Double.parseDouble(hourUnit);
			if (newValue == 0)
				return temp;
			BigDecimal bd = new BigDecimal(newValue * 60);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.doubleValue(), temp, i);
		} else {
			temp[i + 1] = Integer.toString(firstUnit);
			Double newValue = time - firstUnit;
			if (newValue == 0)
				return temp;
			BigDecimal bd = new BigDecimal(newValue * 60);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.doubleValue(), temp, i + 1);
		}
		return temp;
	}
	
	
	/**
	 * @param String table that stored the time calculated in the convertDoubelToTime function
	 * @return "HH:MM:SS" time format (if HH, MM or SS are not null)
	 */
	static String displayTime(String[] time){
		String timeToDisplay = "";
		//If hour, display it
		if(time[0]!=null){
			timeToDisplay += time[0]+":";
		}
		//If minute, display it
		if(time[1]!=null){
			//If second, display it ELSE just dipaly minute whithout ":" after.
			if(time[2]!=null){
				timeToDisplay += time[1]+":"+time[2];
			} else{
				timeToDisplay += time[1];
			}
		}
		
		//milliseconds are not displayed
		return timeToDisplay;
	}
}
