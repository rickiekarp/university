package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung9.Zahlensaecke;

/**
 * Ein Zahlensack ist ein Datentyp, welcher eine gewisse Anzahl an
 * unterschiedlichen (!), zufaelligen Zahlen liefert. Die Zahlen liegen 
 * dabei immer zwischen 0 und Groesse-1, d.h. ein Zahlensack der Groesse 10 
 * liefert Zufallszahlen aus der Menge {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}.
 * 
 * Vorstellbar ist z.B. ein Zahlensack der Groesse 32, welcher immer
 * wieder neue Skatblaetter generiert. Denn: die ersten 32 gezogenen Zahlen
 * sind garantiert unterschiedlich, die naechsten 32 ebenfalls usw.
 * 
 * Ein frisch generierter Zahlensack ist immer leer!
 * 
 * @author Fredrik Winkler
 * @version Dezember 2009
 */
interface Zahlensack
{
    /**
     * @return die Groesse des Zahlensacks
     */
    public int gibGroesse();

    /**
     * @return die Anzahl der Zahlen, die sich momentan im Zahlensack befinden
     */
    public int gibAnzahl();
    
    /**
     * @return true, falls der Zahlensack leer ist
     */
    public boolean istLeer();
    
    /**
     * Entfernt eine Zahl aus dem Zahlensack und liefert sie zurueck.
     * Danach ist der Zahlensack um ein Element kleiner.
     * Ausnahme: Falls der Zahlensack vor dem Aufruf leer ist,
     * wird er zuerst gefuellt, und nach dem Aufruf ist gibAnzahl() == gibGroesse()-1.
     * 
     * @return eine zufaellige Zahl im halboffenen Intervall [0, gibGroesse)
     */
    public int entferneZahl();
    
    /**
     * Verwirft alle uebrig gebliebenen Zahlen aus dem Zahlensack.
     * istLeer() liefert anschliessend true zurueck, und gibAnzahl() liefert 0.
     */
    public void leereSack();
}
