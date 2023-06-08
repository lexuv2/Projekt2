package game.rosliny;

import game.Pair;

public class Mlecz extends game.Roslina {
    public Mlecz(int x,int y, game.Swiat swiat)
    {
        set_id(7);
        set_sila(0);
        set_inicjatywa(0);
        set_znak('M');
        set_nazwa("Mlecz");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Mlecz(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void akcja()
    {
        for (int i=0;i<3;++i)
        {
            int rnd = (int)(Math.random()%100);
            {
                if (rnd < this.get_szansa_na_rozmnozenie())
                {
                    Pair pt = losowe_puste_pole();
                    if (pt.first == this.get_x() && pt.second == this.get_y())
                        continue;
                    rozmnoz(pt.first, pt.second, swiat);
                }
            }
        }
    }
}
