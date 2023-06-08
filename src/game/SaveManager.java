package game;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.swing.*;    


public class SaveManager implements Serializable , ActionListener  {
    Swiat swiat;
    
    public SaveManager(Swiat sw)
    {
        swiat=sw;
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "load")
        {
            /*try {

                FileInputStream fileIn = new FileInputStream("save.sav");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                Projek2.swiat. = (Swiat) objectIn.readObject();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Projek2.swiat.rysuj();*/
            Projek2.swiat.load_next_turn();

        }
        else
        {
            swiat.save();
        }
    }
}
