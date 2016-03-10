package breakout.font;

import java.util.HashMap;
import java.util.Map;

public class HighriserFontBig implements Font {
	
	/* New big Font for the Highriser */
	private static final Map<Character, Letter> charMap = new HashMap<Character, Letter>();
    static {
    	charMap.put('w', new Letter(22, 9, 
			 "1110000001110000001110"
			+"0111000011111000011100"
			+"0111000011011100011100"
			+"0011100111011100011100"
			+"0011100111001110111000"
			+"0001110110001110111000"
			+"0001111110000111110000"
			+"0000111100000011110000"
			+"0000111100000011100000"));
    	/*charMap.put('a', new Letter(20, 9, 
   			 "000000011100000000"
    		+"000000111110000000"
   			+"000001110111000000"));*/
    }
     /**
      * Returns the Letter as Letter for the Highriser
      */
    public Letter getLetter(char c) {
    	Letter res = charMap.get(c);
    	
    	//if not found try lower case
    	if(res==null) { res = charMap.get(java.lang.Character.toLowerCase(c)); }
    	
    	return res;
    }
    
}
