package com.gildedrose

class GildedRoseAlt(var items: Array<Item>) {

    fun updateQuality() =

            items.filter { it.name != SULFURAS }.forEach {

                it.sellIn -= 1

                if (isNormal(it)) {
                    decrementQuality(it)
                } else {
                    incrementQuality(it)
                }

                if (isConjured(it)) {
                    decrementQuality(it)
                }
            }

    private fun decrementQuality(item: Item) {

        item.quality -= if (item.sellIn < 0) 2 else 1

        if (item.quality < 0) item.quality = 0
    }

    private fun incrementQuality(item: Item) =
            if (item.name == BRIE) {
                incrementBrieQuality(item)
            } else {
                incrementBackstageQuality(item)
            }

    private fun incrementBrieQuality(item: Item) =
            inc(item, if (item.sellIn >= 0) 1 else 2)

    private fun incrementBackstageQuality(item: Item) =
            when {
                item.sellIn < 0 -> item.quality = 0
                item.sellIn < 5 -> inc(item, 3)
                item.sellIn < 10 -> inc(item, 2)
                else -> inc(item, 1)
            }

    private fun inc(item: Item, quantity: Int = 1) {
        item.quality += quantity
        if (item.quality + quantity > 50) item.quality = 50
    }

    private fun isNormal(item: Item): Boolean =
            item.name != BACKSTAGE && item.name != BRIE && item.name != SULFURAS

    private fun isConjured(it: Item) = it.name == CONJURED

}