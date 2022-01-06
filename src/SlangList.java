import java.io.*;
import java.util.*;

public class SlangList {
    private TreeMap<String, List<String>> tree = new TreeMap<>();
    private int treeSize;
    String orgFileName = "orgSlang.txt";
    String editableFileName = "editableSlang.txt";
    String historyFileName = "historySlang.txt";
    private SlangList() {
        try {
            String currentAddress = new java.io.File(".").getCanonicalPath();
            System.out.println("current: " + currentAddress);
            editableFileName = currentAddress + "\\" + editableFileName;
            orgFileName = currentAddress + "\\" + orgFileName;
            historyFileName = currentAddress + "\\" + historyFileName;
            readFile(editableFileName);
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
        String line = null;
        treeSize = 0;
        int i = 0;
        String tokens[];
        String key, definitionString;
        while ((line = br.readLine())!= null) {
            if(line.contains("`") == true) {
                List<String> definitionList = new ArrayList<String>();
                tokens = line.split("`");
                System.out.println("Line " + i + ": " + line);
                i++;
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

    public ArrayList<String> getSlangKeyList(){
        ArrayList<String> arr = new ArrayList<>();
        Set<Map.Entry<String, List<String>> > entrySet = tree.entrySet();
        // Convert entrySet to Array using toArray method
        Map.Entry<String, List<String>>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        for(int i = 0; i < tree.size(); i++){
            arr.add(entryArray[i].getKey());
        }

        return arr;
    }

    public ArrayList<String> getSlangDefinition(String key){
        List<String> listMeaning = map.get(key);
        if (listMeaning == null)
            return null;
        int size = listMeaning.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = key;
            s[i][2] = listMeaning.get(i);
        }
        return s;

    }
}
