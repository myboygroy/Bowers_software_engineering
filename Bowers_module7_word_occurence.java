package Word_occurences;
//Author Name: Joachim Bowers
//Date: 07/18/2021
//Program Name: Bowers_module7_word_occurrence
//Purpose: reads a file and counts the occurrence of each word. 
// Also lists the top 20 words.

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Takes html files linked by a hyperlink and counts the occurrences of each word within.
 * @author Joachim Bowers
 *
 */
public class Bowers_module7_word_occurence extends Application{
	//variables
	Text text;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Most used words");
		text = new Text(getTopTwenty());
		
		
		StackPane layout = new StackPane();
		layout.getChildren().add(text);
		
		Scene scene = new Scene(layout, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	/**
	 * Reads html file, converts it to a string, then counts the occurences of each word and stores it in a hashmap.
	 * @param filePath The url of the html file
	 * @return Contents of HTML file as a hashmap
	 */
	public Map<String, Integer> readFile(String filePath) {
		//the URL of the html file
		URL url = null;
		try {
			url = new URL(filePath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//This section scans in the HTML
		Scanner scannerHTML = null;
		try {
			scannerHTML = new Scanner(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this section converts the scanned in HTML to a string buffer
		StringBuffer uncountedList = new StringBuffer();
	      while(scannerHTML.hasNext()) {
	    	  uncountedList.append(scannerHTML.next() + " ");
	      }
	      
	      String resultString = uncountedList.toString();
	      //Removing the HTML tags
	      resultString = resultString.replaceAll("<[^>]*>", "");
	      resultString = resultString.replaceAll("[:.!?{}]", "");
	      resultString = resultString.toLowerCase();
	      
	      //method for counting words
	      //HashMap to store words and occurrences in.
	      Map<String, Integer> map = new HashMap<String, Integer>();
	      int i;
	      int j;
	      for (i = 0; i < resultString.length(); i++){
	            if (resultString.substring(i).startsWith(" ") || i == 0){

	                //here I search for the start of the sentence or " "
	                for (j = i + 1; j < resultString.length(); j++){

	                    if (resultString.substring(j).startsWith(" ") || j == resultString.length() - 1) {
	                        //here I search for the next " " or the end of the sentence
	                        String key = resultString.substring(i, j);
	                        Integer value = map.get(key);
	                        if (value == null) {
	                            map.put(key, 1);
	                        }
	                        else {
	                            map.put(key, ++value);
	                        }
	                        i = j;
	                        //i=j because the next search must be done from where we left

	                    }
	                }
	            }
	        }
	      Map<String, Integer> sortedMap = sortValues(map);
	      
		return sortedMap;
		
	}
	/**
	 * Sorts the list from least word occurrences to most word occurrences.
	 * @param map hashmap with String, Integer Key/Value pair
	 * @return Sorted Hashmap in order of smallest to largest Integer
	 */
	public HashMap sortValues(Map<String, Integer> map) {   
		List list = new LinkedList(map.entrySet());  
		//Custom Comparator  
		Collections.sort(list, new Comparator()   
		{  
			public int compare(Object o1, Object o2)   
			{  
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());  
			}  
		});  
		//copying the sorted list in HashMap to preserve the iteration order  
		HashMap sortedHashMap = new LinkedHashMap();  
		for (Iterator it = list.iterator(); it.hasNext();)   
		{  
			Map.Entry entry = (Map.Entry) it.next();  
			sortedHashMap.put(entry.getKey(), entry.getValue());  
		}   
		return sortedHashMap;  
	}    
	/**
	 * Gets the twenty words that occur the most in the HTML file and returns it as a formatted String.
	 * @return String formatted to list the top twenty words by occurence in the HTML file
	 */
	public String getTopTwenty(){
		String topTwenty = "";
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> sortedMap = sortValues(readFile("http://shakespeare.mit.edu/macbeth/full.html"));
		sortedMap.remove(" ");
		ArrayList<String> keyArray = new ArrayList<String>(sortedMap.keySet());
		ListIterator li = keyArray.listIterator(keyArray.size());
		int i = 1;
		while(li.hasPrevious() && i < 21) {
		//	if(li.previous) {
		//		String previous = li.previous();
		//	}
			sb.append("Number " + i + " most popular word  is " + "\"" + li.previous() +" \""+ " with " + sortedMap.get(li.previous()) + " occurrences" + "\n");
			i++;
		}

		topTwenty = sb.toString();
		return topTwenty;
	
	}
}
