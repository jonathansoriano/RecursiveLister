import javax.swing.JFileChooser;
import java.io.File;

public class DirectoryTest {//This Class is just to toss ideas around on how and when to use a recursive method.Issue in this example is that it
                            //Prints the name of the file twice if the file is directory.
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File[] files = selectedFile.listFiles(); //listFiles() Returns an array of abstract pathnames denoting the
                                                // files in the directory denoted by this abstract pathname.


            if (selectedFile.isDirectory()) {
                System.out.println("The selected file is a directory.");
                for (File file : files){//Going through the array of files (line 13)
                    if (file.isDirectory()){// if while we are going through the array of files and find more elements that are directories too..
                        File[] files2 = file.listFiles();//We are going to make that file to turn into a list.
                        System.out.println(file.getName() + " is another directory. Here are it's files: ");
                        for (File moreFiles: files2){
                            System.out.println(moreFiles.getName());
                        }
                    }
                    System.out.println(file.getName());//getName() doesn't include the full path name of the file!
                }
            } else {//if file isn't a directory, then just print out the file isn't a directory
                System.out.println("The selected file is not a directory.");
            }
        }else {
            System.out.println("No directory selected");
        }


    }
    /*
    //Original Implementation of Recursive List Files
   private void listFileRecursively(File selectedFile) {
        File[] fileArray = selectedFile.listFiles();

        for (File file : fileArray){// Going through fileArray
            results.append(file.getName() + "\n");
            if (file.isDirectory()){
                listFileRecursively(file);
            }

        }
        displayFileTA.setText(results.toString());

    }
     */
}
