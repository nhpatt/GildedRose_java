package com.GildedRose

import com.gildedrose.GildedRoseAlt
import com.gildedrose.Item
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals


class GildedRoseAltTest {

    @Test
    fun foo() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRoseAlt(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun should_never_changes_quailty_of_Sulfuras() {
        val (_, _, quality) = Item("Sulfuras, Hand of Ragnaros", 0, 80)
        val sut = GildedRoseAlt(Arrays.asList(Item("Sulfuras, Hand of Ragnaros", 0, 80)).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(80, quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_never_changes_sellIn_of_Sulfuras() {
        val sulfuras = Item("Sulfuras, Hand of Ragnaros", 0, 80)
        val sut = GildedRoseAlt(Arrays.asList(sulfuras).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(0, sulfuras.sellIn)
    }


    @Test
    @Throws(Exception::class)
    fun should_lower_the_sellIn_by_one_for_normal_items() {
        val normalItem = Item("+5 Dexterity Vest", 10, 20)
        val sut = GildedRoseAlt(Arrays.asList(normalItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(9, normalItem.sellIn)
    }

    @Test
    @Throws(Exception::class)
    fun should_lower_the_quality_by_one_for_normal_items() {
        val normalItem = Item("+5 Dexterity Vest", 10, 20)
        val sut = GildedRoseAlt(Arrays.asList(normalItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(19, normalItem.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_not_lower_the_quality_below_zero() {
        val normalItem = Item("+5 Dexterity Vest", 10, 0)
        val sut = GildedRoseAlt(Arrays.asList(normalItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(0, normalItem.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_lower_the_quality_twice_as_fast_once_the_sell_in_date_has_passed() {
        val normalItem = Item("+5 Dexterity Vest", -1, 25)
        val sut = GildedRoseAlt(Arrays.asList(normalItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(23, normalItem.quality)
    }


    @Test
    @Throws(Exception::class)
    fun should_increase_the_quality_of_aged_brie_as_it_gets_older() {
        val agedBrie = Item("Aged Brie", 10, 25)
        val sut = GildedRoseAlt(Arrays.asList(agedBrie).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(26, agedBrie.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_double_the_increase_in_quality_of_aged_brie_after_its_sell_by_date_has_passed() {
        val agedBrie = Item("Aged Brie", -2, 25)
        val sut = GildedRoseAlt(Arrays.asList(agedBrie).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(27, agedBrie.quality)
    }


    @Test
    @Throws(Exception::class)
    fun should_not_increase_the_quality_of_aged_brie_over_50() {
        val agedBrie = Item("Aged Brie", 10, 50)
        val sut = GildedRoseAlt(Arrays.asList(agedBrie).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(50, agedBrie.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_lower_backstage_passes_to_zero_quality_once_concert_has_happened() {
        val backStagePass = Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)
        val sut = GildedRoseAlt(Arrays.asList(backStagePass).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(0, backStagePass.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_increase_backstage_passes_quality_by_1_when_the_concert_is_more_than_10_days_away() {
        val backStagePass = Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)
        val sut = GildedRoseAlt(Arrays.asList(backStagePass).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(21, backStagePass.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_increase_backstage_passes_quality_by_2_when_the_concert_is_10_days_or_less_away() {
        val backStagePass = Item("Backstage passes to a TAFKAL80ETC concert", 10, 27)
        val sut = GildedRoseAlt(Arrays.asList(backStagePass).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(29, backStagePass.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_increase_backstage_passes_quality_by_3_when_the_concert_is_5_days_or_less_away() {
        val backStagePass = Item("Backstage passes to a TAFKAL80ETC concert", 5, 44)
        val sut = GildedRoseAlt(Arrays.asList(backStagePass).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(47, backStagePass.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_not_increase_backstage_passes_above_a_quality_of_50() {
        val backStagePassMoreThan10DaysAway = Item("Backstage passes to a TAFKAL80ETC concert", 15, 50)
        val backStagePass10DaysAway = Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        val backStagePass5DaysAway = Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)

        val sut = GildedRoseAlt(Arrays.asList(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(50, backStagePassMoreThan10DaysAway.quality)
        assertEquals(50, backStagePass10DaysAway.quality)
        assertEquals(50, backStagePass5DaysAway.quality)
    }


    @Test
    @Throws(Exception::class)
    fun should_reduce_conjured_item_by_2_when_sell_in_date_has_not_passed() {
        val conjuredItem = Item("Conjured Mana Cake", 3, 6)
        val sut = GildedRoseAlt(Arrays.asList(conjuredItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(4, conjuredItem.quality)
    }

    @Test
    @Throws(Exception::class)
    fun should_reduce_conjured_item_by_4_when_sell_in_date_has_passed() {
        val conjuredItem = Item("Conjured Mana Cake", 0, 6)
        val sut = GildedRoseAlt(Arrays.asList(conjuredItem).toTypedArray() as Array<Item>)

        sut.updateQuality()

        assertEquals(2, conjuredItem.quality)
    }

}