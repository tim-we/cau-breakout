package breakout.font;

import java.util.HashMap;
import java.util.Map;

public class HighriserFontBig implements Font {
	
	private static final Map<Character, Letter> charMap = new HashMap<Character, Letter>();
    static {
    	//TODO: implement this
    }
    
    public Letter getLetter(char c) {
    	Letter res = charMap.get(c);
    	
    	//if not found try lower case
    	if(res==null) { res = charMap.get(java.lang.Character.toLowerCase(c)); }
    	
    	return res;
    }
    
}
