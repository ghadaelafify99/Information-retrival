package IR2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.HashSet;

class InvertedIndex002 {


    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */


    /**
     *
     * @author ehab
     */
    /*
     * InvertedIndex - Given a set of text files, implement a program to create an
     * inverted index. Also create a user interface to do a search using that inverted
     * index which returns a list of files that contain the query term / terms.
     * The search index can be in memory.
     *

     */


    public static void main(String args[]) throws IOException {
        Index2 index = new Index2();
        String phrase = "";

        index.buildIndex(new String[]{
                "C:\\Users\\DELL\\Desktop\\docs\\100.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\101.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\102.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\103.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\104.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\105.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\106.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\107.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\108.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\109.txt"
                , "C:\\Users\\DELL\\Desktop\\docs\\522.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\524.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\525.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\526.txt",
                "C:\\Users\\DELL\\Desktop\\docs\527.txt",
                "C:\\Users\\DELL\\Desktop\\docs\300.txt",
                "C:\\Users\\DELL\\Desktop\\docs\\302.txt",
                "C:\\Users\\DELL\\Desktop\\docs\500.txt",
                "C:\\Users\\DELL\\Desktop\\docs\501.txt",
                "C:\\Users\\DELL\\Desktop\\docs\502.txt",
                "C:\\Users\\DELL\\Desktop\\docs\503.txt",
                "C:\\Users\\DELL\\Desktop\\docs\504.txt",
                "C:\\Users\\DELL\\Desktop\\docs\505.txt",
                "C:\\Users\\DELL\\Desktop\\docs\506.txt",
                "C:\\Users\\DELL\\Desktop\\docs\507.txt",
                "C:\\Users\\DELL\\Desktop\\docs\508.txt",
                "C:\\Users\\DELL\\Desktop\\docs\509.txt",
                "C:\\Users\\DELL\\Desktop\\docs\511.txt",
                "C:\\Users\\DELL\\Desktop\\docs\510.txt",
                "C:\\Users\\DELL\\Desktop\\docs\512.txt",
                "C:\\Users\\DELL\\Desktop\\docs\513.txt",
                "C:\\Users\\DELL\\Desktop\\docs\514.txt",
                "C:\\Users\\DELL\\Desktop\\docs\515.txt",
                "C:\\Users\\DELL\\Desktop\\docs\516.txt",
                "C:\\Users\\DELL\\Desktop\\docs\517.txt",
                "C:\\Users\\DELL\\Desktop\\docs\518.txt",
                "C:\\Users\\DELL\\Desktop\\docs\519.txt",
                "C:\\Users\\DELL\\Desktop\\docs\520.txt",
                "C:\\Users\\DELL\\Desktop\\docs\521.txt",
                "C:\\Users\\DELL\\Desktop\\docs\523.txt"

        });
        int number=0;
        String check="";
        boolean item = true;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("1- find_01 ");
            System.out.println("2- find_02 ");
            System.out.println("3- find_03 ");
            System.out.println("4- find_04 ");
            System.out.println("5- find_05 ");
            System.out.println("6- mix");
            System.out.println("7- mix_2");
            System.out.println("8- mix_3");
            System.out.println("9-jaccard");
            System.out.println("10- Exit");

            System.out.println("Enter number of operation : ");
            number = in.nextInt();
            in.nextLine();
            switch (number) {
                case 1:
                    System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.find_01(phrase));
                    break;
                case 2:
                    System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.find_02(phrase));
                    break;
                case 3:
                    System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.find_03(phrase));
                    break;
                case 4:
                    System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.find_04(phrase));
                    break;
                case 5:
                    System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.find_05(phrase));
                    break;
                case 6:
                    //System.out.println("Enter Phrase : ");
                    phrase = "carried and cleaning or ready";
                    System.out.println(index.mix_1(phrase));
                    break;
                case 7:
                    //System.out.println("Enter Phrase : ");
                    //phrase = "carried and not ready";
                    phrase = "ahmed and not tdd";
                    System.out.println(index.mix_2(phrase));
                    break;
                case 8:
                    //System.out.println("Enter Phrase : ");
                    phrase = "carried or not ready";
                    System.out.println(index.mix_3(phrase));
                    break;
                case 9:
                    //System.out.println("Enter Phrase : ");
                    phrase = in.nextLine();
                    System.out.println(index.jaccardSimilarity(phrase));
                    break;
                default:
                    item = false;
                    break;
            }
        }while (item == true);
    }




    //=====================================================================
    static class DictEntry2 {

        public int doc_freq = 0; // number of documents that contain the term
        public int term_freq = 0; //number of times the term is mentioned in the collection
        public HashSet<Integer> postingList;

        DictEntry2() {
            postingList = new HashSet<Integer>();
        }
    }

    //=====================================================================
    static class Index2 {


        //--------------------------------------------
        Map<Integer, String> sources;  // store the doc_id and the file name
        HashMap<String, DictEntry2> index; // THe inverted index
        //--------------------------------------------

        Index2() {
            sources = new HashMap<Integer, String>();
            index = new HashMap<String, DictEntry2>();
        }

        //---------------------------------------------
        public void printDictionary() {
            Iterator it = index.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                DictEntry2 dd = (DictEntry2) pair.getValue();
                HashSet<Integer> hset = dd.postingList;// (HashSet<Integer>) pair.getValue();
                System.out.print("** [" + pair.getKey() + "," + dd.doc_freq + "] <" + dd.term_freq + "> =--> ");
                Iterator<Integer> it2 = hset.iterator();
                while (it2.hasNext()) {
                    System.out.print(it2.next() + ", ");
                }
                System.out.println("");
                //it.remove(); // avoids a ConcurrentModificationException
            }
            System.out.println("------------------------------------------------------");
            System.out.println("* Number of terms = " + index.size());
        }

        //-----------------------------------------------
        public void buildIndex(String[] files) {
            int i = 0;
            for (String fileName : files) {
                try ( BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                    sources.put(i, fileName);
                    String ln;
                    while ((ln = file.readLine()) != null) {
                        String[] words = ln.split("\\W+");
                        for (String word : words) {
                            word = word.toLowerCase();
                            // check to see if the word is not in the dictionary
                            if (!index.containsKey(word)) {
                                index.put(word, new DictEntry2());
                            }
                            // add document id to the posting list
                            if (!index.get(word).postingList.contains(i)) {
                                index.get(word).doc_freq += 1; //set doc freq to the number of doc that contain the term
                                index.get(word).postingList.add(i); // add the posting to the posting:ist
                            }
                            //set the term_fteq in the collection
                            index.get(word).term_freq += 1;
                        }
                    }
                    printDictionary();
                } catch (IOException e) {
                    System.out.println("File " + fileName + " not found. Skip it");
                }
                i++;
            }
        }

        //--------------------------------------------------------------------------
        // query inverted index
        // takes a string of terms as an argument
        public String find(String phrase) {
            String[] words = phrase.split("\\W+");
            HashSet<Integer> res = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            for (String word : words) {
                res.retainAll(index.get(word).postingList);
            }
            if (res.size() == 0) {
                System.out.println("Not found");
                return "";
            }
            String result = "Found in: \n";
            for (int num : res) {
                result += "\t" + sources.get(num) + "\n";
            }
            return result;
        }

        //----------------------------------------------------------------------------
        HashSet<Integer> intersect(HashSet<Integer> pL1, HashSet<Integer> pL2) {
            HashSet<Integer> answer = new HashSet<>();
            List<Integer> p1 = new ArrayList<>(pL1);
            List<Integer> p2 = new ArrayList<>(pL2);
            List<Integer> answer1 = new ArrayList<>(answer);
            int p1_index = 0, p2_index = 0;
            int increment = 0;
            while (p1_index < p1.size() && p2_index < p2.size()) {
                if (p1.get(p1_index) == p2.get(p2_index)) {
                    answer1.add(p1.get(p1_index));
                    ++p1_index;
                    ++p2_index;
                } else if (p1.get(p1_index) < p2.get(p2_index)) {
                    ++p1_index;
                } else {
                    ++p2_index;
                }
                ++increment;
            }
            HashSet<Integer> answer2 = new HashSet<Integer>(answer1);
            System.out.println("AND result " + answer1 );
            return answer2;
        }
        //=======================================================================
        HashSet<Integer> or(HashSet<Integer> pL1, HashSet<Integer> pL2) {
            HashSet<Integer> answer = new HashSet<Integer>();
            Iterator<Integer> itP1 = pL1.iterator();
            Iterator<Integer> itP2 = pL2.iterator();
            int docId1 = 0;
            int docId2 = 0;
            pL1.addAll(pL2);
           // System.out.println("OR result " + pL1);
            return pL1;}


        //-----------------------------------------------------------------------
        HashSet<Integer> Not(HashSet<Integer> pL1) {
            int docId = 0;
            HashSet<Integer> answer = new HashSet<Integer>();
            var res = pL1.toArray();
            for (int i = 0; i < sources.size(); i++) {
                for (var num : res) {
                    if (sources.get(num).equals(sources.get(i))) {
                        i++;
                    }
                }
                if (sources.get(i) != null)
                    answer.add(i);
            }

            return answer;

        }
        //-----------------------------------------------------------------------

        public String find_01(String phrase) { // 2 term phrase  2 postingsLists and
            String result = "";
            String[] words = phrase.split("\\W+");
            // 1- get first posting list
            HashSet<Integer> pL1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            // 2- get second posting list
            HashSet<Integer> pL2 = new HashSet<Integer>(index.get(words[1].toLowerCase()).postingList);
            // 3- apply the algorithm "use intersect function"
            HashSet<Integer> answer = intersect(pL1, pL2);
            //   System.out.println("Found in: ");
            for (int num : answer) {
                result += "\t" + sources.get(num) + "\n";
            }
            return result;
        }
