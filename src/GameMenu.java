import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import tebak_aksara.GameBoard;

public class GameMenu extends JFrame implements ActionListener{

    JButton backButton = new JButton();
    JButton gameButton = new JButton();
    JButton gameButton1 = new JButton();
    JButton gameButton2 = new JButton();
    JButton gameButton3 = new JButton();
    JButton gameButton4 = new JButton();
    String BackButtons[] = {"img/eng/back_menugame.png","img/in/back_menugame.png","img/sun/back_menugame.png"};
    ImageIcon backIcon;
    BufferedImage image;
    String GameButtons[] = {"img/eng/btn_games1.png","img/in/btn_games1.png","img/sun/btn_games1.png"};
    ImageIcon gameIcon;
    String GameButtons1[] = {"img/eng/btn_games2.png","img/in/btn_games2.png","img/sun/btn_games2.png"};
    ImageIcon game1Icon;
    String GameButtons2[] = {"img/eng/btn_games3.png","img/in/btn_games3.png","img/sun/btn_games3.png"};
    ImageIcon game2Icon;
    String GameButtons3[] = {"img/eng/btn_games4.png","img/in/btn_games4.png","img/sun/btn_games4.png"};
    ImageIcon game3Icon;
    String GameButtons4[] = {"img/eng/btn_games5.png","img/in/btn_games5.png","img/sun/btn_games5.png"};
    ImageIcon game4Icon;
    private JFrame frame;
    private ProcessBuilder pb;

