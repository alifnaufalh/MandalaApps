package tebak_aksara;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays a Hangman game board to the screen for interaction with the player.
 * @author Jeff A.
 */
public class GameBoard extends JFrame 
{
    /**
     * The width of the GameBoard.
     */
    private final int WIDTH;
    
    /**
     * The height of the GameBoard.
     */
    private final int HEIGHT;
    
    /**
     * The maximum number of guesses before game over.
     */
    private final int MAX_INCORRECT;
    
    /**
     * The maximum password length permitted.
     */
    private final int MAX_PASSWORD_LENGTH;
    
    /**
     * The directory of the images of the hangman.
     */
    private String HANGMAN_IMAGE_DIRECTORY;
    
    /**
     * The type of the images of the hangman.
     */
    private String HANGMAN_IMAGE_TYPE;
    
    /**
     * The base (common) name of the images of the hangman (e.g. "hangman" for
     * "hangman_0.png, hangman_1.png, ...")
     */
    private String HANGMAN_IMAGE_BASE_NAME;
    
    /**
     * The directory of the images of the letters.
     */
    private final String LETTER_IMAGE_DIRECTORY;
    
    /**
     * The type of the images of the letters.
     */
    private final String LETTER_IMAGE_TYPE;
    
    /**
     * The letter rack containing a the letters to be guessed.
     */
    private LetterRack gameRack;
    
    /**
     * The hangman image placeholder.
     */
    
    private int index;
    private Hangman gameHangman;
    private Hangman gameHangman2;
    private Hangman gameHangman3;
    private Hangman gameHangman4;
    private Hangman gameHangman5;
    private Hangman gameHangman6;
    private Hangman gameHangman7;
    private Hangman gameHangman8;
    private Hangman gameHangman9;
    private Hangman gameHangman10;
    
    /**
     * The number of incorrect guesses.
     */
    private int numIncorrect;
    
    /**
     * Display the password hidden as *'s, revealing each letter as it is
     * guessed
     */
    private JLabel correct;
    
    /**
     * Displays the number of incorrect guesses.
     */
    private JLabel incorrect;
    
    /**
     * The password to be guessed by the player.
     */
    private String password;
    
    /**
     * StringBuilder used to hide the password, revealing letters as they are
     * guessed by the player.
     */
    private StringBuilder passwordHidden;
	
	Random rand=new Random();
    
    /**
     * The default constructor.
     */
    public GameBoard()
    {
        WIDTH = 400;
        HEIGHT = 400;
        MAX_INCORRECT = 6;
        MAX_PASSWORD_LENGTH = 10;
        
        // The default directory for the sample images is images/ and the 
        //     default image type is .png; ensure this directory is
        //     created in the project folder if the sample images are used.
        HANGMAN_IMAGE_DIRECTORY = LETTER_IMAGE_DIRECTORY = "/img/tbk_askara/";
        HANGMAN_IMAGE_TYPE = LETTER_IMAGE_TYPE = ".png";
        HANGMAN_IMAGE_BASE_NAME = "hangman";
        
        setTitle("Game Aksara Sunda");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initialize();
    }
    
