/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask.files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author micha
 */
public class FileLoaderTest {
    private final String xml = "xml";
    private FileLoader testInstance;
    private Path sourcePath;
    
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    @Before
    public void setUp() {
        sourcePath = Paths.get(temporaryFolder.getRoot().getAbsolutePath());
        testInstance = new FileLoader(sourcePath, xml);
    }

    @Test
    public void shouldSetResultAfterWalkingThroughPath() throws IOException{
        testInstance.walkThroughPath();
        assertNotNull(testInstance.getFinderResult());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenResultIsNull() {
        testInstance.getFinderResult();
    }
    
    @Test
    public void shouldReturnResultWhenIsNOTnull() throws IOException{
        testInstance.walkThroughPath();
        assertNotNull(testInstance.getFinderResult());
    }
}
