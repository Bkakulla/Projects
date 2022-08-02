import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    Panel p;
    public KeyHandler(Panel p){
        this.p=p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code= e.getKeyCode();

        if(code== KeyEvent.VK_ENTER){
            p.search();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
