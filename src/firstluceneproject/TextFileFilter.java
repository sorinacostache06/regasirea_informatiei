/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstluceneproject;

import java.io.File;
import java.io.FileFilter;


/**
 *
 * @author Gogosica
 */
public class TextFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.getName().toLowerCase().endsWith(".txt") || 
                pathname.getName().toLowerCase().endsWith(".word") ||
                pathname.getName().toLowerCase().endsWith(".rtf") ||
                pathname.getName().toLowerCase().endsWith(".odt")) {
            return true;
        } else return false;
   }
}
