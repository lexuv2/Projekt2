package game.rosliny;

public class WilczeJagody extends game.Roslina {
    public WilczeJagody(int x,int y, game.Swiat swiat)
    {
        set_id(9);
        set_sila(99);
        set_inicjatywa(0);
        set_znak('J');
        set_nazwa("WilczeJagody");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new WilczeJagody(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }
    @Override
    public void kolizja(game.Organizm kol)
    {
        swiat.add_log(get_nazwa() + "Zatruwa" + kol.get_nazwa());
        umrzyj();
        kol.umrzyj();
       
    }
    
}
