package game.zwierzeta;

import game.IsKeyPressed;
import game.Organizm;
import game.Pair;

public class Czlowiek extends game.Zwierze {
    boolean alive = true;
    boolean ult = false;
    int ultt = 5;
    int cool = 10;

    public Czlowiek(int x, int y, game.Swiat swiat) {
        set_id(-1);
        set_sila(5);
        set_inicjatywa(4);
        set_znak('8');
        set_nazwa("Czlowiek");
        set_x(x);
        set_y(y);
        set_swiat(swiat);
    }

    @Override
    public void kolizja(Organizm kol) {
        if (this.get_sila() > kol.get_sila()) {
            swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i wygrywa");
            kol.umrzyj();
        } else {
            swiat.add_log(get_nazwa() + "zostaje zaatakowany przez" + kol.get_nazwa() + "i przegrywa");
            swiat.del_organizm(get_x(), get_y());
            umrzyj();
            kol.przesun(get_x(), get_y());
        }
    }

    private void ultm() {
        int tposx = get_x();
        int tposy = get_y();
        tposx--;
        tposy--;

        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx -= 2;
        tposy++;

        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx += 2;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx -= 2;
        tposy++;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

        tposx++;
        if (!swiat.czy_puste(tposx, tposy))
            swiat.at_pos(tposx, tposy).umrzyj();

    }

    @Override
    public void akcja() {
        swiat = game.Projek2.swiat;
        if (this.get_nazwa().equals("dead"))
            umrzyj();
        this.increment_czas_zycia();
        if (!alive)
            return;

        swiat.rysuj();
        String info = "";
        // System.out.println("Rusz Człowieka(WSAD) (aby włączyć umiejętnośc kliknij e)
        // (aby zapisac wcisnij z):");
        info += "Rusz Człowieka(WSAD) (aby włączyć umiejętnośc kliknij e): \n";
        if (ult) {
            ultt--;
            if (ultt <= 0) {
                ult = false;
                ultt = 5;
            } else {
                // System.out.println(String.format("[Całopalenie aktywne] [ %d ]", ultt));
                info += String.format("[Całopalenie aktywne] [ %d ] \n", ultt);
            }

        } else if (cool > 5) {
            // System.out.println("[Całopalenie dostępne]");
            info += "[Całopalenie dostępne] \n";
        } else {
            cool++;
        }
        swiat.setInfo(info);
        Pair pt = new Pair(get_x(), get_y());
        char inp = 'a';// System.console().readLine().charAt(0);
        while (true) {
            if (swiat.loading()) {
                return;
            }
            if (swiat.get_typ().equals("Grid")) {
                if (IsKeyPressed.isWJustPressed() && get_y() > 0) {
                    pt.second--;
                    break;
                } else if (IsKeyPressed.isSJustPressed() && get_y() < swiat.get_h() - 1) {
                    pt.second++;
                    break;
                } else if (IsKeyPressed.isAJustPressed() && get_x() > 0) {
                    pt.first--;
                    break;
                } else if (IsKeyPressed.isDJustPressed() && get_x() < swiat.get_w() - 1) {
                    pt.first++;
                    break;
                }
            }
            else
            {
                //hex movement
                if (IsKeyPressed.isQJustPressed() && get_y()>0)
                {
                    pt.second--;
                    break;
                }
                if (IsKeyPressed.isWJustPressed() && get_y()>0 && get_x()<swiat.get_w()-1)
                {
                    pt.second--;
                    pt.first++;
                    break;
                }
                if (IsKeyPressed.isAJustPressed() && get_x()>0)
                {
                    pt.first--;
                    break;
                }
                if (IsKeyPressed.isSJustPressed() && get_x() < swiat.get_w()-1)
                {
                    pt.first++;
                    break;
                }
                if (IsKeyPressed.isZJustPressed() && get_x() > 0 && get_y() < swiat.get_h()-1)
                {
                    pt.first--;
                    pt.second++;
                    break;
                }
                if (IsKeyPressed.isXJustPressed() && get_y() < swiat.get_h()-1)
                {
                    pt.second++;
                    break;
                }
                
            }
            if (IsKeyPressed.isEJustPressed()) {
                if (!ult && cool > 5) {
                    ult = true;
                    cool = 0;

                    info = "";
                    info += "Rusz Człowieka(WSAD) (aby włączyć umiejętnośc kliknij e): \n";
                    info += String.format("[Całopalenie aktywne] [ %d ] \n", ultt);
                    swiat.setInfo(info);
                    ultm();
                }
            }

            // inp = System.console().readLine().charAt(0);
        }
        if (ult) {
            ultm();
            swiat.rysuj();
        }
        if (swiat.czy_puste(pt.first, pt.second)) {
            swiat.del_organizm(get_x(), get_y());
            swiat.add_organizm(pt.first, pt.second, this);
            this.set_x(pt.first);
            this.set_y(pt.second);
        } else {
            swiat.at_pos(pt.first, pt.second).kolizja(this);
        }
        if (ult) {
            ultm();
            // swiat.rysuj();
        }
        swiat.rysuj();

    }

}
