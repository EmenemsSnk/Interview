/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author micha
 */
public class FileLoader {
    private final Path path;
    private final FileExtensionFinder finder;
    private FileExtensionFinderResult result;

    public FileLoader(Path path, String extension) {
        this.path = path;
        this.finder = new FileExtensionFinder(extension);
    }
    
    public void walkThroughPath() throws IOException{
        Files.walkFileTree(path, finder);
        result = new FileExtensionFinderResult(finder.getDirectories()); 
    }

    public FileExtensionFinderResult getFinderResult() {
        if(result == null){
            throw new NullPointerException("Result is null, didn't walked through path");
        }
        return result;
    }
}
