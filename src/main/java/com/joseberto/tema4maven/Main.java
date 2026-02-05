package com.joseberto.tema4maven;

import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        String s = "JOSE  BERTÓ";
        ArrayList<String> lista = new ArrayList<>();

        String banner = "";
        try {

            banner = FigletFont.convertOneLine(s);
            
            System.out.println(banner);
        } catch (IOException IO) {
        }

        lista.add(banner);
        lista.add("Tienda comics");
        lista.add("Lavado de coches");
        lista.add("Media Markt");
        lista.add("Tienda comida animal");
        lista.add("Atención al cliente empresa café");
        lista.add("Dietista");
        lista.add("IDIOMA INGLÉS - INTERMEDIO");
        lista.add("IDIOMA PORTUGUÉS - BÁSICO");
        lista.add("IDIOMA ALEMÁN - MUY BÁSICO");
        lista.add("Estudiando DAW");


        Screen screen = null;
        try {


            screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            int height = screen.getTerminalSize().getRows();
            int yOffset = height;

            while (yOffset > -lista.size()) { //bucle de impresion

                drawFrame(screen, lista, yOffset);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }

                yOffset--;
            }

            screen.stopScreen();


        } catch (IOException IO) {
        }
    }
private static void drawFrame(Screen screen, List<String> lines, int yOffset)

        throws IOException {
    TerminalSize size = screen.getTerminalSize();
    int width = size.getColumns();
    int height = size.getRows();
    screen.clear();
    TextGraphics tg = screen.newTextGraphics();
    for (int i = 0; i < lines.size(); i++) {
        int y = yOffset + i;
        if (y < 0 || y >= height) continue;
        String line = lines.get(i);
// Centrado horizontal (opcional, pero queda mejor)
        int x = Math.max(0, (width - line.length()) / 2);
        if (x >= width) continue;
// Recorte simple si se sale por la derecha
        String visible = (line.length() > width) ? line.substring(0, width) :
                line;
        tg.putString(x, y, visible);
    }
    screen.refresh();
}
}