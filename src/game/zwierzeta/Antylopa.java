package game.zwierzeta;

import game.Pair;

public class Antylopa extends game.Zwierze {
    public Antylopa(int x,int y,game.Swiat swiat)
    {
        set_id(4);
        set_sila(4);
        set_inicjatywa(4);
        set_znak('A');
        set_nazwa("Antylopa");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Antylopa(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void kolizja(game.Organizm kol)
    {
        if (kol.get_nazwa().equals("Antylopa"))
        {
            Pair pt = losowe_puste_pole();
            rozmnoz(pt.first, pt.second, swiat);
        }
        else
        {
            int rnd = (int)(Math.random()%2);
            if (rnd == 0)
            {
                if (this.get_sila() > kol.get_sila())
                {
                    swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i wygrywa");
                    kol.umrzyj();
                }
                else
                {
                    swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i przegrywa");
                    swiat.del_organizm(get_x(), get_y());
                    umrzyj();
                    kol.przesun(get_x(), get_y());
                }
            }
            else
            {
                swiat.add_log(get_nazwa() + "ucieka przed" + kol.get_nazwa());
                Pair pt = losowe_puste_pole();
                przesun(pt.first, pt.second);
            }
        }
    }


    @Override
    public void akcja()
    {
        if (this.get_nazwa().equals("dead"))umrzyj();
        this.increment_czas_zycia();
        Pair pt = losowe_pole();

        if (swiat.czy_puste(pt.first , pt.second))
        {
            swiat.del_organizm(get_x(), get_y());
            swiat.add_organizm(pt.first, pt.second, this);
            this.set_x(pt.first);
            this.set_y(pt.second);
            pt = losowe_puste_pole();
            if (swiat.czy_puste(pt.first , pt.second))
            {
                swiat.del_organizm(get_x(), get_y());
                swiat.add_organizm(pt.first, pt.second, this);
                this.set_x(pt.first);
                this.set_y(pt.second);
            }
        }
        else
        {
            swiat.at_pos(pt.first, pt.second).kolizja(this);
        }
    }
}
