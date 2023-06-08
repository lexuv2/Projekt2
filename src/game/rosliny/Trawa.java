package game.rosliny;

public class Trawa extends game.Roslina {
    public Trawa(int x,int y, game.Swiat swiat)
    {
        set_id(6);
        set_sila(0);
        set_inicjatywa(0);
        set_znak('T');
        set_nazwa("Trawa");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }
    @Override
    public void rozmnoz(int x,int y, game.Swiat swiat)
    {
       swiat.add_organizm(x, y, new Trawa(x,y,swiat));    
       swiat.add_log(get_nazwa() + " rozmnaża sie na polu " + x + " " + y);
    }

}
