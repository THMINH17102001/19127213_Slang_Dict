import java.io.*;
import java.util.*;

public class SlangList{
    private TreeMap<String, List<String>> tree = new TreeMap<>();
    private int treeSize;
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
        treeSize = 0;
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
                    String[] d = definitionString.split("|");
                    Collections.addAll(definitionList, d);
                    treeSize += d.length - 1;
                } else {
                    definitionList.add(definitionString);
                }
                tree.put(key, definitionList);
                treeSize++;
            }
        }
        br.close();
    }

    public void writeFile(String file) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("Slag`Meaning");
        for(Map.Entry<String, List<String>> entry: tree.entrySet())
        {
            bw.write(entry.getKey() +"`");
        }
        bw.close();
    }
    public void reset(){
        try {
            readFile(historyFileName);
        }catch(IOException e){
            e.printStackTrace();
        }
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

    public ArrayList<String> getSlangDefinition(String key){
        List<String> defList = tree.get(key);
        if (defList == null)
            return null;
        ArrayList<String> arr = new ArrayList<>(defList);
        return arr;
    }
}
