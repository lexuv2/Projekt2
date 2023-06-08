package game;

public class Zwierze extends Organizm {
    public void akcja()
    {
        if (this.get_nazwa() == "dead")umrzyj();
        this.increment_czas_zycia();
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
