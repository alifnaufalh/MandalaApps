package jar.game1.src.com.TETOSOFT.graphics; //package

import java.awt.Image; //library harus di cari buat apa

public class Sprite { // nama class Sprite

    protected Animation anim; //membuat variable
    private float x;      
    private float y;
    private float dx;
    private float dy;

    public Sprite(Animation anim){ //membuat konstruktor
        this.anim = anim;
    }   
 
    public void setX(float x){ //membuat fungsi mengeset x
        this.x = x;
    }
	
    public void setY(float y){ //membuat fungsi mengeset y
        this.y = y;
    }
	
	public void setVelocityX(float dx){
        this.dx = dx;
    }

    public void setVelocityY(float dy){
        this.dy = dy;
    }
	
	public float getX(){  //membuat fungsi mengambil x
        return x;
    }
    
    public float getY(){ //membuat fungsi mengambil y
        return y;
    }
		
    public float getVelocityX(){
        return dx;
    }

    public float getVelocityY(){
        return dy;
    }

	public Image getImage(){
        return anim.getImage();
    }
	
    public int getWidth(){
        return anim.getImage().getWidth(null);
    }
    
    public int getHeight(){
        return anim.getImage().getHeight(null);
    }

    public Object clone(){
        return new Sprite(anim);
    }
	
    public void update(long elapsedTime){
        x += dx * elapsedTime;
        y += dy * elapsedTime;
        anim.update(elapsedTime);
    }
}
