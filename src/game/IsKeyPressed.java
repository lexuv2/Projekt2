package game;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class IsKeyPressed implements Serializable{
    private static volatile boolean wPressed = false;
    private static volatile boolean aPressed = false;
    private static volatile boolean sPressed = false;
    private static volatile boolean dPressed = false;
    private static volatile boolean wJustPressed = false;
    private static volatile boolean aJustPressed = false;
    private static volatile boolean sJustPressed = false;
    private static volatile boolean dJustPressed = false;
    private static volatile boolean ePressed = false;
    private static volatile boolean eJustPressed = false;
    private static volatile boolean qPressed = false;
    private static volatile boolean zPressed = false;
    private static volatile boolean xPressed = false;
    private static volatile boolean qJustPressed = false;
    private static volatile boolean zJustPressed = false;
    private static volatile boolean xJustPressed = false;

    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }

    public static boolean isAPressed() {
        synchronized (IsKeyPressed.class) {
            return aPressed;
        }
    }

    public static boolean isSPressed() {
        synchronized (IsKeyPressed.class) {
            return sPressed;
        }
    }

    public static boolean isDPressed() {
        synchronized (IsKeyPressed.class) {
            return dPressed;
        }
    }

    public static boolean isEPressed() {
        synchronized (IsKeyPressed.class) {
            return ePressed;
        }
    }

    public static boolean isQPressed() {
        synchronized (IsKeyPressed.class) {
            return qPressed;
        }
    }

    public static boolean isZPressed() {
        synchronized (IsKeyPressed.class) {
            return zPressed;
        }
    }

    public static boolean isXPressed() {
        synchronized (IsKeyPressed.class) {
            return xPressed;
        }
    }


    public static boolean isWJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (wJustPressed) {
                wJustPressed = false;
                return true;
            } else
                return false;
        }
    }


    public static boolean isAJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (aJustPressed) {
                aJustPressed = false;
                return true;
            } else
                return false;
        }
    }

    public static boolean isSJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (sJustPressed) {
                sJustPressed = false;
                return true;
            } else
                return false;
        }
    }

    public static boolean isDJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (dJustPressed) {
                dJustPressed = false;
                return true;
            } else
                return false;
        }
    }
    public static boolean isEJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (eJustPressed) {
                eJustPressed = false;
                return true;
            } else
                return false;
        }
    }

    public static boolean isQJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (qJustPressed) {
                qJustPressed = false;
                return true;
            } else
                return false;
        }
    }

    public static boolean isZJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (zJustPressed) {
                zJustPressed = false;
                return true;
            } else
                return false;
        }
    }

    public static boolean isXJustPressed() {
        synchronized (IsKeyPressed.class) {
            if (xJustPressed) {
                xJustPressed = false;
                return true;
            } else
                return false;
        }
    }


    public void init() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                if (!wPressed)
                                    wJustPressed = true;
                                else
                                    wJustPressed = false;
                                wPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_A) {
                                if (!aPressed)
                                    aJustPressed = true;
                                else
                                    aJustPressed = false;

                                aPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_S) {

                                if (!sPressed)
                                    sJustPressed = true;
                                else
                                    sJustPressed = false;
                                sPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_D) {
                                if (!dPressed)
                                    dJustPressed = true;
                                else
                                    dJustPressed = false;
                                dPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_E) {
                                if (!ePressed)
                                    eJustPressed = true;
                                else
                                    eJustPressed = false;
                                ePressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_Q) {
                                if (!qPressed)
                                    qJustPressed = true;
                                else
                                    qJustPressed = false;
                                qPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_Z) {
                                if (!zPressed)
                                    zJustPressed = true;
                                else
                                    zJustPressed = false;
                                zPressed = true;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_X) {
                                if (!xPressed)
                                    xJustPressed = true;
                                else
                                    xJustPressed = false;
                                xPressed = true;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_S) {
                                sPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_D) {
                                dPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_E) {
                                ePressed = false;
                            }

                            if (ke.getKeyCode() == KeyEvent.VK_Q) {
                                qPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_Z) {
                                zPressed = false;
                            }
                            if (ke.getKeyCode() == KeyEvent.VK_X) {
                                xPressed = false;
                            }
                            
                            break;
                    }

                    return false;
                }
            }
        });
    }
}