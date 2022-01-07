import com.sun.jdi.ArrayReference;

import java.io.*;
import java.util.*;
public class SlangList{
    private TreeMap<String, List<String>> tree = new TreeMap<>();
    String orgFileName = "orgSlang.txt";
    String editableFileName = "editableSlang.txt";
    String historyFileName = "historySlang.txt";
    ArrayList<String> searchHistory;
    private SlangList() {
        try {
            String currentAddress = new java.io.File(".").getCanonicalPath();
            System.out.println("current: " + currentAddress);
            editableFileName = currentAddress + "\\" + editableFileName;
            orgFileName = currentAddress + "\\" + orgFileName;
            historyFileName = currentAddress + "\\" + historyFileName;
            readFile(editableFileName);
            searchHistory = getHistory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SlangList list = new SlangList();
    public static SlangList getList() {
        return list;
    }

    public void readFile(String file) throws IOException{
        tree.clear();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String firstLine = br.readLine();
        String line;
        String tokens[];
        String key, definitionString;
        while ((line = br.readLine())!= null) {
            if(line.contains("`") == true) {
                List<String> definitionList = new ArrayList<String>();
                tokens = line.split("`");
                key = tokens[0];
                definitionString = tokens[1];
                if (tree.containsKey(key)) {
                    definitionList = tree.get(key);
                }
                if (definitionString.contains("|")) {
                    String[] d = definitionString.split("\\|");
                    Collections.addAll(definitionList, d);
                } else {
                    definitionList.add(definitionString);
                }
                tree.put(key, definitionList);
            }
        }
        br.close();
    }

    public void writeFile(String file) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("Slag`Meaning \n");
        Set<String> treeSet = tree.keySet();
        Object[] treeArray = treeSet.toArray();
        List<String> s;
        StringBuilder line;
        for(int i = 0; i < tree.size(); i++)
        {
            line = new StringBuilder();
            line.append(treeArray[i] + "`");
            List<String> temp = tree.get(treeArray[i]);
            for(int j = 0; j < temp.size(); j++){
                line.append(temp.get(j));
                if(j != temp.size() - 1)
                {
                    line.append("| ");
                }
            }
            line.append("\n");
            bw.write(line.toString());
        }
        bw.close();
    }

    public ArrayList<String> find(String key){
        List<String> defList = tree.get(key);
        if (defList == null)
            return null;
        ArrayList<String> arr = new ArrayList<>(defList);
        return arr;
    }

    public String[][] findByDefinition(String word) {
        ArrayList<String> keyList = getSlangKeyList();
        ArrayList<String> wordContainList = new ArrayList<>();
        ArrayList<String> wordContainDefList = new ArrayList<>();

        for (String key: keyList) {
            List<String> defList = tree.get(key);
            for (String x : defList) {
                if (x.toLowerCase().contains(word.toLowerCase())) {
                    wordContainList.add(key);
                    wordContainDefList.add(x);
                }
            }
        }
        int size = wordContainList.size();
        if(size <= 0)
            return null;
        String s[][] = new String[size][2];
        for (int i = 0; i < size; i++) {
            s[i][0] = wordContainList.get(i);
            s[i][1] = wordContainDefList.get(i);
        }
        return s;
    }


    public boolean contains(String key){
        if(tree.containsKey(key))
            return true;
        return false;
    }
    public void add(int type, String slang, String definition) {
        List<String> defList = new ArrayList<>();
        if(type == 1 || type == 3) {//add new or overwrite
            defList.add(definition);
        }
        else if(type == 2) {//duplicate
            defList = tree.get(slang);
            defList.add(definition);
        }
        tree.put(slang, defList);
        try {
            this.writeFile(editableFileName);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void edit(String key, String oldVal, String newVal){
        List<String> defList = tree.get(key);
        int index = defList.indexOf(oldVal);
        if(oldVal != newVal)
            defList.set(index, newVal);
        try {
            this.writeFile(editableFileName);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void delete(String key, String value) {
        List<String> defList = tree.get(key);
        int index = defList.indexOf(value);
        if (defList.size() == 1) {
            tree.remove(key);
        } else {
            defList.remove(index);
            tree.put(key, defList);
        }
        try {
            this.writeFile(editableFileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void reset(){
        try {
            readFile(orgFileName);
            this.writeFile(editableFileName);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public ArrayList<String> random(){
        int min = 0;
        int max = tree.size() - 1;
        ArrayList<String> arr = new ArrayList<>();
        int rand = getRandomNumber(min, max);
        int index = 0;
        String slang = null;
        for (String key : tree.keySet()) {
            if (index == rand) {
                arr = find(key);
                slang = key;
                break;
            }
            index++;
        }
        arr.add(0, slang);
        return arr;
    }
    public ArrayList<String> getHistory() throws IOException{
        ArrayList<String> arr = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(historyFileName));
        String line;
        while((line = br.readLine()) != null){
            arr.add(line);
        }
        br.close();
        return arr;
    }
    public void saveHistory(ArrayList<String> arr) throws IOException{
        if(arr == null)
            return;
        BufferedWriter bw = new BufferedWriter(new FileWriter(historyFileName));
        for(String str : arr){
            String x = str + "\n";
            bw.write(x);
        }
        bw.close();
    }

    public ArrayList<String> getSlangKeyList(){
        ArrayList<String> arr = new ArrayList<>();
        Set<Map.Entry<String, List<String>> > entrySet = tree.entrySet();

        Map.Entry<String, List<String>>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        for(int i = 0; i < tree.size(); i++){
            arr.add(entryArray[i].getKey());
        }
        return arr;
    }

    public String[] quiz(int type) {
        String[] s = new String[6];
        if (type == 1) {//slangWord --> definition;
            ArrayList<String> correctAnswerRandom = random();
            s[0] = correctAnswerRandom.get(0);
            s[1] = correctAnswerRandom.get(1);
            int i = 2;
            int correctAnswerPlace = getRandomNumber(2,5);
            s[correctAnswerPlace] = correctAnswerRandom.get(1);
            while(i <= 5){
                ArrayList<String> answersRandom = random();
                if(answersRandom.get(0) != correctAnswerRandom.get(0)){
                    if(i != correctAnswerPlace)
                        s[i] = answersRandom.get(1);
                    i++;
                }
            }
        }
        else {//definition --> slang
            ArrayList<String> correctAnswerRandom = random();
            s[0] = correctAnswerRandom.get(0);
            s[1] = correctAnswerRandom.get(1);
            int i = 2;
            int correctAnswerPlace = getRandomNumber(2,5);
            s[correctAnswerPlace] = correctAnswerRandom.get(0);
            while(i <= 5){
                ArrayList<String> answersRandom = random();
                if(answersRandom.get(0) != correctAnswerRandom.get(0)){
                    if(i != correctAnswerPlace)
                        s[i] = answersRandom.get(0);
                    i++;
                }
            }
        }
        return s;
    }

}
