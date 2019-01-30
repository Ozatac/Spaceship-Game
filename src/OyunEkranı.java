
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class OyunEkranı extends JFrame{

    public OyunEkranı(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        OyunEkranı ekran =new OyunEkranı("Uzay Oyunu"); // oyun ekranından obje oluştu;
        ekran.setResizable(false); // yeniden boyutlandırılabilir
        ekran.setFocusable(false); // odaklanabilir ayarlama
        ekran.setSize(800,600); //boyutu ayarlama 
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // varsayılan ayar işlemi
        
        Oyun oyun =new Oyun();
        oyun.requestFocus(); // istek  odak
        oyun.addKeyListener(oyun); //Anahtar Dinleyici ekle
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false); // jpanelin klavye işlemleri anlamsı için gerekli
        ekran.add(oyun);
        ekran.setVisible(true);
        
        
    }
}
