package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung13.Blatt13_Hashing;

/**
 * Dieses Interface definiert die Schnittstelle von Objekten, die 
 * einen Hash-Wert fuer ein gegebenes Wort (einen String) liefern.
 * Diese Werte koennen als Basis fuer Hash-Funktionen dienen.
 * 
 * Verschiedene Implementationen dieses Interfaces koennen Hash-Werte
 * unterschiedlicher Guete realisieren.
 * 
 * @author Fredrik Winkler, Petra Becker-Pechau, Axel Schmolitzky
 * @version September 2011
 */
public interface HashWertBerechner
{
    /**
     * Bilde das gegebene Wort auf einen ganzzahligen Wert ab.
     */
    public int hashWert(String wort);
    
    /**
     * Liefert eine Beschreibung fuer die Art der Berechnung.
     */
    public String gibBeschreibung();
}