    /**
     * Initializes all elements of the GameBoard that must be refreshed upon
     * the start of a new game.
     */
    private void initialize()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"HAYAM"};
        password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
	private void initialize2()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"LAUK"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman2();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
        private void initialize3()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"MANUK"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman3();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
        private void initialize4()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"ORAY"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman4();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    
    private void initialize5()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"SAPI"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman5();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    
    private void initialize6()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"BADAK"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman6();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    
    private void initialize7()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"GAJAH"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman7();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    private void initialize8()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"LANCAH"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman8();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    private void initialize9()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"KUDA"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman9();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    private void initialize10()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Kata: ");
        incorrect = new JLabel("Jumlah Kesalahan: " + numIncorrect + "Sisa: " + (6-numIncorrect));
		
		String[] name = {"MERI"};
                password = name [index];
        
		
		passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addHangman10();
        
        // set board slightly above middle of screen to prevent dialogs
        //     from blocking images
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }
    
    /**
     * Adds the correct and incorrect labels to the top of the GameBoard
     */
    private void addTextPanel()
    {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,2));
        textPanel.add(correct);
        textPanel.add(incorrect);
        // use BorderLayout to set the components of the gameboard in
        //     "visually agreeable" locations
        add(textPanel, BorderLayout.NORTH);
    }
    
    /**
     * Adds the LetterRack to the bottom of the GameBoard and attaches
     * the LetterTile TileListeners to the LetterTiles.
     */
    private void addLetterRack()
    {
        gameRack = new LetterRack(password, 
                LETTER_IMAGE_DIRECTORY, 
                LETTER_IMAGE_TYPE);
        gameRack.attachListeners(new TileListener());
        add(gameRack, BorderLayout.SOUTH);
    }
    
    /**
     * Adds a panel that contains the hangman images to the middle of the
     * GameBoard.
     */
    private void addHangman()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman = new Hangman(HANGMAN_IMAGE_BASE_NAME,
                HANGMAN_IMAGE_DIRECTORY,
                HANGMAN_IMAGE_TYPE);
        hangmanPanel.add(gameHangman);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    private void addHangman2()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman2 = new Hangman("hangman2",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman2);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    private void addHangman3()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman3 = new Hangman("hangman3",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman3);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    private void addHangman4()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman4 = new Hangman("hangman4",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman4);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman5()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman5 = new Hangman("hangman5",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman5);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman6()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman6 = new Hangman("hangman6",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman6);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman7()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman7 = new Hangman("hangman7",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman7);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman8()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman8 = new Hangman("hangman8",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman8);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman9()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman9 = new Hangman("hangman9",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman9);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void addHangman10()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman10 = new Hangman("hangman10",
                "/img/tbk_askara/",
                ".png");
        
        hangmanPanel.add(gameHangman10);
        add(hangmanPanel, BorderLayout.CENTER);
    }
    
    private void getPassword()
    {
		 String[] options = {"Let's Play", "Quit"};
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Enter Password to Be Guessed: ");
        JTextField passwordText = new JTextField(MAX_PASSWORD_LENGTH);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        int confirm = -1;
        
        while (password.isEmpty())
        {
            confirm = JOptionPane.showOptionDialog(null, 
                    passwordPanel, 
                    "Enter Password", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
    if (confirm == 0)
            {
                password = passwordText.getText();
                
                // ensure password is only made up of letters and is less than
                //     the maximum password length
                // NOTE: matches() (and its use of regular expressions) is used
                //     for simplicity, not speed
                if (!password.matches("[a-zA-Z]+") || 
                    password.length() > MAX_PASSWORD_LENGTH)
                {
                    JOptionPane.showMessageDialog(null, 
                            "Password must be less than 10 characters and " +
                            "only contain letters A-Z.", 
                            "Invalid Password", 
                            JOptionPane.ERROR_MESSAGE);
                    password = ""; // empty password if error occurs
                }
            }
                    
            else if (confirm == 1)
                System.exit(0);
        }
        
        // use a regular expression to replace all characters with *'s and
        //     hide the password when it is displayed
        passwordHidden.append(password.replaceAll(".", "*"));
        correct.setText(correct.getText() + passwordHidden.toString());
    }
    
    /**
     * Prompts the user for a new game when one game ends.
     */
    private void newGameDialog()
    {
        int randLevel = rand.nextInt(10)+1;
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                "Katanya adalah : " + password +
                "\nMau main Lagi?",
                "Main Lagi?",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION){
            if (randLevel == 1){
                initialize();
            }
            else if (randLevel == 2){
                initialize2();
            }
            else if (randLevel == 3){
                initialize3();
            }
            else if (randLevel == 4){
                initialize4();
            }
            else if (randLevel == 5){
                initialize5();
            }
            else if (randLevel == 6){
                initialize6();
            }
            else if (randLevel == 7){
                initialize7();
            }
            else if (randLevel == 8){
                initialize8();
            }
            else if (randLevel == 9){
                initialize9();
            }
            else if (randLevel == 10){
                initialize10();
            }
         } // re-initialize the GameBoard
        else
            System.exit(0);
    }
    
    /**
     * A custom MouseListener for the LetterTiles that detects when the user
     * "guesses" (clicks on) a LetterTile and updates the game accordingly.
     */
    private class TileListener implements MouseListener 
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {
            Object source = e.getSource();
            if(source instanceof LetterTile)
            {
                char c = ' ';
                int index = 0;
                boolean updated = false;
                
                // cast the source of the click to a LetterTile object
                LetterTile tilePressed = (LetterTile) source;
                c = tilePressed.guess();
                
                // reveal each instance of the character if it appears in the
                //     the password
                while ((index = password.toLowerCase().indexOf(c, index)) != -1)
                {
                    passwordHidden.setCharAt(index, password.charAt(index));
                    index++;
                    updated = true;
                }
                
                // if the guess was correct, update the GameBoard and check
                //     for a win
                if (updated)
                {
                    correct.setText("Kata: " + passwordHidden.toString());
                    
                    if (passwordHidden.toString().equals(password))
                    {
                        gameRack.removeListeners();
                        gameHangman.winImage();
                        newGameDialog();
                    }
                }
                
                // otherwise, add an incorrect guess and check for a loss
                else
                {
                    incorrect.setText("Kesalahan: " + ++numIncorrect + "Sisa: " + (6-numIncorrect));
                    
                    if (numIncorrect >= MAX_INCORRECT)
                    {
                        gameHangman.loseImage();
                        gameRack.removeListeners();
                        newGameDialog();
                    }
                    
                    
                }
            }
        }
        
        // These methods must be implemented in every MouseListener
        //     implementation, but since they are not used in this application, 
        //     they contain no code
        
        @Override
        public void mouseClicked(MouseEvent e) {}  

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}
        
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}