package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung14.Blatt14_Sortieren;

/**
 * Interface fuer int-Listen, die es ueber eine Operation 'vertausche'
 * sehr einfach ermoeglichen, 'in place' sortiert zu werden.
 * 
 * @author Petra Becker-Pechau, Fredrik Winkler, Axel Schmolitzky
 * @version WiSe 2013/14
 */
interface InPlaceSortierbareIntListe
{
    /**
     * Gib die Anzahl der Werte in der Liste zurueck.
     * 
     * @return die Anzahl der Werte in der Liste
     */
    public int gibLaenge();
    
    /**
     * Pruefe, ob der gegebene Index eine gueltige Position
     * in der Liste darstellt.
     * 
     * @param position die zu pruefende Position
     * @return true, falls (position >= 0) && (position < gibLaenge())
     */
    public boolean existiert(int position);
    
    /**
     * Gib den Wert an der angegebenen Position zurueck.
     * 
     * @param position die Position des int-Werts, der zurueckgegeben werden soll
     * @throws IndexOutOfBoundsException falls !existiert(position)
     * @return den Wert an der angegebenen Position
     */
    public int gib(int position);
    
    /**
     * Vertausche die beiden Elemente an den angegebenen Positionen in der Liste.
     * 
     * @param i die Position des ersten Elements
     * @param k die Position des zweiten Elements
     * @throws IndexOutOfBoundsException falls !(existiert(i) && existiert(k))
     */
    public void vertausche(int i, int k);
}
