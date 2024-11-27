import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveListerGUI extends JFrame {
    private JPanel resultsPanel;
    private JPanel buttonPanel;

    private JTextArea displayFileTA;

    private JButton startBtn;
    private JButton quitBtn;

    private JFileChooser fileChooser;
    private File selectedFile;

    private StringBuilder results = new StringBuilder();

    public RecursiveListerGUI(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        generateResultPanel();
        mainPanel.add(resultsPanel, BorderLayout.CENTER);

        generateButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);



        this.setTitle("Recursive Lister Application");
        this.setSize(450, 410);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void generateButtonPanel() {
        buttonPanel = new JPanel();
        //Start Button
        startBtn = new JButton("Start");
        startBtn.setFocusable(false);
        startBtn.setBackground(new Color(34,139,34));//Sets the background color of the button...
        startBtn.setOpaque(true);//Need this to show color on button
        startBtn.addActionListener((ActionEvent ae) -> {
            File homeDir = new File(System.getProperty("user.home"));
            fileChooser = new JFileChooser(homeDir);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// This setting for JFileChooser only allows
                                                                        //you to select directories only. Won't open a directory
                                                                        // and view its content like normal
            fileChooser.setAcceptAllFileFilterUsed(false);// This means that the user won't be able to select the
                                                        // "All Files" filter from the "Files of Type"
                                                        // dropdown menu in the file chooser dialog
            fileChooser.setDialogTitle("Select a Directory");//Sets the title of the JFileChoose Dialog Box!
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                selectedFile = fileChooser.getSelectedFile();//File Selected by the user has Global scope...
                System.out.println("This is a directory!");//Just testing whether or not the file is directory in console.
                //Create a Recursive method that list all the files within the chosen directory and any of its subdirectories
                listFileRecursively(selectedFile);
            }else {
                displayFileTA.setText("No File selected!");
                return;//Returns back to GUI
            }
        });
        //Quit Button
        quitBtn = new JButton("Quit");
        quitBtn.setFocusable(false);
        quitBtn.setBackground(new Color(220,20,60));//Sets the background color of the button...
        quitBtn.setOpaque(true);//Need this to show color on button
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);// Code to exit the application completely
        });

        buttonPanel.add(startBtn);
        buttonPanel.add(quitBtn);
    }

    private void listFileRecursively(File selectedFile) {
        if (selectedFile.isDirectory()){ //If the file is a Directory...
            results.append("(Directory) " + selectedFile.getName() + "\n"); //Append the "results" String builder with file name using getName().
                                                                            //getName() doesn't include the full path name of the file!
            File[] fileArray = selectedFile.listFiles(); //listFiles() Returns an array of abstract pathnames denoting the
                                // files in the directory denoted by this abstract pathname.
            for (File f: fileArray){ //Go through the all file elements in the array
                if (f.isDirectory()){// if a file found to be a directory...
                    listFileRecursively(f);//Use the same method to check if "f" (current file) is a directory...
                }else {
                    results.append("\t" + f.getName() + "\n");
                }
            }
        }
        else { //If file isn't a Directory...
            results.append(selectedFile.getName());//Add file name to the TextArea
        }
        displayFileTA.setText(results.toString());
    }

    private void generateResultPanel(){
        //Results Panel
        resultsPanel = new JPanel();
        //Label
        JLabel titleLabel = new JLabel("Recursive Lister ");

        displayFileTA = new JTextArea(18, 42);
        displayFileTA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayFileTA);
        //Added to panel in the order I want them to be.
        resultsPanel.add(titleLabel);// Label will be added first then the TextArea.
        resultsPanel.add(scrollPane);

    }


}
