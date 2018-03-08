/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.files;

import pl.mdziedzic.nokiatask.common.Directory;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author micha
 */
public class FileExtensionFinder implements FileVisitor<Path> {
    private final String searchedExtension;
    private final List<Directory> directories = new ArrayList<>();
    
    public FileExtensionFinder(String searchedExtension) {
        this.searchedExtension = searchedExtension;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {     
        String absolutePath = dir.toString();
        String currentFolder = dir.getFileName().toString(); 
        
        Directory directory = new Directory(absolutePath, currentFolder);
        directories.add(directory);
             
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        
        if(isDesiredExtensionFile(fileName)){
            String currentFolderAbsolutePath = file.getParent().toString();
            
            for(Directory directory : directories){
                if(directory.getAbsolutePath().equals(currentFolderAbsolutePath)){
                    directory.addFile(fileName);
                }
            }
        }
        
        return FileVisitResult.CONTINUE;
    }
    
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        Objects.requireNonNull(file);
        throw exc;
    }  
    
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    public static boolean isPathExists(Path path){
        return Files.exists(path);
    }
    
    private boolean isDesiredExtensionFile(String file) {
        String extension = getExtensionFromFile(file);
        return extension.equals(searchedExtension);
    }

    private String getExtensionFromFile(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
    
    public List<Directory> getDirectories() {
        return directories;
    }  
           
}
