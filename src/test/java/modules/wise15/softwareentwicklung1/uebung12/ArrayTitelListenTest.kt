package modules.wise15.softwareentwicklung1.uebung12

import com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes.ArrayTitelListe
import com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes.Titel
import com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes.TitelBibliothek
import com.rkarp.uni.modules.wise15.softwareentwicklung1.uebung12.Blatt12_SE1Tunes.TitelListe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

/**
 * JUnit-Test fuer die Klasse ArrayTitelListe.
 *
 * @author Till Aust
 * @author Axel Schmolitzky
 * @author Petra Becker-Pechau
 * @author Fredrik Winkler
 * @version 8. Januar 2015
 */
internal class ArrayTitelListenTest {
    private val _testTitel: Array<Titel>

    init {
        val bibliothek = TitelBibliothek("JazzMix.txt")
        _testTitel = bibliothek.gibZufaelligeTitel(10)
    }

    /**
     * Testet die Methode enthaelt(String) der Liste.
     */
    @Test
    fun testeEnthaelt() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }

        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[0]), "titelListe soll den Test-Titel 0 enthalten")
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[2]), "titelListe soll den Test-Titel 2 enthalten")
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[9]), "titelListe soll den Test-Titel 9 enthalten")

        val falscherTitel = Titel("", "", "", 0, "", 0)
        Assertions.assertFalse(
                titelListe.enthaelt(falscherTitel),
                "titelListe darf nicht den Titel 'falscherTitel' enthalten")
    }

    /**
     * Testet, ob zwei Titel in der Methode enthaelt(String) mit equals verglichen
     * werden.
     */
    @Test
    fun testetEqualsVerwendungInEnthaelt() {
        val titelListe = erzeugeListe()
        val titel = Titel("At Saturday", "Esbjoern Svensson Trio", "Winter in Venice", 1999, "Jazz", 374)
        val gleicherTitel = Titel("At Saturday", "Esbjoern Svensson Trio", "Winter in Venice", 1999, "Jazz", 374)
        titelListe.fuegeEin(titel, 0)

        Assertions.assertTrue(titelListe.enthaelt(gleicherTitel), "titelListe soll gleicherTitel enthalten")
    }

    /**
     * Testet die Methode gibLaenge der Liste.
     */
    @Test
    fun testeGibLaenge() {
        val titelListe = erzeugeListe()
        assertEquals(0, titelListe.gibLaenge())

        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
            assertEquals(i + 1, titelListe.gibLaenge())
        }

        assertEquals(10, titelListe.gibLaenge())
    }

    /**
     * Testet die Methode fuegeEin(Titel, int) der Liste. Dabei wird getestet,
     * ob sich Elemente mittig in die Liste einfuegen lassen.
     */
    @Test
    fun testeFuegeEinMittig() {
        val titelListe = erzeugeListe()
        for (i in 0..8) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        titelListe.fuegeEin(_testTitel[9], 5)
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[9]), "titelListe soll den Test-Titel 9 enthalten")
        assertEquals(_testTitel[9], titelListe.gibTitel(5))
        assertEquals(_testTitel[4], titelListe.gibTitel(4))
        assertEquals(_testTitel[5], titelListe.gibTitel(6))
        assertEquals(_testTitel[8], titelListe.gibTitel(9))
    }

    /**
     * Testet die Methode fuegeEin(Titel, int) der Liste. Dabei wird getestet,
     * ob sich Elemente am Anfang in die Liste einfuegen lassen.
     */
    @Test
    fun testeFuegeEinListenanfang() {
        val titelListe = erzeugeListe()
        for (i in 0..8) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        titelListe.fuegeEin(_testTitel[9], 0)
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[9]), "titelListe soll den Test-Titel 9 enthalten")
        assertEquals(_testTitel[9], titelListe.gibTitel(0))
        assertEquals(_testTitel[0], titelListe.gibTitel(1))
        assertEquals(_testTitel[8], titelListe.gibTitel(9))
    }

    /**
     * Testet die Methode fuegeEin(String, int) der Liste. Dabei wird getestet,
     * ob sich Elemente am Ende in die Liste einfuegen lassen.
     */
    @Test
    fun testeFuegeEinListenende() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        assertEquals(10, titelListe.gibLaenge())
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))
    }

    /**
     * Testet, ob beim Einfuegen am Anfang korrekt verschoben wird.
     */
    @Test
    fun testeEinfuegenUndEntfernen() {
        val titelListe = erzeugeListe()
        titelListe.fuegeEin(_testTitel[6], 0)
        titelListe.fuegeEin(_testTitel[5], 0)
        titelListe.entferne(1)
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[5]), "titelListe soll den Test-Titel 5 enthalten")
    }

    /**
     * Testet das Vergroessern der Liste waehrend des Einfuegens vieler
     * Elemente.
     */
    @Test
    fun testeFuegeEinVergroessern() {
        val titelListe = erzeugeListe()
        for (i in 0..11) {
            titelListe.fuegeEin(_testTitel[i % 10], 0)
        }
        assertEquals(12, titelListe.gibLaenge())

        assertEquals(_testTitel[0], titelListe.gibTitel(11))
        assertEquals(_testTitel[1], titelListe.gibTitel(10))
    }

    /**
     * Testet die Methode fuegeEin(String, int) der Liste. Dabei wird getestet,
     * ob sich die Liste richtig verhaelt, wenn man versucht ein Element an
     * ungueltigen Indizes einzufuegen.
     */
    @Test
    fun testeFuegeEinNegativtest() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        var exception = false
        val falscherTitel = Titel("", "", "", 0, "", 0)
        try {
            titelListe.fuegeEin(falscherTitel, -1)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertFalse(
                titelListe.enthaelt(falscherTitel),
                "titelListe darf nicht den Titel 'falscherTitel' enthalten")
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))

        exception = false
        try {
            titelListe.fuegeEin(falscherTitel, 11)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertFalse(
                titelListe.enthaelt(falscherTitel),
                "titelListe darf nicht den Titel 'falscherTitel' enthalten")
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))
    }

    /**
     * Testet die Methode entferne(int) der Liste. Dabei wird getestet, ob sich
     * Elemente mittig aus der Liste entfernen lassen.
     */
    @Test
    fun testeEntferneMittig() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        titelListe.entferne(5)
        assertEquals(9, titelListe.gibLaenge())
        Assertions.assertFalse(titelListe.enthaelt(_testTitel[5]),
                "titelListe darf nicht den Test-Titel 5 enthalten")
        assertEquals(_testTitel[4], titelListe.gibTitel(4))
        assertEquals(_testTitel[6], titelListe.gibTitel(5))
        assertEquals(_testTitel[9], titelListe.gibTitel(8))
    }

    /**
     * Testet die Methode entferne(int) der Liste. Dabei wird getestet, ob sich
     * Elemente am Anfang aus der Liste entfernen lassen.
     */
    @Test
    fun testeEntferneListenanfang() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        titelListe.entferne(0)
        assertEquals(9, titelListe.gibLaenge())
        Assertions.assertFalse(titelListe.enthaelt(_testTitel[0]),
                "titelListe darf nicht den Test-Titel 0 enthalten")
        assertEquals(_testTitel[1], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(8))
    }

    /**
     * Testet die Methode entferne(int) der Liste. Dabei wird getestet, ob sich
     * Elemente am Ende aus der Liste entfernen lassen.
     */
    @Test
    fun testeEntferneListenende() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        titelListe.entferne(9)
        assertEquals(9, titelListe.gibLaenge())
        Assertions.assertFalse(titelListe.enthaelt(_testTitel[9]),
                "titelListe darf nicht den Test-Titel 9 enthalten")
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[8], titelListe.gibTitel(8))
    }

    /**
     * Testet, ob sich Elemente aus einer Liste loeschen lassen, die mehr als 10
     * Elemente enthaelt.
     */
    @Test
    fun testeLoeschenAusGrosserListe() {
        val titelListe = erzeugeListe()
        for (i in 0..11) {
            titelListe.fuegeEin(_testTitel[i % 10], titelListe.gibLaenge())
        }
        titelListe.entferne(0)
        titelListe.entferne(0)
        titelListe.entferne(0)
        assertEquals(9, titelListe.gibLaenge())
        assertEquals(_testTitel[3], titelListe.gibTitel(0))
    }

    /**
     * Testet die Methode entferne(int) der Liste. Dabei wird getestet, ob sich
     * die Liste richtig verhaelt, wenn man versucht ein Element an ungueltigen
     * Indizes zu entfernen.
     */
    @Test
    fun testeEntferneNegativtest() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        var exception = false
        try {
            titelListe.entferne(-1)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))

        exception = false
        try {
            titelListe.entferne(10)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))
    }

    /**
     * Testet die Methode gibTitel(int) der Liste. Dabei wird getestet, ob sich
     * Elemente mittig in der Liste abfragen lassen.
     */
    @Test
    fun testeGibMittig() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        assertEquals(_testTitel[5], titelListe.gibTitel(5))
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[5]), "titelListe soll den Test-Titel 5 enthalten")
    }

    /**
     * Testet die Methode gibTitel(int) der Liste. Dabei wird getestet, ob sich
     * Elemente am Anfang in der Liste abfragen lassen.
     */
    @Test
    fun testeGibListenanfang() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[0]), "titelListe soll den Test-Titel 0 enthalten")
    }

    /**
     * Testet die Methode gibTitel(int) der Liste. Dabei wird getestet, ob sich
     * Elemente am Ende in der Liste abfragen lassen.
     */
    @Test
    fun testeGibListenende() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        assertEquals(_testTitel[9], titelListe.gibTitel(9))
        assertEquals(10, titelListe.gibLaenge())
        Assertions.assertTrue(titelListe
                .enthaelt(_testTitel[9]), "titelListe soll den Test-Titel 9 enthalten")
    }

    /**
     * Testet die Methode gibTitel(int) der Liste. Dabei wird getestet, ob sich
     * die Liste richtig verhaelt, wenn man versucht ein Element an ungueltigen
     * Indizes abzufragen.
     */
    @Test
    fun testeGibNegativtest() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        var exception = false
        try {
            titelListe.gibTitel(-1)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))

        exception = false
        try {
            titelListe.gibTitel(10)
        } catch (ie: IndexOutOfBoundsException) {
            exception = true
        }

        Assertions.assertTrue(exception, "es soll eine Exception aufgetreten sein")
        assertEquals(10, titelListe.gibLaenge())
        assertEquals(_testTitel[0], titelListe.gibTitel(0))
        assertEquals(_testTitel[9], titelListe.gibTitel(9))
    }

    /**
     * Testet die Methode leere der Liste.
     */
    @Test
    fun testeLeere() {
        val titelListe = erzeugeListe()
        for (i in 0..9) {
            titelListe.fuegeEin(_testTitel[i], titelListe.gibLaenge())
        }
        assertEquals(10, titelListe.gibLaenge())
        titelListe.leere()
        assertEquals(0, titelListe.gibLaenge())
        Assertions.assertFalse(titelListe.enthaelt(_testTitel[0]),
                "titelListe darf nicht den Test-Titel 0 enthalten")
        Assertions.assertFalse(titelListe.enthaelt(_testTitel[9]),
                "titelListe darf nicht den Test-Titel 0 enthalten")
    }

    /**
     * Testet das Vergroessern der Liste waehrend des Einfuegens vieler
     * Elemente.
     */
    @Test
    fun testeVergroessern() {
        val titelListe = erzeugeListe()
        for (i in 0..99) {
            titelListe.fuegeEin(_testTitel[i % 10], 0)
        }
        assertEquals(100, titelListe.gibLaenge())

        for (i in 0..99) {
            assertEquals(_testTitel[i % 10], titelListe.gibTitel(99 - i))
        }
    }

    /**
     * Erzeugt eine neue TitelListe.
     *
     * @return Eine leere Liste.
     */
    fun erzeugeListe(): TitelListe {
        return ArrayTitelListe()
    }
}
