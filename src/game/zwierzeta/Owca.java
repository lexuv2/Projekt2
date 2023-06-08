package game.zwierzeta;

import game.Pair;

public class Owca extends game.Zwierze {
    public Owca(int x,int y, game.Swiat swiat)
    {
        set_id(1);
        set_sila(4);
        set_inicjatywa(4);
        set_znak('O');
        set_nazwa("Owca");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Owca(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }

    @Override
    public void kolizja(game.Organizm kol)
    {
        if (kol.get_nazwa().equals("Owca"))
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
