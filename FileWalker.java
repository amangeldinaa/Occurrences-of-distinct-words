
package wordmap;
import java.io.*;


public abstract class FileWalker
{

    public static boolean seemsOK( File f )
    {
        return f. exists( ) && f. canRead( ); 
    }

    public static boolean seemsOK( String filename )
    { 
        return seemsOK( new File( filename ));
    }

    public static Occurrences getOccurrences( String filename ) 
    throws FileNotFoundException, IOException
    {
        Occurrences occ = new Occurrences( ); 
        addOccurrences( new File( filename ), occ );
        return occ; 
    } 


    public static void addOccurrences( File file, Occurrences occ )
    throws FileNotFoundException, IOException {
        if(file.isDirectory()) {
            File[] contents = file.listFiles();
            for(File f : contents) {
                addOccurrences(f, occ);
            }
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            addOccurrences(reader, file.toString(), occ);
        }
    }


    public static
    void addOccurrences( BufferedReader source, String sourcename,
                          Occurrences occ )
    throws IOException {
        int ch;
        int curLine = 1, curCol = 0, startCol = 1;
        StringBuilder str = new StringBuilder();

        do {
            ch = source.read();

            if(!Syntax.isInWord((char)ch)) {
                if(str.length() > 0) {
                    if(seemsOK(sourcename)) {
                        occ.put(str.toString().toLowerCase(), sourcename, new Position(curLine, startCol));
                    }
                    str = new StringBuilder();
                }
                curCol++;
                startCol = curCol + 1;
                if(Syntax.isNewLine((char)ch)) {
                    curLine++;
                    curCol = 0;
                    startCol = 1;
                }
            } else {
                str.append((char)ch);
                curCol++;
            }
        } while(ch != -1);
        source.close();
    }
}