    public GameMenu(){
        super("Game Menu");
        switch (Game.language_status) {
            case 0:
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackButtons[0]));
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[0]));
                game1Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons1[0]));
                game2Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons2[0]));
                game3Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons3[0]));
                game4Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons4[0]));
                break;
            case 1:
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackButtons[1]));
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[1]));
                game1Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons1[1]));
                game2Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons2[1]));
                game3Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons3[1]));
                game4Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons4[1]));
                break;
            case 2:
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackButtons[2]));
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[2]));
                game1Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons1[2]));
                game2Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons2[2]));
                game3Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons3[2]));
                game4Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons4[2]));
                break;
            default:
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackButtons[0]));
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[0]));
                game1Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons1[0]));
                game2Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons2[0]));
                game3Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons3[0]));
                game4Icon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons4[0]));
                break;
        }
        try {
            this.create().tampil();
        } catch (IOException e) { }
        addCloseWindowListener();
    }

    private GameMenu create() throws IOException {
        frame = createFrame();
        frame.add(createContent(), BorderLayout.CENTER);
        frame.setResizable(false);

        return this;
    }

    private JFrame createFrame() {
        JFrame frameb = new JFrame(getClass().getName());
        frameb.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return frameb;
    }

    private void tampil() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private Component createContent() throws IOException {
        final BufferedImage image = requestImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(image, 0, 0, null);
                Graphics2D g2d = (Graphics2D) g.create();
                int tileWidth = image.getWidth();
                int tileHeight = image.getHeight();
                for (int y = 0; y < getHeight(); y += tileHeight) {
                    for (int x = 0; x < getWidth(); x += tileWidth) {
                        g2d.drawImage(image, x, y, this);
                    }
                }
                g2d.dispose();
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel panelBack = new JPanel();
        panelBack.setOpaque(false);
        panelBack.setLayout(new BoxLayout(panelBack, BoxLayout.X_AXIS));

        JPanel panelDalem = new JPanel();
        panelDalem.setOpaque(false);
        panelDalem.setLayout(new BoxLayout(panelDalem, BoxLayout.LINE_AXIS));

        JPanel panelDalem1 = new JPanel();
        panelDalem1.setOpaque(false);
        panelDalem1.setLayout(new BoxLayout(panelDalem1, BoxLayout.Y_AXIS));

        JPanel panelDalem2 = new JPanel();
        panelDalem2.setOpaque(false);
        panelDalem2.setLayout(new BoxLayout(panelDalem2, BoxLayout.LINE_AXIS));
        
        backButton.setSize(215,250);
        Image backImg = backIcon.getImage();
        Image backImgB = backImg.getScaledInstance(backButton.getWidth(), backButton.getHeight(), 0);
        backButton.setIcon(new ImageIcon(backImgB));
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameButton.setSize(125,150);
        Image gameImg = gameIcon.getImage();
        Image gameImgB = gameImg.getScaledInstance(gameButton.getWidth(), gameButton.getHeight(), 0);
        gameButton.setIcon(new ImageIcon(gameImgB));
        gameButton.setContentAreaFilled(false);
        gameButton.setOpaque(false);
        gameButton.setBorderPainted(false);
        gameButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        gameButton1.setSize(125,150);
        Image game1Img = game1Icon.getImage();
        Image game1ImgB = game1Img.getScaledInstance(gameButton1.getWidth(), gameButton1.getHeight(), 0);
        gameButton1.setIcon(new ImageIcon(game1ImgB));
        gameButton1.setContentAreaFilled(false);
        gameButton1.setOpaque(false);
        gameButton1.setBorderPainted(false);
        gameButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        gameButton2.setSize(125,150);
        Image game2Img = game2Icon.getImage();
        Image game2ImgB = game2Img.getScaledInstance(gameButton2.getWidth(), gameButton2.getHeight(), 0);
        gameButton2.setIcon(new ImageIcon(game2ImgB));
        gameButton2.setContentAreaFilled(false);
        gameButton2.setOpaque(false);
        gameButton2.setBorderPainted(false);
        gameButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        gameButton3.setSize(125,150);
        Image game3Img = game3Icon.getImage();
        Image game3ImgB = game3Img.getScaledInstance(gameButton3.getWidth(), gameButton3.getHeight(), 0);
        gameButton3.setIcon(new ImageIcon(game3ImgB));
        gameButton3.setContentAreaFilled(false);
        gameButton3.setOpaque(false);
        gameButton3.setBorderPainted(false);
        gameButton3.setAlignmentX(Component.LEFT_ALIGNMENT);

        gameButton4.setSize(125,150);
        Image game4Img = game4Icon.getImage();
        Image game4ImgB = game4Img.getScaledInstance(gameButton4.getWidth(), gameButton4.getHeight(), 0);
        gameButton4.setIcon(new ImageIcon(game4ImgB));
        gameButton4.setContentAreaFilled(false);
        gameButton4.setOpaque(false);
        gameButton4.setBorderPainted(false);
        gameButton4.setAlignmentX(Component.RIGHT_ALIGNMENT);


        GameMenu.ButtonHandler handler = new GameMenu.ButtonHandler();
        backButton.addActionListener(handler);
        gameButton.addActionListener(handler);
        gameButton1.addActionListener(handler);
        gameButton2.addActionListener(handler);
        gameButton3.addActionListener(handler);
        gameButton4.addActionListener(handler);
        
        panelBack.add(backButton);

        panelDalem.add(gameButton);
        panelDalem.add(Box.createRigidArea(new Dimension(135, 100)));

        panelDalem1.add(Box.createRigidArea(new Dimension(0, 125)));
        panelDalem1.add(gameButton1);
        panelDalem.add(panelDalem1);

        panelDalem.add(Box.createRigidArea(new Dimension(135, 200)));
        panelDalem.add(gameButton2);

        panelDalem2.add(gameButton3);
        panelDalem2.add(Box.createRigidArea(new Dimension(475, 0)));
        panelDalem2.add(gameButton4);

        //panel.add(Box.createRigidArea(new Dimension(900, 225)));
        panel.add(panelBack);
        panel.add(panelDalem);
        panel.add(Box.createRigidArea(new Dimension(900, 25)));
        panel.add(panelDalem2);

        panelDalem.setPreferredSize(new Dimension(900, 155));
        panelDalem1.setPreferredSize(new Dimension(650, 155));
        panel.setPreferredSize(new Dimension(900, 1000));

        return panel;
    }

    public static void music() {
        String soundName = "/sound/gong.wav";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(GameMenu.class.getResource(soundName));
        } catch (UnsupportedAudioFileException | IOException e) { }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) { }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) { }
        clip.start();
    }

    private BufferedImage requestImage() {
        try {
            image = ImageIO.read(GameMenu.class.getResourceAsStream("/img/bg_games.png"));
        } catch (IOException e) { }

        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) { }

    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InputStream is;
            File tempFile = null;
            File source = new File("src/jar/game1/");
            File dest = new File(System.getProperty("java.io.tmpdir"));
            String textPath, textFolderPath;
            Path path;
            
            if (e.getSource() == gameButton) {
                try {
                    FileUtils.copyDirectory(source, dest);
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(Game.btn_fx_status || Game.bgm_status){
                    music();
                    pb = new ProcessBuilder("java", "-jar", "TryGame_soundfx.jar");
                }
                else{
                    pb = new ProcessBuilder("java", "-jar", "TryGame_mute.jar");
                }
                textFolderPath = dest.getAbsolutePath();
                pb.directory(new File(textFolderPath));
                try {
                    pb.start();
                } catch (IOException e1) { }
            }

            if (e.getSource() == gameButton1) {
                if(Game.btn_fx_status)
                    music();
                is = GameMenu.class.getResourceAsStream("/jar/game2/Puzzle5.jar");
                
                try {
                    tempFile = File.createTempFile("Puzzle5", ".jar");
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                textPath = tempFile.getAbsolutePath();
                String tempFilePath = textPath.substring(0,textPath.lastIndexOf(File.separator));
                path = Paths.get(textPath);

                try {
                    Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tempFile.deleteOnExit();
                pb = new ProcessBuilder("java", "-jar", tempFile.toString());
                pb.directory(new File(tempFilePath));
                try {
                    pb.start();
                } catch (IOException e1) { }

            }

            if (e.getSource() == gameButton2) {
                if(Game.btn_fx_status)
                    music();
                is = GameMenu.class.getResourceAsStream("/jar/game3/NebakKecap.jar");
                
                try {
                    tempFile = File.createTempFile("NebakKecap", ".jar");
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                textPath = tempFile.getAbsolutePath();
                String tempFilePath = textPath.substring(0,textPath.lastIndexOf(File.separator));
                path = Paths.get(textPath);

                try {
                    Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tempFile.deleteOnExit();
                pb = new ProcessBuilder("java", "-jar", tempFile.toString());
                pb.directory(new File(tempFilePath));
                try {
                    pb.start();
                } catch (IOException e1) { }
            }

            if (e.getSource() == gameButton3) {
                if(Game.btn_fx_status)
                    music();
                new GameBoard();
            }
            
            if (e.getSource() == gameButton4) {
                if(Game.btn_fx_status)
                    music();
                is = GameMenu.class.getResourceAsStream("/jar/game4/SlidingPuzzle.jar");
                
                try {
                    tempFile = File.createTempFile("SlidingPuzzle", ".jar");
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                textPath = tempFile.getAbsolutePath();
                String tempFilePath = textPath.substring(0,textPath.lastIndexOf(File.separator));
                path = Paths.get(textPath);

                try {
                    Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tempFile.deleteOnExit();
                pb = new ProcessBuilder("java", "-jar", tempFile.toString());
                pb.directory(new File(tempFilePath));
                try {
                    pb.start();
                } catch (IOException e1) { } 
            }
            
            if (e.getSource() == backButton) {
                    if(Game.btn_fx_status)
                        music();
                    try {
                        new Game().create().tampil();
                    } catch (IOException ex) { }
                    frame.dispose();
                }
        }
    }
    
    private void addCloseWindowListener()
    {
        // NOTE: Must be DO_NOTHING_ON_CLOSE for prompt to function correctly
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                String ObjButtons[] = {"Yes","No"};
                String ObjButtons1[] = {"Ya","Tidak"};
                String ObjButtons2[] = {"Muhun","Teu"};
                String PromptTitle[] = {"Exit?","Keluar?","Kaluar?"};
                int PromptResult = 0;
                switch (Game.language_status) {
                    case 0:
                        PromptResult = JOptionPane.showOptionDialog(null,PromptTitle[0],"Mandala Apps",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons,ObjButtons[1]);
                        break;
                    case 1:
                        PromptResult = JOptionPane.showOptionDialog(null,PromptTitle[1],"Mandala Apps",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons1,ObjButtons[1]);
                        break;
                    case 2:
                        PromptResult = JOptionPane.showOptionDialog(null,PromptTitle[2],"Mandala Apps",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButtons2,ObjButtons[1]);
                        break;
                    default:
                        gameIcon = new ImageIcon(GameButtons[0]);
                        break;
                }
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                new GameMenu().create().tampil();
            } catch (IOException e) {
            }
        });
    }
}
