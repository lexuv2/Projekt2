package game;
import javax.swing.*;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Swiat implements Serializable {
    private Vector<Organizm> spawn;
    private Deque<String> log;
    private Map<Pair, Organizm> organizmy;
    private int tura;
    private JTextArea logField;
    private boolean load_next_turn=false;
    JButton save_Button = new JButton("Zapisz");
    JButton load_Button = new JButton("Wczytaj");
    private JTextArea infoField;
    final int grid_size = 16;
    private int h;
    private int w;
    private String typ;
    
    GameUI ui;
    JFrame frame;
    SaveManager SM;
    public Swiat(String typString) {
        typ=typString;
        spawn = new Vector<Organizm>();
        log = new ArrayDeque<String>();
        organizmy = new HashMap<Pair, Organizm>();
        tura = 0;
    }

    public boolean loading()
    {
        return load_next_turn;
    }
    public String get_typ()
    {
        return typ;
    }

    public void add_spawn(int id, Organizm org) {

        while (spawn.size() <= id) {
            spawn.add(null);
        }
        spawn.set(id, org);

    }
    public Vector<Organizm> get_spawn() {
        return spawn;
    }
    public Vector<String> get_spawn_names()
    {
        Vector<String> ret=new Vector<String>();
        for(Organizm org:spawn)
        {
            ret.add(org.get_nazwa());
        }
        return ret;
    }

    public void spawn(int id, int x, int y) {
        if (spawn.get(id) == null)
            return;
        spawn.get(id).rozmnoz(x, y, this);
    }

    public void init(int a, int b) {
        h = a;
        w = b;

        SM = new SaveManager(this);
       
        SwingUtilities.isEventDispatchThread();
        frame = new JFrame("Projekt 2 Alekanser Iwicki 193354");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui = new GameUI(this, this.get_w(), this.get_h(), grid_size);
        
        // b.addKeyListener(new KeyChecker());
        logField = new JTextArea();
        logField.setEditable(false);
        logField.setSize(300, h*grid_size+grid_size);
        logField.setLocation(w*grid_size*2, 0);

        infoField = new JTextArea();
        infoField.setEditable(false);
        infoField.setSize(500, 30);
        infoField.setLocation(w*grid_size-120, h*grid_size+grid_size);

        

        save_Button.setBounds(0, grid_size * this.get_h()+grid_size, 100, 30);
        load_Button.setBounds(100, grid_size * this.get_h()+grid_size, 100, 30);
        save_Button.setActionCommand("save");
        load_Button.setActionCommand("load");
        save_Button.addActionListener(SM);
        load_Button.addActionListener(SM);
        frame.add(save_Button);
        frame.add(load_Button);
        frame.add(infoField);
        frame.add(logField);
        frame.add(ui);
        frame.pack();
        frame.setVisible(true);




    }

    public void wykonaj_ture() {
        tura++;
        if (load_next_turn)
        {
            load();
            rysuj();
            load_next_turn=false;
        }
        PriorityQueue<Organizm> q = new PriorityQueue<Organizm>(new Comparator<Organizm>() {
            @Override
            public int compare(Organizm o1, Organizm o2) {
                if (o1.get_inicjatywa() > o2.get_inicjatywa())
                    return -1;
                else if (o1.get_inicjatywa() < o2.get_inicjatywa())
                    return 1;
                else {
                    if (o1.get_wiek() > o2.get_wiek())
                        return -1;
                    else if (o1.get_wiek() < o2.get_wiek())
                        return 1;
                    else
                        return 0;
                }
            }
        });
        for (Entry<Pair, Organizm> entry : organizmy.entrySet()) {
            q.add(entry.getValue());
        }
        while (q.isEmpty() == false) {
            Organizm org = q.poll();
            if (org.get_nazwa() == "dead") {
                org.umrzyj();
                continue;
            }
            org.akcja();
        }
    }

    public void add_organizm(int x, int y, Organizm org) {
        organizmy.put(new Pair(x, y), org);
    }

    public void del_organizm(int x, int y) {
        organizmy.remove(new Pair(x, y));
    }

    public void add_log(String inp) {
        log.add(inp);
        if (log.size() > h) {
            log.removeFirst();
        }
        String log = "";
        for (String s : this.log) {
            log += s + "\n";
        }
        logField.setText(log);
    }

    public Boolean czy_puste(int x, int y) {
        return !organizmy.containsKey(new Pair(x, y));
    }

    public void rysuj() {
        ui.draw();
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Aleksander Iwicki 193354");
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                if (!czy_puste(j, i)) {
                    System.out.print(organizmy.get(new Pair(j, i)).get_znak());
                } else {
                    System.out.print(".");
                }

            }
            if (log.size() >= h - i - 1) {
                System.out.print(" " + log.toArray()[h - i - 1]);
            }
            System.out.println();
        }

    }
    public void setInfo(String inp)
    {
        infoField.setText(inp);
    }
    public Organizm at_pos(int x, int y) {
        return organizmy.get(new Pair(x, y));
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream("save.sav");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // create a new user object
          
            // write object to file
            oos.writeObject(organizmy);
            oos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

        /*
         * try (PrintWriter writer = new PrintWriter(file.toString())) {
         * writer.println(tura);
         * writer.println(h);
         * writer.println(w);
         * for (Entry<Pair, Organizm> entry : organizmy.entrySet()) {
         * writer.println(entry.getKey().first);
         * writer.println(entry.getKey().second);
         * writer.println(entry.getValue().get_sila());
         * writer.println(entry.getValue().get_inicjatywa());
         * writer.println(entry.getValue().get_wiek());
         * writer.println(entry.getValue().get_znak());
         * writer.println(entry.getValue().get_id());
         * }
         * } catch (Exception e) {
         * System.out.println("Nie udalo sie zapisac gry");
         * }
         */

    }
    public void load_next_turn()
    {
        load_next_turn=true;
    }

    private void load() {

        try {

            FileInputStream fileIn = new FileInputStream("save.sav");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            organizmy = (Map<Pair, Organizm>) objectIn.readObject();
            objectIn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        
    }

    public int get_h() {
        return h;
    }

    public int get_w() {
        return w;
    }

}
