package com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung9.Informatiker;

/**
 * Ein Vergleicher ist in der Lage, zwei Personen miteinander zu vergleichen.
 * Welches Vergleichskriterium dabei verwendet wird, kann in jeder Klasse anders festgelegt werden.
 * 
 * @author Fredrik Winkler
 * @version 8. Dezember 2014
 */
interface Vergleicher
{
    /**
     * Vergleicht zwei Personen miteinander und gibt Auskunft darueber, in welcher Beziehung sie zueinander stehen.
     * Wenn die beiden Personen (gemessem am Vergleichskriterium) gleich sind, dann wird 0 geliefert.
     * Wenn a in einer geordneten Liste vor b stehen wuerde, dann wird irgendeine negative Zahl geliefert.
     * Ansonsten wird irgendeine positive Zahl geliefert.
     */
    public int vergleiche(Person a, Person b);
}
