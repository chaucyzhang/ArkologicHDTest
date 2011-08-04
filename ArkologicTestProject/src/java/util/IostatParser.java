/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class IostatParser {

    private final File fFile;
    private static String fileUrl;

    public IostatParser(String aFileName) throws FileNotFoundException {
        fFile = new File(aFileName);
    }
    
    public List<Hashtable<String,String>> getFileJsonFormat() throws FileNotFoundException{
        trimAllContect();
        List<Hashtable<String, String>> resultList = new ArrayList<Hashtable<String, String>>();
        resultList = processLineByLine(fileUrl + "_copy");
        return resultList;
    
    }

    public final List<Hashtable<String, String>> processLineByLine(String url) throws FileNotFoundException {
        //FileReader is used, not File, since File is not Closeable
        Scanner scanner = new Scanner(new FileReader(url));
        String keys[] = new String[10];
        String values[] = new String[10];
        int iter = 0;
        List<Hashtable<String, String>> li = new ArrayList<Hashtable<String, String>>();
        try {
            scanner.nextLine();
            //first use a Scanner to get each line
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                System.arraycopy(processKeys(line), 0, keys, 0, processKeys(line).length);
            }
            while (scanner.hasNextLine()) {
                Hashtable<String, String> map = new Hashtable<String, String>();
                String secline = scanner.nextLine();
                for (int i = 0; i < processLines(secline).length; i++) {
                    values[i] = processLines(secline)[i];
                    int col = i % 10;
                    switch (col) {
                        case 0:
                            map.put(keys[0], values[i]);
                        case 1:
                            map.put(keys[1], values[i]);
                        case 2:
                            map.put(keys[2], values[i]);
                        case 3:
                            map.put(keys[3], values[i]);
                        case 4:
                            map.put(keys[4], values[i]);
                        case 5:
                            map.put(keys[5], values[i]);
                        case 6:
                            map.put(keys[6], values[i]);
                        case 7:
                            map.put(keys[7], values[i]);
                        case 8:
                            map.put(keys[8], values[i]);
                        case 9:
                            map.put(keys[9], values[i]);
                    }
                }
                li.add(map);
                iter++;
            }
            return li;
        } finally {
            scanner.close();
        }
    }

    protected String[] processKeys(String aLine) {
        //use a second Scanner to parse the content of each line 
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(" ");
        String keys[] = new String[10];
        int i = 0;
        while (scanner.hasNext() && i < 10) {
            String key = scanner.next();
            keys[i] = key.trim();
            if(keys[i].equals("r/s")){
            keys[i]="rps";
            }
            else if(keys[i].equals("w/s")){
            keys[i]="wps";
            }
            else if(keys[i].equals("kr/s")){
            keys[i]="krps";
            }
            else if(keys[i].equals("kw/s")){
            keys[i]="kwps";
            }
            else if(keys[i].equals("%w")){
            keys[i]="w";
            }
            else if(keys[i].equals("%b")){
            keys[i]="b";
            }
            i++;
        }
        return keys;
        // log("Empty or invalid line. Unable to process.");
        //no need to call scanner.close(), since the source is a String
    }

    protected String[] processLines(String aLine) {
        //use a second Scanner to parse the content of each line 
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(" ");
        String values[] = new String[10];
        int i = 0;
        while (scanner.hasNext() && i < 10) {
            String value = scanner.next();
            values[i] = value.trim();
            i++;
        }
        return values;

    }

    protected void trimAllContect() throws FileNotFoundException {
        Scanner scn = new Scanner(new FileReader(fFile));
        try {
            FileWriter fw = new FileWriter(fileUrl + "_copy");
            PrintWriter fout = new PrintWriter(fw);

            while (scn.hasNextLine()) {
                String s = scn.nextLine();
                String lineStr = trimLine(s);

                fout.println(lineStr);



            }
            fout.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scn.close();
        }
    }

    protected String trimLine(String aLine) {
        Scanner scanner = new Scanner(aLine);
        StringBuffer sbf = new StringBuffer();
        sbf.append(scanner.next());
        while (scanner.hasNext()) {
            sbf.append(" " + scanner.next());

        }

        return sbf.toString();

    }

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText) {
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
