package game.zwierzeta;

import game.Pair;

public class Wilk extends game.Zwierze {
    public Wilk(int x , int y, game.Swiat swiat)
    {
        set_id(0);
        set_sila(9);
        set_inicjatywa(5);
        set_znak('W');
        set_nazwa("Wilk");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Wilk(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void kolizja(game.Organizm kol)
    {
        if (kol.get_nazwa().equals("Wilk"))
        {
            Pair pt = losowe_puste_pole();
            rozmnoz(pt.first, pt.second, swiat);
        }
        else
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
    }

}
