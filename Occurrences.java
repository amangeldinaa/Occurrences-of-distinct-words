
package wordmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Occurrences  
{
    private Map< String, Map< String, Set< Position >>> occ; 
        // Maps words -> filename -> sets of their Positions in the file.
 
    public Occurrences( ) 
    {
        occ = new TreeMap<> ( ); 
    }

    public void
    put( String word, String filename, Position pos ) throws FileNotFoundException {

        String lowerCaseWord = word.toLowerCase();
        if(!occ.containsKey(lowerCaseWord)) {
            Map< String, Set< Position >> map = new TreeMap<>();
            occ.put(lowerCaseWord, map);
        }
        if(occ.get(lowerCaseWord).containsKey(filename)) {
            occ.get(lowerCaseWord).get(filename).add(pos);
        } else {
            Set<Position> set = new TreeSet<>();
            set.add(pos);
            occ.get(lowerCaseWord).put(filename, set);
        }
    }


    public int countWords( ) {
        return occ.size();
    }

    public int count( ) {
        int count = 0;
        for(Map.Entry<String, Map< String, Set< Position >>> pair : occ.entrySet()) {
            for(Map.Entry< String, Set< Position >> innerPair : pair.getValue().entrySet()) {
                count += innerPair.getValue().size();
            }
        }
        return count;
    }

    public int count( String word ) {
        if(!occ.containsKey(word)) {
            return 0;
        }

        int count = 0;
        for(Map.Entry< String, Set< Position >> pair : occ.get(word).entrySet()) {
           count += pair.getValue().size();
        }
        return count;
    }

    public int count( String word, String file ) {
        if(!occ.containsKey(word)) {
            return 0;
        }
        if(!occ.get(word).containsKey(file)) {
            return 0;
        }
        return occ.get(word).get(file).size();
    }
 
    public String toString( ) {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<String, Map< String, Set< Position >>> pair : occ.entrySet()) {
            str.append("word \"").append(pair.getKey()).append("\" occurs ").append(count(pair.getKey())).append(" times:\n");

            for(Map.Entry< String, Set< Position >> innerPair : pair.getValue().entrySet()) {
                str.append("   in file \"").append(innerPair.getKey()).append("\":\n");

                for(Position pos : innerPair.getValue()) {
                    str.append("      at ").append(pos.toString()).append("\n");
                }
            }
        }

        return str.toString();
    }
}


