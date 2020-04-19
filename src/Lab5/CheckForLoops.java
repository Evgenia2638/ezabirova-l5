package Lab5;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class CheckForLoops {
    static String[] Fl = new String[1000];
    static int i = 0;


    public static void add(String file) throws IOException {
        Fl[i++] = Arrays.toString(new String[]{file});
    }


public static void compare(PriorityQueue<Product> pq) throws IOException {
    Object[] products = pq.toArray();
    int l= 0;
    for(int p = 0; p < Fl.length; p++){
        for(int p2 = 0; p2 < i; p2++) {
            if (p2 == p) continue;
            if (!(Fl[p2] == null)&&(!(Fl[p]==null)) ){
                if ((Fl[p2]).charAt(0) == (Fl[p]).charAt(0)) {
                   l=10;
                    break;
                }
            }
        }
    }
    if (l==10) {
        System.err.println("К сожалению я не могу выполнить данную команду");
    }else {
        ForExecuteScript.execute_script_for_file(pq, new File(Fl[i]));
    }
}

 public static void clean(){
    Fl =null;
    i=0;
 }
}
