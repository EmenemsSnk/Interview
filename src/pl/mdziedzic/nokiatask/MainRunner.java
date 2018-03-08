/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask;

import pl.mdziedzic.nokiatask.files.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 *
 * @author micha
 */
public class MainRunner {
    private static final String XML = "xml";    
    private static final Logger LOGGER = Logger.getLogger(MainRunner.class.getName());
    
    public static void main(String[] args){
        final Path sourcePath = Paths.get(args[0]); 
        
        if(FileExtensionFinder.isPathExists(sourcePath)){
            FileLoader facade = new FileLoader(sourcePath, XML);
            try {
                facade.walkThroughPath();
                FileExtensionFinderResult result = facade.getFinderResult();
                result.show();
            } catch (IOException ex) {
                LOGGER.severe("Interrupt with file: " + ex.getMessage());
            }
        }
        else{
            throw new IllegalArgumentException("Invalid input path, check input: " + args[0]);
        }
    }
}