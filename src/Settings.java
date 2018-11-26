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

public class Settings extends JFrame implements ActionListener{

    JButton btnMusik = new JButton();
    JButton btnSuara = new JButton();
    JButton btnNext = new JButton();
    JButton btnPrev = new JButton();
    JButton btnKembali = new JButton();
    JButton btnReset = new JButton();
    JLabel languageLabel = new JLabel();

    String ImageBg[] = {"/img/eng/settings_bg_eng.jpg","/img/in/settings_bg_in.jpg","/img/sun/settings_bg_sun.jpg"};
    BufferedImage image;
    //String MusicOnBtn[] = {"img/eng/music_on_eng.png","img/in/music_on_in.png","img/sun/music_on_sun.png"};
    ImageIcon musikIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource("img/music_on.png"));
    //String MusicOffBtn[] = {"img/eng/music_off_eng.png","img/in/music_off_in.png","img/sun/music_off_sun.png"};
    ImageIcon musikIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource("img/music_off.png"));
    //String SoundOnBtn[] = {"img/eng/sound_on_eng.png","img/in/sound_on_in.png","img/sun/sound_on_sun.png"};
    ImageIcon suaraIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource("img/sound_on.png"));
    //String SoundOffBtn[] = {"img/eng/sound_off_eng.png","img/in/sound_off_in.png","img/sun/sound_off_sun.png"};
    ImageIcon suaraIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource("img/sound_off.png"));
    ImageIcon nextIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/settings_kanan.png"));
    ImageIcon prevIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/settings_kiri.png"));
    String BackBtn[] = {"img/eng/back_eng.png","img/in/back_in.png","img/sun/back_sun.png"};
    ImageIcon backIcon;
    ImageIcon resetIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/reset_eng_in_sun.png"));
    String LanguageLabel[] = {"img/eng/bhs_eng.png","img/in/bhs_in.png","img/sun/bhs_sun.png"};
    ImageIcon language;
    private JFrame frame;

    public Settings(){
        super("Settings");
        switch (Game.language_status) {
            case 0:
                //musikIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOnBtn[0]));
                //musikIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOffBtn[0]));
                //suaraIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOnBtn[0]));
                //suaraIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOffBtn[0]));
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackBtn[0]));
                language = new ImageIcon(this.getClass().getClassLoader().getResource(LanguageLabel[0]));
                break;
            case 1:
                //musikIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOnBtn[1]));
                //musikIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOffBtn[1]));
                //suaraIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOnBtn[1]));
                //suaraIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOffBtn[1]));
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackBtn[1]));
                language = new ImageIcon(this.getClass().getClassLoader().getResource(LanguageLabel[1]));
                break;
            case 2:
                //musikIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOnBtn[2]));
                //musikIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOffBtn[2]));
                //suaraIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOnBtn[2]));
                //suaraIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOffBtn[2]));
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackBtn[2]));
                language = new ImageIcon(this.getClass().getClassLoader().getResource(LanguageLabel[2]));
                break;
            default:
                //musikIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOnBtn[0]));
                //musikIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(MusicOffBtn[0]));
                //suaraIcon_on = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOnBtn[0]));
                //suaraIcon_off = new ImageIcon(this.getClass().getClassLoader().getResource(SoundOffBtn[0]));
                backIcon = new ImageIcon(this.getClass().getClassLoader().getResource(BackBtn[0]));
                language = new ImageIcon(this.getClass().getClassLoader().getResource(LanguageLabel[0]));
                break;
        }
        try {
            this.create().tampil();
        } catch (IOException e) { }
        addCloseWindowListener();
    }

    private Settings create() throws IOException {
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
    
    public void btn_suara_on(){
        Image sound_on = suaraIcon_on.getImage();
        Image soundonb = sound_on.getScaledInstance(btnSuara.getWidth(), btnSuara.getHeight(), 0);
        btnSuara.setIcon(new ImageIcon(soundonb));
        btnSuara.setName("sound_on");
    }
    
    public void btn_suara_off(){
        Image sound_off = suaraIcon_off.getImage();
        Image soundoffb = sound_off.getScaledInstance(btnSuara.getWidth(), btnSuara.getHeight(), 0);
        btnSuara.setIcon(new ImageIcon(soundoffb));
        btnSuara.setName("sound_off");
    }
    
    public void bgm_on(){
        Image music_on = musikIcon_on.getImage();
        Image musiconb = music_on.getScaledInstance(btnMusik.getWidth(), btnMusik.getHeight(), 0);
        btnMusik.setIcon(new ImageIcon(musiconb));
        btnMusik.setName("music_on");
    }
    
    public void bgm_off(){
        Image music_off = musikIcon_off.getImage();
        Image musicoffb = music_off.getScaledInstance(btnMusik.getWidth(), btnMusik.getHeight(), 0);
        btnMusik.setIcon(new ImageIcon(musicoffb));
        btnMusik.setName("music_off");
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

        JPanel panelDalem = new JPanel();
        panelDalem.setOpaque(false);
        panelDalem.setLayout(new BoxLayout(panelDalem, BoxLayout.LINE_AXIS));

        JPanel panelDalem1 = new JPanel();
        panelDalem1.setOpaque(false);
        panelDalem1.setLayout(new BoxLayout(panelDalem1, BoxLayout.LINE_AXIS));

        JPanel panelDalem2 = new JPanel();
        panelDalem2.setOpaque(false);
        panelDalem2.setLayout(new BoxLayout(panelDalem2, BoxLayout.LINE_AXIS));


        btnSuara.setSize(113,45);
        if(Game.btn_fx_status){
            btn_suara_on();
        }
        else{
            btn_suara_off();
        }
        btnSuara.setContentAreaFilled(false);
        btnSuara.setOpaque(false);
        btnSuara.setBorderPainted(false);
        btnSuara.setAlignmentX(Component.RIGHT_ALIGNMENT);

        btnMusik.setSize(113,45);
        if(Game.bgm_status){
            bgm_on();
        }
        else{
            bgm_off();
        }
        btnMusik.setContentAreaFilled(false);
        btnMusik.setOpaque(false);
        btnMusik.setBorderPainted(false);
        btnMusik.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnPrev.setSize(50,50);
        Image prev = prevIcon.getImage();
        Image prevb = prev.getScaledInstance(btnPrev.getWidth(), btnPrev.getHeight(), 0);
        btnPrev.setIcon(new ImageIcon(prevb));
        btnPrev.setName("prev");
        btnPrev.setContentAreaFilled(false);
        btnPrev.setOpaque(false);
        btnPrev.setBorderPainted(false);
        btnPrev.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        languageLabel.setSize(150,50);
        Image lngLabel = language.getImage();
        Image lngLabelb = lngLabel.getScaledInstance(languageLabel.getWidth(), languageLabel.getHeight(), 0);
        languageLabel.setIcon(new ImageIcon(lngLabelb));
        switch (Game.language_status) {
            case 0:
                languageLabel.setName("english");
                break;
            case 1:
                languageLabel.setName("indonesia");
                break;
            default:
                languageLabel.setName("sunda");
                break;
        }
        
        languageLabel.setOpaque(false);
        languageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnNext.setSize(50,50);
        Image next = nextIcon.getImage();
        Image nextb = next.getScaledInstance(btnNext.getWidth(), btnNext.getHeight(), 0);
        btnNext.setIcon(new ImageIcon(nextb));
        btnNext.setName("next");
        btnNext.setContentAreaFilled(false);
        btnNext.setOpaque(false);
        btnNext.setBorderPainted(false);
        btnNext.setAlignmentX(Component.RIGHT_ALIGNMENT);

        btnKembali.setSize(212,106);
        Image back_icon = backIcon.getImage();
        Image back_iconb = back_icon.getScaledInstance(btnKembali.getWidth(), btnKembali.getHeight(), 0);
        btnKembali.setIcon(new ImageIcon(back_iconb));
        btnKembali.setName("back");
        btnKembali.setContentAreaFilled(false);
        btnKembali.setOpaque(false);
        btnKembali.setBorderPainted(false);
        btnKembali.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        btnReset.setSize(212,106);
        Image reset_icon = resetIcon.getImage();
        Image reset_iconb = reset_icon.getScaledInstance(btnReset.getWidth(), btnReset.getHeight(), 0);
        btnReset.setIcon(new ImageIcon(reset_iconb));
        btnReset.setName("reset");
        btnReset.setContentAreaFilled(false);
        btnReset.setOpaque(false);
        btnReset.setBorderPainted(false);
        btnReset.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        Settings.ButtonHandler handler = new Settings.ButtonHandler();
        btnSuara.addActionListener(handler);
        btnMusik.addActionListener(handler);
        btnKembali.addActionListener(handler);
        btnReset.addActionListener(handler);
        btnPrev.addActionListener(handler);
        btnNext.addActionListener(handler);


        panelDalem.add(Box.createRigidArea(new Dimension(213, 100)));
        panelDalem.add(btnMusik);
        panelDalem.add(Box.createRigidArea(new Dimension(107, 100)));
        panelDalem.add(btnSuara);

        panelDalem1.add(Box.createRigidArea(new Dimension(250, 50)));
        panelDalem1.add(btnPrev);
        panelDalem1.add(languageLabel);
        panelDalem1.add(btnNext);

        panelDalem2.add(btnKembali);
        panelDalem2.add(btnReset);
        

        panel.add(Box.createRigidArea(new Dimension(0, 372)));
        panel.add(panelDalem);
        panel.add(panelDalem1);
        panel.add(panelDalem2);


        panelDalem.setPreferredSize(new Dimension(650, 62));
        panelDalem1.setPreferredSize(new Dimension(650, 50));
        panelDalem2.setPreferredSize(new Dimension(650, 106));
        panel.setPreferredSize(new Dimension(900, 1000));

        return panel;
    }

    public static void btnGongSoundEffect() {
        String soundName = "/sound/gong.wav";
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Settings.class.getResource(soundName));
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
            switch (Game.language_status) {
            case 0:
                image = ImageIO.read(Settings.class.getResourceAsStream(ImageBg[0]));
                break;
            case 1:
                image = ImageIO.read(Settings.class.getResourceAsStream(ImageBg[1]));
                break;
            case 2:
                image = ImageIO.read(Settings.class.getResourceAsStream(ImageBg[2]));
                break;
            default:
                image = ImageIO.read(Settings.class.getResourceAsStream(ImageBg[0]));
                break;
            }
        } catch (IOException e) { }

        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object comp = e.getSource();
            if(comp instanceof JButton){
                JButton button = (JButton)comp;
                switch (button.getName()) {
                    case "sound_on":
                        btnSuara.setSize(113,45);
                        btnSuara.setContentAreaFilled(false);
                        btnSuara.setOpaque(false);
                        btnSuara.setBorderPainted(false);
                        btnSuara.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        btn_suara_off();
                        Game.btn_fx_status = false;
                        break;
                    case "sound_off":
                        btnSuara.setSize(113,45);
                        btnSuara.setContentAreaFilled(false);
                        btnSuara.setOpaque(false);
                        btnSuara.setBorderPainted(false);
                        btnSuara.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        btn_suara_on();
                        Game.btn_fx_status = true;
                        break;
                    case "music_on":
                        btnMusik.setSize(113,45);
                        btnMusik.setContentAreaFilled(false);
                        btnMusik.setOpaque(false);
                        btnMusik.setBorderPainted(false);
                        btnMusik.setAlignmentX(Component.LEFT_ALIGNMENT);
                        bgm_off();
                        Game.clip.stop();
                        Game.bgm().stop();
                        Game.bgm_status = false;
                        break;
                    case "music_off":
                        btnMusik.setSize(113,45);
                        btnMusik.setContentAreaFilled(false);
                        btnMusik.setOpaque(false);
                        btnMusik.setBorderPainted(false);
                        btnMusik.setAlignmentX(Component.LEFT_ALIGNMENT);
                        bgm_on();
                        Game.bgm().start();
                        Game.clip.start();
                        Game.bgm_status = true;
                        break;
                    default:
                        break;
                }
                
                if (button.getName().equals("back")) {
                    if(Game.btn_fx_status)
                        btnGongSoundEffect();
                    try {
                        new Game().create().tampil();
                    } catch (IOException ex) { }
                    frame.dispose();
                }
                
                if(button.getName().equals("reset")){
                    btnGongSoundEffect();
                    bgm_on();
                    btn_suara_on();
                    Game.clip.start();
                    Game.language_status = 0;
                    Game.bgm_status = true;
                    Game.btn_fx_status = true;
                    frame.dispose();
                    new Settings();
                }
                
                if(button.getName().equals("next")){
                    if(Game.btn_fx_status)
                        btnGongSoundEffect();
                    if(languageLabel.getName().equals("english")){
                        languageLabel.setName("indonesia");
                        Game.language_status = 1;
                        frame.dispose();
                        new Settings();
                    }
                    else if(languageLabel.getName().equals("indonesia")){
                        languageLabel.setName("sunda");
                        Game.language_status = 2;
                        frame.dispose();
                        new Settings();
                    }
                    else {
                        languageLabel.setName("english");
                        Game.language_status = 0;
                        frame.dispose();
                        new Settings();
                    }
                }
                
                if(button.getName().equals("prev")){
                    if(Game.btn_fx_status)
                        btnGongSoundEffect();
                    switch (languageLabel.getName()) {
                        case "english":
                            languageLabel.setName("sunda");
                            Game.language_status = 2;
                            frame.dispose();
                            new Settings();
                            break;
                        case "indonesia":
                            languageLabel.setName("english");
                            Game.language_status = 0;
                            frame.dispose();
                            new Settings();
                            break;
                        default:
                            languageLabel.setName("indonesia");
                            Game.language_status = 1;
                            frame.dispose();
                            new Settings();
                            break;
                    }
                }
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
                int PromptResult = JOptionPane.showOptionDialog(null,"Yakin ingin keluar?","Mandala Apps",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
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
                new Settings().create().tampil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
