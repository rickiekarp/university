package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung9.Zahlensaecke;

/**
 * Diese Klasse vergleicht die Effizienz verschiedener Implementationen von Zahlensack.
 *
 * Implementiert in der Methode vermesse(Zahlensack) den fehlenden Code!
 *
 * @author Fredrik Winkler, Christian M. Spaeh
 * @version Dezember 2013
 */
public class Effizienzvergleicher
{
    /**
     * Vergleicht die Effizienz verschiedener Implementationen von Zahlensack.
     * Alle Zahlensaecke werden mit der gleichen Groesse erzeugt.
     * 
     * @param groesse die Groesse der Zahlensaecke
     */
    public void vergleiche(int groesse)
    {
        if (groesse < 1)
        {
            throw new IllegalArgumentException("groesse < 1");
        }
        
        Zahlensack zs = new Naiv(groesse);
        vermesse(zs);
        
        zs = new Permutation(groesse);
        vermesse(zs);
        
        zs = new Auswahl(groesse);
        vermesse(zs);
    }

    /**
     * Misst die Zeit, die fuer das Entfernen von sehr vielen Zahlen aus dem
     * Zahlensack benoetigt wird. Das Ergebnis wird auf der Konsole ausgegeben.
     * 
     * @param sack der zu vermessende Zahlensack
     */
    public void vermesse(Zahlensack sack)
    {
        // Speichere die aktuelle Zeit als Startzeit
        long startZeit = System.nanoTime();

        // Rufe 1 Mio. Mal "entferneZahl" auf
        for (int i = 0; i < 1000000; i++)
        {
            sack.entferneZahl();
        }

        // Speichere die aktuelle Zeit als Stoppzeit
        long endZeit = System.nanoTime();

        // Bilde die Differenz aus Stoppzeit und Startzeit
        long diff = endZeit - startZeit;

        // Teile die Differenz durch 1000000, um von ns nach ms umzurechnen
        diff = diff / 1000000;

        System.out.print(sack); // Beschreibung des Zahlensacks ausgeben
        System.out.print(": "); // gefolgt von einem Doppelpunkt
        // Gib das Ergebnis auf der Konsole aus
        System.out.println(diff + "ms");
    }
}
