/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mdziedzic.nokiatask;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.mdziedzic.nokiatask.files.FileExtensionFinderTest;
import pl.mdziedzic.nokiatask.files.FileLoaderTest;

/**
 *
 * @author micha
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FileExtensionFinderTest.class, FileLoaderTest.class})
public class JUnitNokiaTaskSuite {

}
