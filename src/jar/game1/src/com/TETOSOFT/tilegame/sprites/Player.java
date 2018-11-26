package jar.game1.src.com.TETOSOFT.tilegame.sprites;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import jar.game1.src.com.TETOSOFT.graphics.Animation;

/**
    The Player.
*/
public class Player extends Creature 
{

    private static final float JUMP_SPEED = -.95f;

    private boolean onGround;
    
    public Player(Animation left, Animation right, Animation deadLeft, Animation deadRight)
    {
        super(left, right, deadLeft, deadRight);
    }


    public void collideHorizontal() {
        setVelocityX(0);
    }


    public void collideVertical() {
        // check if collided with ground
        if (getVelocityY() > 0) {
            onGround = true;
        }
        setVelocityY(0);
    }


    public void setY(float y) {
        // check if falling
        if (Math.round(y) > Math.round(getY())) {
            onGround = false;
        }
        super.setY(y);
    }


    public void wakeUp() {
        // do nothing
    }


    /**
        Makes the player jump if the player is on the ground or
        if forceJump is true.
    */
    public void jump(boolean forceJump) {
        if (onGround || forceJump) {
            onGround = false;
            setVelocityY(JUMP_SPEED);
            try{
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/jump.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);

                    clip.start();
            }
            catch(Exception e){
                    e.printStackTrace();
            }
        }
    }


    public float getMaxSpeed() {
        return 0.5f;
    }

}
