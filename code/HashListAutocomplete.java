import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements Autocompletor by utilizing a HashList.
 * @author Amy Liu
 */

public class HashListAutocomplete implements Autocompletor {

    private static final int MAX_PREFIX = 10;
    private Map<String, ArrayList<Term>> myMap;
    private int mySize;


    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
        // error catching for HashList mapping and alignment b/w terms and weights
        if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
        initialize(terms, weights);
    }

    @Override
    public List<Term> topMatches(String prefix, int k) {
        // TODO Auto-generated method stub
        if (prefix.length() > MAX_PREFIX){
            prefix = prefix.substring(0, MAX_PREFIX);
        }

        // if prefix not in dict - return empty list
        if (! myMap.containsKey(prefix)){
            return List.of();
        }
        
        List<Term> all = myMap.get(prefix);
        List<Term> list = all.subList(0, Math.min(k, all.size()));
        
        return list;
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        // TODO Auto-generated method stub
        // initialize hashmap mapping MAX_PREFIX substring as key, Term objects w/ prefix as value
        HashMap<String,ArrayList<Term>> dict = new HashMap<>();

        // loop thru all terms
        for (int j = 0; j < terms.length; j++){
            String word = terms[j];
            for (int i = 0; i <= Math.min(word.length(), MAX_PREFIX); i++){
                // why start from 0? -> includes empty string as a substring
                String subs = word.substring(0,i);
                
                // initializing term
                Term t = new Term(word, weights[j]);

                // update mySize for term stored
                mySize += BYTES_PER_DOUBLE + BYTES_PER_CHAR * t.getWord().length();	
                
                // updating size for key already in dictionary (only need to update for term vals)
                if (!dict.containsKey(subs)){
                    mySize += BYTES_PER_CHAR * subs.length();
                }
                
                // initialize dict key for new subs key
                dict.putIfAbsent(subs, new ArrayList<>());
                
                // add term to subs key's respective ArrayList value
                dict.get(subs).add(t);
                
            }
        }

        // loop through HashMap keys
        for (String key: dict.keySet()){
            // loops through each corresponding ArrayList value to sort ArrayList by weight
            Collections.sort(dict.get(key), Comparator.comparing(Term::getWeight).reversed());
        }
        myMap = dict;
    }

    @Override
    public int sizeInBytes() {
        // TODO Auto-generated method stub
        // see initialize for size updating (took care of already)
        return mySize;
    }  
}

