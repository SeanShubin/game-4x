package com.seanshubin.game_4x.prototype3

import com.seanshubin.game_4x.prototype3.Item.Companion.toItem
import org.junit.Test
import kotlin.test.assertEquals

class FormatTest {
    @Test
    fun itemStructure() {
        assertEquals(Item(), Format.parseItem("{}"))
        assertEquals(mapOf("name" to "colonizer").toItem(), Format.parseItem("{colonizer}"))
        assertEquals(mapOf("name" to "colonizer").toItem(), Format.parseItem("{name=colonizer}"))
        assertEquals(
            mapOf(
                "name" to "gatherer",
                "resource" to "food",
                "activated" to "false"
            ).toItem(), Format.parseItem("{gatherer resource=food activated=false}")
        )
        assertEquals(
            mapOf(
                "name" to "gatherer",
                "resource" to "food",
                "activated" to "false"
            ).toItem(), Format.parseItem("{name=gatherer resource=food activated=false}")
        )
    }

    @Test
    fun itemValueTypes() {
        assertEquals(
            mapOf("name" to "node").toItem(),
            Format.parseItem("{node}")
        )
        assertEquals(
            mapOf("resource" to "food").toItem(),
            Format.parseItem("{resource=food}")
        )
        assertEquals(
            mapOf("activated" to "false").toItem(),
            Format.parseItem("{activated=false}")
        )
        assertEquals(
            mapOf("density" to "6").toItem(),
            Format.parseItem("{density=6}")
        )
        assertEquals(
            mapOf("owner" to "\$owner").toItem(),
            Format.parseItem("{owner=\$owner}")
        )
        assertEquals(
            mapOf(
                "name" to "node",
                "resource" to "food",
                "activated" to "false",
                "density" to "6",
                "owner" to "\$owner"
            ).toItem(), Format.parseItem("{node resource=food activated=false density=6 owner=\$owner}")
        )
    }
}

/*
name
primitive
  string
  boolean
  int
alias for primitive
attribute
alias for attribute
item
call
value
  string
  boolean
  int
  item
alias for value
namedValue

 */