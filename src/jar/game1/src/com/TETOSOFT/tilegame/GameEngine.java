package jar.game1.src.com.TETOSOFT.tilegame;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import jar.game1.src.com.TETOSOFT.graphics.*;
import jar.game1.src.com.TETOSOFT.input.*;
import jar.game1.src.com.TETOSOFT.test.GameCore;
import jar.game1.src.com.TETOSOFT.tilegame.sprites.*;

/**
 * GameManager manages all parts of the game.
 */
public class GameEngine extends GameCore 
{
    
    public static void main(String[] args) 
    {
        new GameEngine().run();
    }
    
    public static final float GRAVITY = 0.002f;
    
    private Point pointCache = new Point();
    private TileMap map;
    private MapLoader mapLoader;
    private InputManager inputManager;
    private TileMapDrawer drawer;
    
    private GameAction moveLeft;
    private GameAction moveRight;
    private GameAction jump;
    private GameAction exit;
    private GameAction mulai;
    private GameAction panunjuk;
    private int collectedStars=0;
    private int collectedMission=0;
    private int currentLevel = 1;
    private int numLives = 5;
    private int i=0;
   
    public void init()
    {
        super.init();
        
        // set up input manager
        initInput();
        
        // start resource manager
        mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());
        
        // load resources
        drawer = new TileMapDrawer();
        drawer.setBackground(mapLoader.loadImage("background1.jpg"));
        try{
            AudioInputStream bgm = AudioSystem.getAudioInputStream(new File("sounds/mainMusic.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(bgm);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
                e.printStackTrace();
        }
        // load first map
        map = mapLoader.loadNextMap();
    }
    
    
    /**
     * Closes any resurces used by the GameManager.
     */
    public void stop() {
        super.stop();
        
    }
    
    private void initInput() {
        moveLeft = new GameAction("moveLeft");
        moveRight = new GameAction("moveRight");
        jump = new GameAction("jump", GameAction.DETECT_INITAL_PRESS_ONLY);
        exit = new GameAction("exit",GameAction.DETECT_INITAL_PRESS_ONLY);
        mulai = new GameAction("mulai",GameAction.DETECT_INITAL_PRESS_ONLY);
        panunjuk = new GameAction("panunjuk",GameAction.DETECT_INITAL_PRESS_ONLY);
        
        inputManager = new InputManager(screen.getFullScreenWindow());
        inputManager.setCursor(InputManager.INVISIBLE_CURSOR);
        
        inputManager.mapToKey(moveLeft, KeyEvent.VK_LEFT);
        inputManager.mapToKey(moveRight, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(jump, KeyEvent.VK_SPACE);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
        //testing
        inputManager.mapToKey(mulai, KeyEvent.VK_ENTER);
        inputManager.mapToKey(panunjuk, KeyEvent.VK_P);
    }
    
    
    private void checkInput(long elapsedTime) 
    {
        
        if (exit.isPressed()) {
            stop();
        }
        
        // Restart when in game over state
        if (mulai.isPressed() && mapLoader.currentMap == -2){
            drawer = new TileMapDrawer();
            drawer.setBackground(mapLoader.loadImage("credit.jpg"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            mapLoader.currentMap = 1;
            collectedStars=0;
            collectedMission=0;
            numLives = 5;
            i=0;
            initInput();
            mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());
        
            // load resources
            drawer.setBackground(mapLoader.loadImage("background1.jpg"));
            map = mapLoader.loadNextMap();
        }
       
        Player player = (Player)map.getPlayer();
        if (player.isAlive()) 
        {
            float velocityX = 0;
            if (moveLeft.isPressed()) 
            {
                velocityX-=player.getMaxSpeed();
            }
            if (moveRight.isPressed()) {
                velocityX+=player.getMaxSpeed();
            }
            if (jump.isPressed()) {
                player.jump(false);
            }
            player.setVelocityX(velocityX);
        }
        
    }
    
    
    public void draw(Graphics2D g) {
        drawer.draw(g, map, screen.getWidth(), screen.getHeight());
        // draw score on top of screen
        if (mapLoader.currentMap > -1){     
            g.setFont(new Font("Open Sans", Font.PLAIN, 17));
            g.setColor(Color.WHITE);
            g.drawString("Pencet ESC Upami Bade Kaluar.",10.0f,20.0f);
            g.setColor(Color.GREEN);
            g.drawString("Skor : "+collectedStars,290.0f,20.0f);
            g.setColor(Color.GREEN);
            g.drawString("Misi :"+collectedMission +" / 4",410.0f,20.0f);
            g.setColor(Color.YELLOW);
            g.drawString("Nyawa : "+(numLives),550.0f,20.0f );
            g.setColor(Color.WHITE);
            g.drawString("Tingkatan ka : "+mapLoader.currentMap,670.0f,20.0f);
        }
        // Enter finish game, state level = -1
        else if (mapLoader.currentMap == -1){
            g.setFont(new Font("Open Sans", Font.PLAIN, 25));
            g.setColor(Color.ORANGE);
            g.drawString("Pencet ESC Upami Bade Kaluar.",215.0f,40.0f);
            g.drawString("Pencet ENTER Upami Atos",500.0f,80.0f);
            g.setFont(new Font("Open Sans", Font.PLAIN, 35));
            g.setColor(Color.GREEN);
            g.drawString("Skor: "+collectedStars,340.0f,300.0f);
            
        }
        // Enter game over, state level = -2
        else{
            g.setFont(new Font("Open Sans", Font.PLAIN, 16));
            g.setColor(Color.WHITE);
            g.drawString("Pencet ESC Upami Bade Kaluar",20.0f,80.0f);
            g.setFont(new Font("Open Sans", Font.PLAIN, 45));
            g.setColor(Color.BLACK);
            g.drawString("Skor: "+collectedStars,330.0f,150.0f);
            g.setFont(new Font("Open Sans", Font.PLAIN, 16));
            g.setColor(Color.WHITE);
            g.drawString("Pencet ENTER Upami Cobian Deui",500.0f,80.0f);
        }
    } 
    
    
    
    /**
     * Gets the current map.
     */
    public TileMap getMap() {
        return map;
    }
    
    /**
     * Gets the tile that a Sprites collides with. Only the
     * Sprite's X or Y should be changed, not both. Returns null
     * if no collision is detected.
     */
    public Point getTileCollision(Sprite sprite, float newX, float newY) 
    {
        float fromX = Math.min(sprite.getX(), newX);
        float fromY = Math.min(sprite.getY(), newY);
        float toX = Math.max(sprite.getX(), newX);
        float toY = Math.max(sprite.getY(), newY);
        
        // get the tile locations
        int fromTileX = TileMapDrawer.pixelsToTiles(fromX);
        int fromTileY = TileMapDrawer.pixelsToTiles(fromY);
        int toTileX = TileMapDrawer.pixelsToTiles(
                toX + sprite.getWidth() - 1);
        int toTileY = TileMapDrawer.pixelsToTiles(
                toY + sprite.getHeight() - 1);
        
        // check each tile for a collision
        for (int x=fromTileX; x<=toTileX; x++) {
            for (int y=fromTileY; y<=toTileY; y++) {
                if (x < 0 || x >= map.getWidth() ||
                        map.getTile(x, y) != null) {
                    // collision found, return the tile
                    pointCache.setLocation(x, y);
                    return pointCache;
                }
            }
        }
        // no collision found
        return null;
    }
    
    
    /**
     * Checks if two Sprites collide with one another. Returns
     * false if the two Sprites are the same. Returns false if
     * one of the Sprites is a Creature that is not alive.
     */
    public boolean isCollision(Sprite s1, Sprite s2) {
        // if the Sprites are the same, return false
        if (s1 == s2) {
            return false;
        }
        
        // if one of the Sprites is a dead Creature, return false
        if (s1 instanceof Creature && !((Creature)s1).isAlive()) {
            return false;
        }
        if (s2 instanceof Creature && !((Creature)s2).isAlive()) {
            return false;
        }
        
        // get the pixel location of the Sprites
        int s1x = Math.round(s1.getX());
        int s1y = Math.round(s1.getY());
        int s2x = Math.round(s2.getX());
        int s2y = Math.round(s2.getY());
        
        // check if the two sprites' boundaries intersect
        return (s1x < s2x + s2.getWidth() &&
                s2x < s1x + s1.getWidth() &&
                s1y < s2y + s2.getHeight() &&
                s2y < s1y + s1.getHeight());
    }
    
    
    /**
     * Gets the Sprite that collides with the specified Sprite,
     * or null if no Sprite collides with the specified Sprite.
     */
    public Sprite getSpriteCollision(Sprite sprite) {
        
        // run through the list of Sprites
        Iterator i = map.getSprites();
        while (i.hasNext()) {
            Sprite otherSprite = (Sprite)i.next();
            if (isCollision(sprite, otherSprite)) {
                // collision found, return the Sprite
                return otherSprite;
            }
        }
        
        // no collision found
        return null;
    }
    
    
    /**
     * Updates Animation, position, and velocity of all Sprites
     * in the current map.
     */
    public void update(long elapsedTime) {
        Creature player = (Creature)map.getPlayer();
        
        
        // player is dead! start map over
        if (player.getState() == Creature.STATE_DEAD) {
            map = mapLoader.reloadMap();
            return;
        }
        
        // get keyboard/mouse input
        checkInput(elapsedTime);
        
        // update player
        updateCreature(player, elapsedTime);
        player.update(elapsedTime);
        
        // update other sprites
        Iterator i = map.getSprites();
        while (i.hasNext()) {
            Sprite sprite = (Sprite)i.next();
            if (sprite instanceof Creature) {
                Creature creature = (Creature)sprite;
                if (creature.getState() == Creature.STATE_DEAD) {
                    i.remove();
                } else {
                    updateCreature(creature, elapsedTime);
                }
            }
            // normal update
            sprite.update(elapsedTime);
        }
    }
    
    
    /**
     * Updates the creature, applying gravity for creatures that
     * aren't flying, and checks collisions.
     */
    private void updateCreature(Creature creature,
            long elapsedTime) {
        
        // apply gravity
        if (!creature.isFlying()) {
            creature.setVelocityY(creature.getVelocityY() +
                    GRAVITY * elapsedTime);
        }
        
        // change x
        float dx = creature.getVelocityX();
        float oldX = creature.getX();
        float newX = oldX + dx * elapsedTime;
        Point tile =
                getTileCollision(creature, newX, creature.getY());
        if (tile == null) {
            creature.setX(newX);
        } else {
            // line up with the tile boundary
            if (dx > 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x) -
                        creature.getWidth());
            } else if (dx < 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x + 1));
            }
            creature.collideHorizontal();
        }
        if (creature instanceof Player) {
            checkPlayerCollision((Player)creature, false);
        }
        
