package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Model {
    String content = new String();
    String counter = new String();
    String howmany;


    // take a file and made  list of his content
    public void read(String filepath, ArrayList<String> list) throws FileNotFoundException {

        File file = new File(filepath);
        content = "";
        list.clear();

        Scanner contentsFile = new Scanner(file);

        while (contentsFile.hasNext()) {

            list.add(contentsFile.next());

        }
        for (int i = 0; i < list.size(); i++) {
            content = content + " " + list.get(i);
        }

        contentsFile.close();
    }

    //  Make a two list one give us word, second number of reference in document
    public void statista(ArrayList<String> list) {
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<String> formatlist = new ArrayList<>();

        // making list without usless character
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() < 2) {
                continue;
            }
            formatlist.add(list.get(i).replaceAll("[^a-zA-Z0-9-а-я-А-Я]", ""));
        }

        // calculate words
        for (int i = 0; i < formatlist.size(); i++) {
            int k = 0;
            for (int j = 0; j < formatlist.size(); j++) {

                if (formatlist.get(i).equals(formatlist.get(j)) && k == 0) {
                    k++;
                    count.add(i, 1);
                } else if (formatlist.get(i).equals(formatlist.get(j)) && k != 0) {
                    count.set(i, ++k);
                    formatlist.remove(j);
                    j--;
                } else continue;

            }

        }

        indexsista(formatlist, count);
    }

    //  format list that the more reference word get the higher place in list this get
    public void indexsista(ArrayList<String> list, ArrayList<Integer> count) {
        int safe;
        String save;
        counter = "";
        for (int i = 0; i < count.size(); i++) {
            for (int j = i; j < count.size(); j++) {
                if (count.get(i) > count.get(j)) continue;
                else if (count.get(i) < count.get(j)) {
                    safe = count.get(i);
                    count.set(i, count.get(j));
                    count.set(j, safe);
                    save = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, save);
                } else continue;
            }
        }


        for (int i = 0; i < count.size(); i++) {
            counter = counter + (i + 1) + ". " + list.get(i) + " - ";
            counter = counter + count.get(i) + " - " + howmany + " | ";
            if (i % 3 == 0) {
                counter = counter + "\n";
            }
        }


    }
}
