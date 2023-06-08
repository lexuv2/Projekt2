package game.rosliny;

public class BarszczSosnowskiego extends game.Roslina {
    public BarszczSosnowskiego(int  x,int y,game.Swiat swiat)
    {
        set_id(10);
        set_sila(99);
        set_inicjatywa(0);
        set_znak('B');
        set_nazwa("BarszczSosnowskiego");
        set_x(x);
        set_y(y);
        set_swiat(swiat);


    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new BarszczSosnowskiego(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void kolizja(game.Organizm kol)
    {
        if (kol.get_nazwa().equals("CyberOwca"))
        {
            swiat.add_log(get_nazwa()+ "Zostaje zjedzony przez" + kol.get_nazwa());
            umrzyj();
            kol.przesun(get_x(), get_y());
        }
        else
        {
        swiat.add_log(get_nazwa() + "Zatruwa" + kol.get_nazwa());
        umrzyj();
        kol.umrzyj();
        }
       
    }
    @Override
    public void akcja()
    {
        int tposx=get_x();
        int tposy=get_y();
        tposx--;
        tposy--;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx-=2;
        tposy++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx+=2;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx-=2;
        tposy++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
        if (swiat.at_pos(tposx, tposy).get_nazwa() != "CyberOwca" && swiat.at_pos(tposx, tposy) instanceof game.Zwierze)
        {
            swiat.add_log(get_nazwa() + "Zatruwa" + swiat.at_pos(tposx, tposy).get_nazwa());
            swiat.at_pos(tposx, tposy).umrzyj();
        }

    }
}