        // change y
        float dy = creature.getVelocityY();
        float oldY = creature.getY();
        float newY = oldY + dy * elapsedTime;
        tile = getTileCollision(creature, creature.getX(), newY);
        if (tile == null) {
            creature.setY(newY);
        } else {
            // line up with the tile boundary
            if (dy > 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y) -
                        creature.getHeight());
            } else if (dy < 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y + 1));
            }
            creature.collideVertical();
        }
        if (creature instanceof Player) {
            boolean canKill = (oldY < creature.getY());
            checkPlayerCollision((Player)creature, canKill);
        }
        
    }
    
    
    /**
     * Checks for Player collision with other Sprites. If
     * canKill is true, collisions with Creatures will kill
     * them.
     */
    public void checkPlayerCollision(Player player,
            boolean canKill) {
        if (!player.isAlive()) {
            return;
        }
        
        // check for player collision with other sprites
        Sprite collisionSprite = getSpriteCollision(player);
        if (collisionSprite instanceof PowerUp) {
            acquirePowerUp((PowerUp)collisionSprite);
        } else if (collisionSprite instanceof Creature) {
            Creature badguy = (Creature)collisionSprite;
            if (canKill) {
                // kill the badguy and make player bounce
                badguy.setState(Creature.STATE_DYING);
                collectedStars++;
                if (collectedStars % 50 == 0) 
                {
                    numLives++;
                    // score goes on without deducting it from zero
                    //collectedStars=0;
                    try{
                        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/bonus.wav"));
                        Clip clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.start();
                    }
                    catch(Exception e){
                            e.printStackTrace();
                    }
                }
                try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/enemyDie.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                }
                catch(Exception e){
                        e.printStackTrace();
                }
                player.setY(badguy.getY() - player.getHeight());
                player.jump(true);
            } else {
                // player dies!
                player.setState(Creature.STATE_DYING);
                numLives--;
                collectedMission = 0;
                try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/disastah.wav"));

                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();		
		}
		catch(Exception e){
                    e.printStackTrace();
		}
                // Enter game over screen
                if(numLives == 0) {
                    drawer = new TileMapDrawer();
                    drawer.setBackground(mapLoader.loadImage("backYouDied.png"));
                    mapLoader.currentMap = -2;      //-2 is game over state
                    map = mapLoader.overMap();
                }
            }
        }
    }
    
    
    /**
     * Gives the player the speicifed power up and removes it
     * from the map.
     */
    public void acquirePowerUp(PowerUp powerUp) {
        // remove it from the map
        map.removeSprite(powerUp);
        
        if (powerUp instanceof PowerUp.Star) {
            // do something here, like give the player points
            collectedStars++;
            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/collect.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
            if (collectedStars % 50 == 0) 
            {
                numLives++;
                // score goes on without deducting it from zero
                //collectedStars=0;
                try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/bonus.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                }
                catch(Exception e){
                        e.printStackTrace();
                }
            }
            
        }else if (powerUp instanceof PowerUp.MissionKa) {
            // do something here, like give the player points
            collectedMission++;
            try{
                AudioInputStream ais2 = AudioSystem.getAudioInputStream
                    (new File("sounds/ka.wav"));
                Clip clip2 = AudioSystem.getClip();
                clip2.open(ais2);
                clip2.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
            
        } else if (powerUp instanceof PowerUp.MissionBa) {
            // do something here, like give the player points
            collectedMission++;
            try{
                AudioInputStream ais2 = AudioSystem.getAudioInputStream
                    (new File("sounds/ba.wav"));
                Clip clip2 = AudioSystem.getClip();
                clip2.open(ais2);
                clip2.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
            
        }else if (powerUp instanceof PowerUp.MissionYa) {
            // do something here, like give the player points
            collectedMission++;
            try{
                AudioInputStream ais2 = AudioSystem.getAudioInputStream
                    (new File("sounds/ya.wav"));
                Clip clip2 = AudioSystem.getClip();
                clip2.open(ais2);
                clip2.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
            
        }else if (powerUp instanceof PowerUp.MissionN) {
            // do something here, like give the player points
            collectedMission++;
            try{
                AudioInputStream ais2 = AudioSystem.getAudioInputStream
                    (new File("sounds/n.wav"));
                Clip clip2 = AudioSystem.getClip();
                clip2.open(ais2);
                clip2.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
            
        }else if (powerUp instanceof PowerUp.Goal) {
            // advance to next map if level < 4
            if( collectedMission == 4){
                currentLevel++;
                String query = "background" +currentLevel +".png";
                drawer = new TileMapDrawer();
                drawer.setBackground(mapLoader.loadImage(query));
                map = mapLoader.loadNextMap();
                collectedMission = 0;
            }
            // finsih the game when player reaches the end of level (level 4)
            else if(mapLoader.currentMap <5 && collectedMission != 4 ){
                map = mapLoader.reloadMap();
                numLives--;
                collectedMission = 0;
                try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/disastah.wav"));

                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();		
		}
		catch(Exception e){
                    e.printStackTrace();
		}
            } 
            else {
                // -1 is game finish state
                mapLoader.currentMap = -1;
                map = mapLoader.clearMap();
                try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/finish.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                drawer = new TileMapDrawer();
                drawer.setBackground(mapLoader.loadImage("backFinish.png"));
                // Advance to credit title
                if (mulai.isPressed() && mapLoader.currentMap == -1){
                    mapLoader.currentMap = 1;
                    // load resources
                    drawer.setBackground(mapLoader.loadImage("credit.jpg"));
                    map = mapLoader.loadNextMap();
                    // nunggu 5 detik terus otomatis keluar
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    stop();
                }
            }
        }
    }
}