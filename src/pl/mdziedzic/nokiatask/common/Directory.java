/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Directory{
    private final String absolutePath;
    private final String currentFolder;
    private List<String> fileNames = new ArrayList<>();

    public Directory(String absolutePath, String currentFolder) {
        this.absolutePath = absolutePath;
        this.currentFolder = currentFolder;
    }

    public void addFile(String fileName) {
        fileNames.add(fileName);
    }
    
    public String getAbsolutePath() {
        return absolutePath;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public String getPath() {
        return absolutePath;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }
}
