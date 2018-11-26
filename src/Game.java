import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends JPanel {

    JButton gameButton = new JButton();
    JButton galleryButton = new JButton();
    JButton settingsButton = new JButton();
    JButton exitButton = new JButton();
    JButton helpButton = new JButton();
    BufferedImage image;
    
    String GameButtons[] = {"img/eng/btn_games_eng.png","img/in/btn_games_in.png", "img/sun/btn_games_sun.png"};
    ImageIcon gameIcon;
    String GalleryButtons[] = {"img/eng/btn_gallery_eng.png","img/in/btn_gallery_in.png", "img/sun/btn_gallery_sun.png"};
    ImageIcon galleryIcon;
    String SettingsButtons[] = {"img/eng/btn_settings_eng.png","img/in/btn_settings_in.png", "img/sun/btn_settings_sun.png"};
    ImageIcon settingsIcon;
    ImageIcon helpIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/btn_help.png"));
    ImageIcon exitIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/btn_exit.png"));
    private JFrame frame;
    public static Clip clip;
    public static Clip btn_fx;
    public static boolean btn_fx_status = true;
    public static boolean bgm_status = true;
    public static int language_status = 0;

    public Game create() throws IOException {
        switch (Game.language_status) {
            case 0:
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[0]));
                galleryIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GalleryButtons[0]));
                settingsIcon = new ImageIcon(this.getClass().getClassLoader().getResource(SettingsButtons[0]));
                break;
            case 1:
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[1]));
                galleryIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GalleryButtons[1]));
                settingsIcon = new ImageIcon(this.getClass().getClassLoader().getResource(SettingsButtons[1]));
                break;
            case 2:
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[2]));
                galleryIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GalleryButtons[2]));
                settingsIcon = new ImageIcon(this.getClass().getClassLoader().getResource(SettingsButtons[2]));
                break;
            default:
                gameIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GameButtons[0]));
                galleryIcon = new ImageIcon(this.getClass().getClassLoader().getResource(GalleryButtons[0]));
                settingsIcon = new ImageIcon(this.getClass().getClassLoader().getResource(SettingsButtons[0]));
                break;
        }
        
        frame = createFrame();
        frame.setTitle("Mandala Apps");
        frame.add(createContent(), BorderLayout.CENTER);
        addCloseWindowListener();
        return this;
    }

    private JFrame createFrame() {
        JFrame frameb = new JFrame(getClass().getName());
        frameb.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return frameb;
    }

    public void tampil() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        JPanel panelBtnSupp = new JPanel();
        panelBtnSupp.setOpaque(false);
        panelBtnSupp.setLayout(new BoxLayout(panelBtnSupp, BoxLayout.X_AXIS));

        JPanel panelDalem = new JPanel();
        panelDalem.setOpaque(false);
        panelDalem.setLayout(new BoxLayout(panelDalem, BoxLayout.X_AXIS));

        JPanel panelDalem1 = new JPanel();
        panelDalem1.setOpaque(false);
        panelDalem1.setLayout(new BoxLayout(panelDalem1, BoxLayout.X_AXIS));

        gameButton.setSize(300,150);
        Image gameImg = gameIcon.getImage();
        Image gameImgB = gameImg.getScaledInstance(gameButton.getWidth(), gameButton.getHeight(), 0);
        gameButton.setIcon(new ImageIcon(gameImgB));
        gameButton.setName("game");
        gameButton.setContentAreaFilled(false);
        gameButton.setOpaque(false);
        gameButton.setBorderPainted(false);

        galleryButton.setSize(150,150);
        Image galleryImg = galleryIcon.getImage();
        Image galleryImgB = galleryImg.getScaledInstance(galleryButton.getWidth(), galleryButton.getHeight(), 0);
        galleryButton.setIcon(new ImageIcon(galleryImgB));
        galleryButton.setName("gallery");
        galleryButton.setContentAreaFilled(false);
        galleryButton.setOpaque(false);
        galleryButton.setBorderPainted(false);

        settingsButton.setPreferredSize(new Dimension(150, 150));
        settingsButton.setSize(325,150);
        Image settingsImg = settingsIcon.getImage();
        Image settingsImgB = settingsImg.getScaledInstance(settingsButton.getWidth(), settingsButton.getHeight(), 0);
        settingsButton.setIcon(new ImageIcon(settingsImgB));
        settingsButton.setName("settings");
        settingsButton.setContentAreaFilled(false);
        settingsButton.setOpaque(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        exitButton.setSize(90,90);
        Image exitImg = exitIcon.getImage();
        Image exitImgB = exitImg.getScaledInstance(exitButton.getWidth(), exitButton.getHeight(), 0);
        exitButton.setIcon(new ImageIcon(exitImgB));
        exitButton.setName("exit");
        exitButton.setContentAreaFilled(false);
        exitButton.setOpaque(false);
        exitButton.setBorderPainted(false);
        
        helpButton.setSize(90,90);
        Image helpImg = helpIcon.getImage();
        Image helpImgB = helpImg.getScaledInstance(helpButton.getWidth(), helpButton.getHeight(), 0);
        helpButton.setIcon(new ImageIcon(helpImgB));
        helpButton.setName("help");
        helpButton.setContentAreaFilled(false);
        helpButton.setOpaque(false);
        helpButton.setBorderPainted(false);

        ButtonHandler handler = new ButtonHandler();
        gameButton.addActionListener(handler);
        galleryButton.addActionListener(handler);
        settingsButton.addActionListener(handler);
        exitButton.addActionListener(handler);
        helpButton.addActionListener(handler);

        panelBtnSupp.add(exitButton);
        panelBtnSupp.add(Box.createVerticalStrut(2));
        panelBtnSupp.add(helpButton);
        panelBtnSupp.add(Box.createRigidArea(new Dimension(650, 5)));
        panel.add(panelBtnSupp);
        panel.add(Box.createRigidArea(new Dimension(5, 285)));
        panelDalem.add(Box.createRigidArea(new Dimension(560, 5)));
        panelDalem.add(settingsButton);
        panel.add(panelDalem);
        panel.add(Box.createRigidArea(new Dimension(5, 45)));
        panelDalem1.add(Box.createRigidArea(new Dimension(250, 5)));
        panelDalem1.add(gameButton);
        panelDalem1.add(galleryButton);
        panel.add(panelDalem1);

        panelDalem.setPreferredSize(new Dimension(325, 155));
        panelDalem1.setPreferredSize(new Dimension(650, 155));
        panel.setPreferredSize(new Dimension(890, 1000));

        return panel;
    }

    private BufferedImage requestImage() {

        try {
            image = ImageIO.read(Game.class.getResourceAsStream("/img/bg_fix.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static Clip btnSoundEffect() {
        String soundName = "/sound/btn.wav";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource(soundName));
        } catch (UnsupportedAudioFileException | IOException e) { }
        btn_fx = null;
        try {
            btn_fx = AudioSystem.getClip();
        } catch (LineUnavailableException e) { }
        try {
            btn_fx.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) { }
        btn_fx.start();
        return btn_fx;
    }

    public static Clip bgm() {
        String soundName = "/sound/bgm.wav";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Game.class.getResource(soundName));
        } catch (UnsupportedAudioFileException | IOException e) { }
        Game.clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) { }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) { }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        return clip;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == galleryButton) {
                if(btn_fx_status)
                    btnSoundEffect();
                //new Gallery();
                ComingSoon soon = new ComingSoon();
                soon.setVisible(true);
            }
            if (e.getSource() == settingsButton) {
                if(btn_fx_status)
                    btnSoundEffect();
                new Settings();
                frame.dispose();
            }
            if (e.getSource() == gameButton) {
                if(btn_fx_status)
                    btnSoundEffect();
                new GameMenu();
                frame.dispose();
            }
            if (e.getSource() == helpButton) {
                if(btn_fx_status)
                    btnSoundEffect();
                About about = new About();
                about.setVisible(true);
            }
            
            if (e.getSource() == exitButton) {
                if(btn_fx_status)
                    btnSoundEffect();
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
                    System.exit(0);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

    public static void main(String[] args) throws IOException{
        GameSplash splash = new GameSplash();
        splash.setVisible(true);
        int i;
        try {
            for(i=0;i<=100;i++){
                Thread.sleep(40);
                splash.loading.setText(Integer.toString(i)+"%"); 
                splash.loadingBar.setValue(i);
                if(i==100){
                   splash.setVisible(false);
                   new Game().create().tampil();
                   bgm().start();
                }
                
            }
            
        } catch (InterruptedException ex) { }
                   
 
    }
}
