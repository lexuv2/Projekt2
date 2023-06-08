package game.rosliny;

public class Guarana extends game.Roslina {
    public Guarana(int x,int y, game.Swiat swiat)
    {
        set_id(8);
        set_sila(0);
        set_inicjatywa(0);
        set_znak('G');
        set_nazwa("Guarana");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Guarana(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }

    @Override
    public void kolizja(game.Organizm kol)
    {
        swiat.add_log(get_nazwa() + "zostaje zjedzona przez" + kol.get_nazwa());
        kol.set_sila(kol.get_sila() + 3);
        umrzyj();
        kol.przesun(get_x(), get_y());
    }
}
