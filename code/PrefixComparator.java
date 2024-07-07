import java.util.Comparator;

/**
 * Factor pattern for obtaining PrefixComparator objects
 * without calling new. Users simply use
 *
 *     Comparator<Term> comp = PrefixComparator.getComparator(size)
 *
 * @author owen astrachan
 * @author Amy Liu for compare() implementation
 */
public class    PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
        return new PrefixComparator(prefix);
    }


    @Override
    /**
     * Use at most myPrefixSize characters from each of v and w
     * to return a value comparing v and w by words. Comparisons
     * should be made based on the first myPrefixSize chars in v and w.
     * @return < 0 if v < w, == 0 if v == w, and > 0 if v > w
     */
    public int compare(Term v, Term w) {
        // change this to use myPrefixSize as specified,
        // replacing line below with code
        String string_v = v.getWord();
        String string_w = w.getWord();
        
        // looking for min(v, w, myPrefixSize)
        int first_check = Math.min(string_v.length(), string_w.length());
        int min = Math.min(first_check, myPrefixSize);

        // return 0 if strings are the same
        if (string_v.equals(string_w)) return 0;

        // loop through min number of letters for v and w
        for (int i = 0; i < min; i++){
            if (string_v.charAt(i) != string_w.charAt(i)){
                // remember: compare(a, b) > 0 means b comes before a
                // lexicographic order: letters at start of alphabet have smaller vals
                if (string_v.charAt(i) > string_w.charAt(i)){
                    return 1;
                } 

                // remember: compare(a, b) < 0 means a comes before b
                if (string_v.charAt(i) < string_w.charAt(i)){
                    return -1;
                }
            }
        }
        
        // if chars equal up to min point: shorter string comes before longer string in sorting
        if (min < myPrefixSize){
            if (string_v.length() == min){
                return -1;
            } 
            if (string_w.length() == min) {
                return 1;
            }
        }
        return 0;
    }
}
