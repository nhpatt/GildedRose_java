package com.gildedrose

const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val BRIE = "Aged Brie"
const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
const val CONJURED = "Conjured Mana Cake"

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {

        items.forEach {

            when {
                it.name == BRIE -> inc(it)
                it.name == BACKSTAGE -> incBackStage(it)
                else -> dec(it)
            }

            if (it.name != SULFURAS) {
                it.sellIn -= 1
            }

            if (it.sellIn < 0) {
                when {
                    it.name == BRIE -> inc(it)
                    it.name == BACKSTAGE -> it.quality = 0
                    else -> dec(it)
                }
            }
        }
    }

    private fun incBackStage(item: Item) {
        inc(item)

        if (item.sellIn < 11) {
            inc(item)
        }
        if (item.sellIn < 6) {
            inc(item)
        }
    }

    private fun inc(item: Item) {
        if (item.quality < 50) {
            item.quality += 1
        }
    }

    private fun dec(item: Item) {
        if (item.quality > 0 && item.name != SULFURAS) {
            item.quality -= 1
        }
    }

}

