package game.zwierzeta;


import game.Pair;

public class Zolw extends game.Zwierze {
    public Zolw(int x,int y, game.Swiat swiat)
    {
        set_id(3);
        set_sila(2);
        set_inicjatywa(1);
        set_znak('Z');
        set_nazwa("Zolw");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void kolizja(game.Organizm kol)
    {
        if (kol.get_nazwa().equals("Zolw"))
        {
            Pair pt = losowe_puste_pole();
            rozmnoz(pt.first, pt.second,swiat);
        }
        else
        {
            if (this.get_sila() > kol.get_sila())
            {
                swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i wygrywa");
                kol.umrzyj();
            }
            else if (kol.get_sila() < 5)
            {
                swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i odpycha napastnika");
            }
            else
            {
                swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i przegrywa");
                swiat.del_organizm(get_x(), get_y());
                umrzyj();
                kol.przesun(get_x(), get_y());
            }
        }
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
         swiat.add_organizm(x, y, new Zolw(x,y,swiat));    
         swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void akcja()
    {
        if (this.get_nazwa().equals("dead"))umrzyj();
        this.increment_czas_zycia();
        int rnd = (int)(Math.random()%5);
        if (rnd == 0)
        {
            Pair pt = losowe_pole();
            if (swiat.czy_puste(pt.first, pt.second))
            {
                swiat.del_organizm(get_x(), get_y());
                swiat.add_organizm(pt.first, pt.second, this);
                this.set_x(pt.first);
                this.set_y(pt.second);
            }
            else
            {
                swiat.at_pos(pt.first, pt.second).kolizja(this);
            }
        }
    }


}
