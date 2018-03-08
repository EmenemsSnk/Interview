/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.files;

import pl.mdziedzic.nokiatask.common.Directory;
import java.util.List;

/**
 *
 * @author micha
 */
public class FileExtensionFinderResult {
    private final List<Directory> directories;
    private String totalCountResultDescription;
    private String resultDesription;

    FileExtensionFinderResult(List<Directory> resultList) {
        directories = resultList;
        buildDescription();
    }
        
    private void buildDescription() {
        StringBuilder resultMessage = new StringBuilder();
        int totalCount = 0;
        
        for(Directory directory : directories){
            int filesInDirectory = directory.getFileNames().size();
            resultMessage
                    .append(directory.getCurrentFolder())
                    .append(" " + filesInDirectory)
                    .append(",");
            totalCount+= filesInDirectory;
        }
        
        resultMessage.deleteCharAt(resultMessage.length() - 1);
        totalCountResultDescription = "All: " + totalCount;
        resultDesription = resultMessage.toString();
    }

    public void show() {
        System.out.println(resultDesription);
        System.out.println(totalCountResultDescription);
    }
}
