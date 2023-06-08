package game;

import java.io.Console;
import java.io.Serializable;
import java.util.Random;

public abstract class Organizm implements Serializable {
    private int sila;
    private int inicjatywa;
    private int x;
    private int y;
    private int wiek;
    private char znak;
    protected Swiat swiat;
    private int id;
    private String nazwa;
    private int czas_zycia = 0;

    public void akcja() {

    }

    public void increment_czas_zycia() {
        czas_zycia++;
    }

    public void kolizja(Organizm org) {

    }

    public void rysuj() {
        System.out.print(znak);
    }

    public void set_sila(int inp) {
        sila = inp;
    }

    public void set_inicjatywa(int inp) {
        inicjatywa = inp;
    }

    public void set_x(int inp) {
        x = inp;
    }

    public void set_y(int inp) {
        y = inp;
    }

    public void set_wiek(int inp) {
        wiek = inp;
    }

    public void set_znak(char inp) {
        znak = inp;
    }

    public void set_swiat(Swiat inp) {
        swiat = inp;
    }

    public int get_sila() {
        return sila;
    }

    public int get_inicjatywa() {
        return inicjatywa;
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public int get_wiek() {
        return wiek;
    }

    public char get_znak() {
        return znak;
    }

    public Swiat get_swiat() {
        return swiat;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int inp) {
        id = inp;
    }

    public Pair losowe_pole() {
        int dx = 10000000;
        int dy = 10000000;
        if (swiat.get_typ().equals("Grid")) {
            while (x + dx >= swiat.get_w() || x + dx < 0 || y + dy >= swiat.get_h() || y + dy < 0
                    || (dx == 0 && dy == 0)) {
                Random rand = new Random();
                dx = (Math.abs(rand.nextInt()) % 3) - 1;
                dy = (Math.abs(rand.nextInt()) % 3) - 1;

            }
        }
        else
        {
            while (x + dx >= swiat.get_w() || x + dx < 0 || y + dy >= swiat.get_h() || y + dy < 0
                    || (dx == 0 && dy == 0) || (dx == 1 && dy == 1) || (dx == -1 && dy == -1)) {
                Random rand = new Random();
                dx = (Math.abs(rand.nextInt()) % 3) - 1;
                dy = (Math.abs(rand.nextInt()) % 3) - 1;

            }
        }
        return new Pair(x + dx, y + dy);
    }

    public Pair losowe_puste_pole() {
        Pair pt = losowe_pole();
        int i = 0;
        while (!swiat.czy_puste(pt.first, pt.second) && i < 100) {
            pt = losowe_pole();
            i++;
        }
        if (i >= 99) {
            pt.first = x;
            pt.second = y;
        }
        return pt;
    }

    public void umrzyj() {
        swiat.del_organizm(x, y);
    }

    public void przesun(int xx, int yy) {
        swiat.del_organizm(x, y);
        x = xx;
        y = yy;
        swiat.add_organizm(x, y, this);
    }

    public String to_save() {
        return Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(sila) + " "
                + Integer.toString(id);
    }

    public void rozmnoz(int a, int b, Swiat swiat) {
    }

    public String get_nazwa() {
        return nazwa;
    }

    public void set_nazwa(String inp) {
        nazwa = inp;
    }

}
