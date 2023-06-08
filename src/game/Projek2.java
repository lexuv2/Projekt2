package game;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;
import game.zwierzeta.*;
import game.rosliny.*;
import javax.swing.*;

public class Projek2 {
    public static Swiat swiat;

    public static void main(String[] args) throws Exception {
        // set random seed
        
        Random rand = new Random(193354);
        swiat = new Swiat("Grid");

        swiat.add_spawn(0, new Wilk(0, 0, swiat));
        swiat.add_spawn(1, new Owca(0, 0, swiat));
        swiat.add_spawn(2, new Lis(0, 0, swiat));
        swiat.add_spawn(3, new Zolw(0, 0, swiat));
        swiat.add_spawn(4, new Antylopa(0, 0, swiat));
        swiat.add_spawn(5, new Owca(0, 0, swiat));
        swiat.add_spawn(6, new Trawa(0, 0, swiat));
        swiat.add_spawn(7, new Mlecz(0, 0, swiat));
        swiat.add_spawn(8, new Guarana(0, 0, swiat));
        swiat.add_spawn(9, new WilczeJagody(0, 0, swiat));
        swiat.add_spawn(10, new BarszczSosnowskiego(0, 0, swiat));

        /*
         * System.out.println("Jesli chcesz załadować gre wciśnij l");
         * char c;
         * c = (char) System.console().readLine().charAt(0);
         * if (c == 'l') {
         * // swiat.add_organizm(pt.first, pt.second,new
         * // Czlowiek(pt.first,pt.second,swiat));
         * 
         * } else {/*
         */
        JFrame f1 = new JFrame();
        String size_input = JOptionPane.showInputDialog(f1, "Podaj rozmiar planszy", "Rozmiar planszy",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println("Podaj rozmiar planszy");
        int n, m;
        //n = Integer.parseInt(System.console().readLine());
        //m = Integer.parseInt(System.console().readLine());

        n = Integer.parseInt(size_input);
        m = n;

        swiat.init(n, m);

        swiat.add_organizm(1, 1, new Czlowiek(1, 1, swiat));

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j < 5; ++j) {
                int xx = rand.nextInt(n);
                int yy = rand.nextInt(m);
                swiat.spawn(i, xx, yy);
            }
        }

        
        swiat.rysuj();
        while (true) {
            swiat.wykonaj_ture();
            swiat.rysuj();
           
        }

    }
}
