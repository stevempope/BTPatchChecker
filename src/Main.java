/* CSV-Reader tool that identifies which routers are suitable for patching.
 * 
 * A suitable router must meet the following conditions:
 * 
 * -OS level 12 or higher
 * -Not already patched
 * -No routers with a shared IP
 * -No routers with a shared Hostname
 *
 * @author Stephen Pope - stevempope@gmail.com
 * */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {

		try {
			//Grab the CSV file and parse it into its composite lines.
			Path thePath = Paths.get(args[0]);
			List<String> lineArray;
			ArrayList<String> routers = new ArrayList<String>();
			lineArray = Files.readAllLines(thePath);

			for(String line: lineArray){
				routers.add(line);
			}

			//Compile list of routers that do not match the rules in top comment.
			int [] unpatchable =  new int [routers.size()];
			for(int i = 1; i < routers.size(); i++){
				for (int j = 0; j < routers.size(); j++){
					//Split each line into its Comma Separated Values for comparison.
					String[] compare = routers.get(i).toLowerCase().split(",",5);
					String[] compareTo = routers.get(j).toLowerCase().split(",", 5);
					//Test for invalid data in CSV.
					if(compare[0].contains("example.com") == false){
						unpatchable[i] = i;
						break;
					}
					else if (compare[1].length() != 7){
						unpatchable[i] = i;
						break;
					}
					//Record routers place in the file if they do not meet the rules.
					//Duplicate Hostnames
					else if (compare[0].equals(compareTo[0])){
						if(i != j){
							unpatchable[i] = i;
							break;
						}
					}
					//Duplicate IP Addresses
					else if (compare[1].equals(compareTo[1])){
						if(i != j){
							unpatchable[i] = i;
							break;
						}
					}
					//Already patched
					else if (compare[2].equals("yes")){
						unpatchable[i] = i;
						break;
					}
					//OS Version is numerical and greater than 12
					else if (compare[3].length() > 0){
						char[] validateOs = compare[3].toCharArray();
						for (int m = 0; m < validateOs.length; m++){
							if((int) validateOs[m] > 57 && ((int) validateOs[m] < 48)){
								unpatchable[i] = i;
								break;
							}
							if (Double.parseDouble(compare[3]) < 12){
								unpatchable[i] = i;
								break;
							}
						}
					}
				}	
			}
			//Print routers not in the unpatchable list.
			for (int k = 1 ; k < routers.size(); k++){
				if (unpatchable[k] == 0){
					String[] temp = routers.get(k).split(",", 5);
					System.out.printf("%s (%s), OS Version %s ", temp[0], temp[1], temp[3]);
					if(temp[4].trim().isEmpty() == false){
						System.out.printf("[%s] \n", temp[4]);
					}
					else{
						System.out.println("");
					}
				}
			}
		} 
		//Print any errors
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}