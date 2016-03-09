package breakout.font;

import java.util.HashMap;
import java.util.Map;

public class HighriserFont implements Font {
	
	private static final Map<Character, Letter> charMap = new HashMap<Character, Letter>();
    static {
    	charMap.put('a', new Letter(4, 5, "0000"+"0100"+"1010"+"1110"+"1010"));
    	charMap.put('A', new Letter(4, 5, "0100"+"1010"+"1110"+"1010"+"1010"));
    	charMap.put('b', new Letter(5, 5, "11100"+"10010"+"11100"+"10010"+"11100"));
    	charMap.put('c', new Letter(4, 5, "1110"+"1000"+"1000"+"1000"+"1110"));
        charMap.put('d', new Letter(4, 5, "0000"+"1100"+"1010"+"1010"+"1100"));
        charMap.put('D', new Letter(5, 5, "11100"+"10010"+"10010"+"10010"+"11100"));
        charMap.put('e', new Letter(3, 5, "110"+"100"+"110"+"100"+"110"));
        charMap.put('E', new Letter(4, 5, "1110"+"1000"+"1110"+"1000"+"1110"));
        charMap.put('g', new Letter(5, 5, "01110"+"10000"+"10110"+"10010"+"01100"));
        charMap.put('i', new Letter(2, 5, "00"+"10"+"10"+"10"+"10"));
        charMap.put('k', new Letter(5, 5, "10010"+"10100"+"11000"+"10100"+"10010"));
        charMap.put('m', new Letter(6, 5, "000000"+"100010"+"110110"+"101010"+"100010"));
        charMap.put('M', new Letter(6, 5, "100010"+"110110"+"101010"+"100010"+"100010"));
        charMap.put('n', new Letter(5, 5, "00000"+"10010"+"10010"+"11010"+"10110"));
        charMap.put('o', new Letter(5, 5, "00000"+"01100"+"10010"+"10010"+"01100"));
        charMap.put('r', new Letter(4, 5, "1100"+"1010"+"1100"+"1010"+"1010"));
        charMap.put('s', new Letter(4, 5, "0000"+"1110"+"1000"+"0010"+"1110"));
        charMap.put('S', new Letter(4, 5, "1110"+"1000"+"1110"+"0010"+"1110"));
        charMap.put('t', new Letter(4, 5, "0000"+"1110"+"0100"+"0100"+"0100"));
        charMap.put('T', new Letter(4, 5, "1110"+"0100"+"0100"+"0100"+"0100"));
        charMap.put('u', new Letter(5, 5, "10010"+"10010"+"10010"+"10010"+"11110"));
        charMap.put('v', new Letter(5, 5, "00000"+"10010"+"10010"+"10010"+"01100"));
        charMap.put('w', new Letter(6, 5, "000000"+"100010"+"100010"+"101010"+"010100"));
        charMap.put('W', new Letter(6, 5, "100010"+"100010"+"100010"+"101010"+"010100"));
        charMap.put('x', new Letter(4, 5, "0000"+"0000"+"1010"+"0100"+"1010"));
        charMap.put('X', new Letter(4, 5, "1010"+"1010"+"0100"+"1010"+"1010"));
        
        //numbers:
        charMap.put('0', new Letter(5, 5, "11110"+"10010"+"10010"+"10010"+"11110"));
        charMap.put('1', new Letter(2, 5, "10"+"10"+"10"+"10"+"10"));
        charMap.put('2', new Letter(5, 5, "11110"+"00010"+"11110"+"10000"+"11110"));
        charMap.put('3', new Letter(5, 5, "11100"+"00010"+"01100"+"00010"+"11100"));
        charMap.put('4', new Letter(5, 5, "10010"+"10010"+"11110"+"00010"+"00010"));
        charMap.put('5', new Letter(5, 5, "11110"+"10000"+"11110"+"00010"+"11110"));
        charMap.put('6', new Letter(5, 5, "11110"+"10000"+"11110"+"10010"+"11110"));
        charMap.put('7', new Letter(5, 5, "11110"+"00010"+"00010"+"00010"+"00010"));
        charMap.put('8', new Letter(5, 5, "11110"+"10010"+"11110"+"10010"+"11110"));
        charMap.put('9', new Letter(5, 5, "11110"+"10010"+"11110"+"00010"+"11110"));
        
        //other symbols
        charMap.put('!', new Letter(2, 5, "10"+"10"+"10"+"00"+"10"));
        charMap.put(':', new Letter(2, 5, "00"+"00"+"10"+"00"+"10"));
        charMap.put('.', new Letter(2, 5, "00"+"00"+"00"+"00"+"10"));
        charMap.put('-', new Letter(4, 5, "0000"+"0000"+"0110"+"0000"+"0000"));
    }
    
    public Letter getLetter(char c) {
    	Letter res = charMap.get(c);
    	
    	//if not found try lower case
    	if(res==null) { res = charMap.get(java.lang.Character.toLowerCase(c)); }
    	
    	return res;
    }
}
