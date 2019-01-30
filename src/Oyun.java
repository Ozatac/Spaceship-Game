
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
    

class Ates{
    
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    

    }
public class Oyun extends JPanel implements KeyListener,ActionListener{
    Timer timer =new Timer(5, this); // kaç saniyede bir çalışacagı,
    
    private int gecensure=0;
    private int harcananAtes=0;
    private BufferedImage image;
    
    private  ArrayList <Ates> atesler =new ArrayList<Ates>();
    private int atesdirY=1; // atesler hareket etmesi icin
    private int topX=0; // saga sola gitmeyi ayarlıyacak
    private int topdirX=2;
    private int uzayGemesiX=0; // uzay gemisi nereden baslayacak
    private int dirUzayX=20; // klavyeden sag bastıgımızda bunu ekleyecek saga kayacak
    
    public boolean kontrolEt(){
        for (Ates ates : atesler) {
            if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))) { // atesle daire carpisirsa
                return true;
            }
            
        }
        return false;
        
    }
    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("roket.png"))); // image okuyor.
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        gecensure+=5;
        
        g.setColor(Color.red);
        g.fillOval(topX, 0 , 20, 20);
        g.drawImage(image, uzayGemesiX, 490,image.getWidth()/4,image.getHeight()/4,this);
        for (Ates ates : atesler) {
            if (ates.getY() < 0) {
                atesler.remove(ates); // y ekseninden çıkarsa siliyoruz;
            }
        }
        g.setColor(Color.BLUE);
        for (Ates ates : atesler) {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        if (kontrolEt()) {
            timer.stop();
            String message="Kazandınız...\n"
                    + "Harcanan Ateş:"+ harcananAtes
                    + "\n Geçen Süre:" + gecensure/1000.0 + " Saniyedir";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() { // her çagırıldıgında paint çagırılıyor.
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        
        if (c== KeyEvent.VK_LEFT) {
            if (uzayGemesiX <= 0) {
                uzayGemesiX=0;
            }
            else{
                uzayGemesiX -= dirUzayX;
            }
                    
        }
        else if (c == KeyEvent.VK_RIGHT) {
            if (uzayGemesiX >=750) {
                uzayGemesiX =750;
            }
            else{
                uzayGemesiX += dirUzayX;
            }
        }
        else if (c == KeyEvent.VK_CONTROL) {
            atesler.add(new Ates(uzayGemesiX+15,470));
            harcananAtes++;
            // ateş olusturuyoruz ve arraya ekliyoruz.
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ates ates : atesler) {
            ates.setY(ates.getY() - atesdirY); // butun y kordinatları degisiyor;
        }
        // eylem gerçekleştirildiginde
        topX+=topdirX;
        
        if (topX >= 780) {
            topdirX=-topdirX; // geriye dogru gider
        }
        if (topX<=0) {
            topdirX =-topdirX;
        }
        
        repaint(); // her actionPerformed çalıştıgı zaman top hareket ediyor.
    }
    
}
