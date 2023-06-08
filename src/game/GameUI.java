package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GameUI extends JPanel implements MouseListener {

    int w;
    int h;
    Swiat swiat;
    int grid_size = 16;
    IsKeyPressed keyListener = new IsKeyPressed();

    public GameUI(Swiat inp, int ww, int hh, int gs) {
        keyListener.init();
        swiat = inp;
        w = ww;
        h = hh;
        grid_size = gs;
        this.addMouseListener(this);
        // setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public Dimension getPreferredSize() {
        return new Dimension(grid_size * w * 2 + 300, grid_size * h + 50);
    }

    public void draw() {
        repaint(0, 0, w * grid_size * 2, h * grid_size + grid_size);
    }

    protected void paintComponent(Graphics g) {
        if (swiat.get_typ().equals("Grid")) {
            super.paintComponent(g);
            for (int i = 0; i < w; ++i) {
                for (int j = 0; j <= h; ++j) {
                    Organizm org = Projek2.swiat.at_pos(i, j - 1);
                    g.setColor(Color.gray);
                    if (j != h)
                        g.drawRect(i * grid_size, j * grid_size, grid_size, grid_size);

                    if (org != null) {
                        g.setColor(Color.black);
                        g.drawString(String.valueOf(org.get_znak()), i * grid_size + 2, j * grid_size - 2);
                    }

                }
            }
        } else {
            super.paintComponent(g);
            for (int i = 0; i < w; ++i) {
                for (int j = 0; j <= h; ++j) {
                    Organizm org = Projek2.swiat.at_pos(i, j - 1);
                    g.setColor(Color.gray);
                    // g.drawRect(i * grid_size, j * grid_size, grid_size, grid_size);
                    // draw hex

                    int x = i * 15 * grid_size / 12 + j * grid_size / 2 + grid_size;
                    int y = j * grid_size + grid_size;
                    int gs = 2 * grid_size / 3;
                    int[] xpoints = { x - gs, x - gs, x, x + gs, x + gs, x };
                    int[] ypoints = { y - gs / 2, y + gs / 2, y + gs, y + gs / 2, y - gs / 2, y - gs };
                    if (j != h)
                        g.drawPolygon(xpoints, ypoints, 6);

                    if (org != null) {
                        g.setColor(Color.black);
                        g.drawString(String.valueOf(org.get_znak()), x - gs, y - gs);
                        // g.drawRect(x, y, 2, 2);
                    }

                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        swiat = Projek2.swiat;
        int x, y;

        if (swiat.get_typ().equals("Grid")) {
            x = e.getX() / grid_size;
            y = e.getY() / grid_size;
        } else {

            y = (e.getY()-8) / (grid_size);
            x = (e.getX() - e.getY()/2) / (grid_size * 15 / 12);
        }

        if (!swiat.czy_puste(x, y)) {
            return;
        }
        JFrame frame = new JFrame("frame");
        /*
         * JFrame f = new JFrame("frame");
         * JDialog d = new JDialog(f, "dialog Box");
         * 
         * // create a label
         * JLabel l = new JLabel("this is a dialog box");
         * 
         * d.add(l);
         * 
         * // setsize of dialog
         * d.setSize(100, 100);
         * 
         * // set visibility of dialog
         * d.setVisible(true);
         */

        Object[] options = swiat.get_spawn_names().toArray();

        int n = JOptionPane.showOptionDialog(frame,
                "Wybierz orgaznim który chcesz stworzyć",
                "Tworzenie organizmu",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        System.out.println(n);
        if (n == -1) {
            return;
        }
        swiat.spawn(n, x, y);
        draw();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }
}
