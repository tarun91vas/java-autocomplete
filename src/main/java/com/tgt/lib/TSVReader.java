package com.tgt.lib;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;

public class TSVReader {

    private String tsvFile = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources/word_search.tsv";

    private CSVReader reader = null;

    public HashMap<String, Long> buildWordFrequencyMap() {
        HashMap<String, Long> freqMap = new HashMap<>();
        try {
            reader = new CSVReader(new FileReader(tsvFile), '\t');
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[1] != null) {
                    freqMap.put(line[0], Long.valueOf(line[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freqMap;
    }

    public static void main(String[] args) {
        TSVReader tsv = new TSVReader();
        HashMap<String, Long> map = tsv.buildWordFrequencyMap();
        System.out.println(map.size());
    }
}
