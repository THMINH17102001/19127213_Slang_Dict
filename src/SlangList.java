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
        String line = null;
        treeSize = 0;
        int i = 0;
        String tokens[];
        String key, definitionString;
        while ((line = br.readLine())!= null) {
            List<String> definitionList = new ArrayList<String>();
            tokens = line.split("`");
            key = tokens[0];
            definitionString =tokens[1];
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
        br.close();
    }
}