//-----------------------------------------------------------------------

        public String find_02(String phrase) { // 2 terms or
            String result = "";
            String[] words = phrase.split("\\W+");
            // 1- get first posting list
            HashSet<Integer> pL1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            // 2- get second posting list
            HashSet<Integer> pL2 = new HashSet<Integer>(index.get(words[1].toLowerCase()).postingList);
            // 3- apply the algorithm
            HashSet<Integer> answer = or(pL1, pL2);
            System.out.println("Found in: ");
            for (int num : answer) {
                //System.out.println("\t" + sources.get(num));
                result += "\t" + sources.get(num) + "\n";
            }
            return result;

        }
        //-----------------------------------------------------------------------

        public HashSet<Integer> find_03(String phrase) { // not
            String result = "";
            String[] words = phrase.split("\\W+");
            HashSet<Integer> pL1 = new HashSet<Integer>(index.get(words[1].toLowerCase()).postingList);

            HashSet<Integer> answer = Not(pL1);
            System.out.println(" Not Found in: ");
            for (int num : answer) {
                //System.out.println("\t" + sources.get(num));
                result += "\t" + sources.get(num) + "\n";
            }
            return answer;

        }

        //-----------------------------------------------------------------------
        public String find_04(String phrase) { //  lists of 3 terms or
            String result = "";
            String[] words = phrase.split("\\W+");
            // 1- get first posting list
            HashSet<Integer> pL1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            // 2- get second posting list
            HashSet<Integer> pL2 = new HashSet<Integer>(index.get(words[1].toLowerCase()).postingList);
            // 2- get third posting list
            HashSet<Integer> pL3 = new HashSet<Integer>(index.get(words[2].toLowerCase()).postingList);
            // 3- apply the algorithm "use intersect function"
            HashSet<Integer> answer = or(pL1, pL2);
            HashSet<Integer> answer2 = or(answer, pL3);
            //System.out.println("Found in: ");
            for (int num : answer2) {
                result += "\t" + sources.get(num) + "\n";
            }
            return result;

        }
        //-----------------------------------------------------------------------
        public String find_05(String phrase) { //  3 terms and
            String result = "";
            String[] words = phrase.split("\\W+");
            // 1- get first posting list
            HashSet<Integer> pL1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            // 2- get second posting list
            HashSet<Integer> pL2 = new HashSet<Integer>(index.get(words[1].toLowerCase()).postingList);
            // 2- get third posting list
            HashSet<Integer> pL3 = new HashSet<Integer>(index.get(words[2].toLowerCase()).postingList);
            // 3- apply the algorithm "use intersect function"
            HashSet<Integer> answer = intersect(pL1, pL2);
            HashSet<Integer> answer2 = intersect(answer, pL3);
            //System.out.println("Found in: ");
            for (int num : answer2) {
                result += "\t" + sources.get(num) + "\n";
            }
            return result;

        }

        public String mix_1(String phrase) //term and term or term
        {
            String result = "";
            String[] words = phrase.split("\\W+");
            HashSet<Integer> word1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            HashSet<Integer> word2 = new HashSet<Integer>(index.get(words[2].toLowerCase()).postingList);
            HashSet<Integer> word3= new HashSet<Integer>(index.get(words[4].toLowerCase()).postingList);


            HashSet<Integer> answer = intersect(word1, word2);
            HashSet<Integer> answer2 = or(answer, word3);


            result += "Found in: ";
            for (int num : answer) {

                result += "\n" + sources.get(num) + "\n";
            }
            return result;
        }
        public String mix_2(String phrase) //term and not term
        {
            String result = "";
            String[] words = phrase.split("\\W+");
            HashSet<Integer> word1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            HashSet<Integer> word2 = new HashSet<Integer>(index.get(words[3].toLowerCase()).postingList);


            HashSet<Integer> answer = new HashSet<Integer>();
            answer=intersect((word1),Not(word2));
            result += "Found in: ";
            for (int num : answer) {

                result += "\n" + sources.get(num) + "\n";
            }
            return result;
        }
        public String mix_3(String phrase)//term or not term
        {
            String result = "";
            String[] words = phrase.split("\\W+");
            HashSet<Integer> word1 = new HashSet<Integer>(index.get(words[0].toLowerCase()).postingList);
            HashSet<Integer> word2 = new HashSet<Integer>(index.get(words[3].toLowerCase()).postingList);


            HashSet<Integer> answer = new HashSet<Integer>();
            answer=or((word1),Not(word2));
            result += "Found in: ";
            for (int num : answer) {

                result += "\n" + sources.get(num) + "\n";
            }
            return result;
        }
        public double calculateSimilarity(String  oneContent,  String otherContent)
        {
            double numerator   = intersect(oneContent,otherContet);
            double denominator = or(oneContent,otherContet);

            return denominator.size() > 0 ?
                    (double)numerator.size()/(double)denominator.size() : 0;
        }

    }

}


