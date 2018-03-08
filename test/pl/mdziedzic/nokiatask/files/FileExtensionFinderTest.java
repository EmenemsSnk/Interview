/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import pl.mdziedzic.nokiatask.common.Directory;

/**
 *
 * @author micha
 */
public class FileExtensionFinderTest {
    
    private final String xml = "xml";
    private final BasicFileAttributes fileAttributes = null;
    
    private FileExtensionFinder testInstance;
    private Path sourcePath;
    
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    @Before
    public void setUp() {
        testInstance = new FileExtensionFinder(xml);
    }
    
    @Test
    public void shouldThrowNullPointerExceptionIfFileIsNullWhenFileFailed() throws Exception {
        //given
        sourcePath = null;
        
        // when - then
        try{
            testInstance.visitFileFailed(sourcePath, new IOException());
        }catch(Exception exception){
            assertTrue(exception instanceof NullPointerException);
        }
    }
    
     @Test
    public void shouldThrowIOExceptionWhenFileFailed() throws Exception {
        //given
        sourcePath = Paths.get("xyz");
        
        // when - then
        try{
            testInstance.visitFileFailed(sourcePath, new IOException());
        }catch(Exception exception){
            assertTrue(exception instanceof IOException);
        }
    }

    @Test
    public void pathShouldExists() {
        //given
        sourcePath = Paths.get(temporaryFolder.getRoot().getAbsolutePath());
        
        //when
        boolean result = FileExtensionFinder.isPathExists(sourcePath);
        
        //then
        assertEquals(true, result);
    }
    
    @Test
    public void shouldAddCurrentDirectoryWhenPreVisitCorrectPath() throws Exception {
        //given
        sourcePath = Paths.get(temporaryFolder.getRoot().getAbsolutePath());

        //when
        testInstance.preVisitDirectory(sourcePath, fileAttributes);
        int elementsCount = testInstance.getDirectories().size();
        
        //then
        assertEquals(1, elementsCount);
    }
    
    @Test (expected = Exception.class)
    public void shouldThrowExceptionWhenPreVisitDirectoryIncorrectPath() throws Exception {
        //given
        sourcePath = null;

        //when
        testInstance.preVisitDirectory(sourcePath, fileAttributes);
        int elementsCount = testInstance.getDirectories().size();
        
        //then
        assertEquals(1, elementsCount);
    }

    @Test
    public void shouldAddXMLFileToListToProperDirectory() throws Exception {
        //given
        List<Directory> directories = testInstance.getDirectories();
        File tempFile = temporaryFolder.newFile("file.xml");
        sourcePath = Paths.get(tempFile.getAbsolutePath());
        
        directories.add(new Directory("someDirectory", "someFolder"));
        directories.add(new Directory(temporaryFolder.getRoot().getAbsolutePath(), "someFolder"));
        
        //when
        testInstance.visitFile(sourcePath, fileAttributes);
        
        //then
        Directory directoryWithoutXML = directories.get(0);
        Directory directoryContainingXML = directories.get(1);
        
        int xmlFilesQuantity = directoryWithoutXML.getFileNames().size();
        assertEquals(0, xmlFilesQuantity);
        
        xmlFilesQuantity = directoryContainingXML.getFileNames().size();
        assertEquals(1, xmlFilesQuantity);
    }
    
    @Test
    public void shouldNOTAddFileWithDifferentExtension() throws Exception {
        //given
        List<Directory> directories = testInstance.getDirectories();
        File tempFile = temporaryFolder.newFile("filexml.txt");
        sourcePath = Paths.get(tempFile.getAbsolutePath());
        
        directories.add(new Directory(temporaryFolder.getRoot().getAbsolutePath(), "someFolder"));
        
        //when
        testInstance.visitFile(sourcePath, fileAttributes);
        
        //then
        Directory currentDirectory = directories.get(0);
        
        int xmlFilesQuantity = currentDirectory.getFileNames().size();
        assertEquals(0, xmlFilesQuantity);
    }
}
