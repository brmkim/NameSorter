/*
 *  ExtensionFileFilter.java
 */

package namesort;
import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 * 
 * @author pbladek
 */
public class ExtensionFileFilter extends FileFilter 
{
  private String description;
  private String extensions[];

  /**
   * constructor 
   * @param description describes usage
   * @param extension an array of acceptable file extensions
   */
  public ExtensionFileFilter(String description, String extension) {
    this(description, new String[] { extension }); 
  }

  /**
   * 
   * @param description  describes usage
   * @param extensions an array of acceptable file extensions
   */
  public ExtensionFileFilter(String description, String extensions[]) {
    if (description == null) {
      this.description = extensions[0];
    } else {
      this.description = description;
    }
    this.extensions = (String[]) extensions.clone();
    toLower(this.extensions);
  }

  /**
   * sets each string in the array to lower case
   * @param array an array of acceptable file extensions
   */
  private void toLower(String array[]) {
    for (int i = 0, n = array.length; i < n; i++) {
      array[i] = array[i].toLowerCase();
    }
  }
  /**
   * accessor for description
   * @return the description
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Tests whether or not the specified abstract pathname should be included
   *     in a pathname list.
   * @param file
   * @return 
   */
  @Override
  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    } else {
      String path = file.getAbsolutePath().toLowerCase();
      for (int i = 0, n = extensions.length; i < n; i++) {
        String extension = extensions[i];
        if ((path.endsWith(extension) &&
                (path.charAt(path.length() - extension.length() - 1)) == '.'))
        {
          return true;
        }
      }
    }
    return false;
  }
}

