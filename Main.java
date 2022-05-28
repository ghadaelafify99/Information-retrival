package IR2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static double similarity(String documentPath, String query) throws FileNotFoundException {
        String[] q = query.split(" ");
        Scanner s = new Scanner(new File(documentPath));
        ArrayList<String> d = new ArrayList<String>();
        while (s.hasNext()){
            d.add(s.next());
        }
        HashSet<String> queryWordsSet=new HashSet<>();
        HashSet<String> docWordsSet=new HashSet<>();
        s.close();
        queryWordsSet.addAll(Arrays.asList(q));
        docWordsSet.addAll(d);
        HashSet<String> intersect=new HashSet<>(queryWordsSet);
        intersect.retainAll(docWordsSet);

        HashSet<String> union=new HashSet<>(queryWordsSet);
        union.addAll(docWordsSet);
        return  (double)intersect.size()/(double)union.size();
    }

    public static void main(String[] args) {
	// write your code here
        Main main= new Main();
        String[] q=new String[6];
        q[0]="C:\\Users\\DELL\\Desktop\\docs\\1.txt";
        q[1]="C:\\Users\\DELL\\Desktop\\docs\\2.txt";
        q[2]="C:\\Users\\DELL\\Desktop\\docs\\102.txt";
        q[3]="C:\\Users\\DELL\\Desktop\\docs\\103.txt";
        q[4]="C:\\Users\\DELL\\Desktop\\docs\\104.txt";
        q[5]="C:\\Users\\DELL\\Desktop\\docs\\105.txt";
        try {
            double[] list = new double[6];
            //to store all scores returned from jaccard
            for (int i = 0; i < q.length; i++)
            {
            list[i] = similarity(q[i], "idea of March");
                }
            //sort
            Arrays.sort( list);
            int count=5;
            for (int z=0;z<q.length;z++)
            {
                double c=similarity( q[z],"idea of March");;
            //double c=similarity( q[z],"Department of Information systems");
                if(list[count]==c) //match score to its suitable  document
                {
                    System.out.println(  main.similarity( q[z],"idea of March") +
                            "  "+ q[z]);
                    z=-1;
                    count--;
                }

            }
        }
        catch (Exception e){}



    }
    }

