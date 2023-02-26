package com.seanshubin.game_4x.game

import kotlin.test.Test
import kotlin.test.assertEquals

class ApiGameplayTest {
    @Test
    fun startWithColonizer() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        api.addThing(planetName, landIndex, node)
        api.addThing(planetName, landIndex, colonizer)
        val expected = Land(
            planetName,
            landIndex,
            node to 1,
            colonizer to 1
        )

        // when
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(
            expected.toLines().joinToString("\n"),
            actual.toLines().joinToString("\n")
        )

    }

    @Test
    fun colonize() {
        // given
        val planetName = "Planet A"
        val landIndex = 0
        val colonizer = Things.createColonizer()
        val node = Things.createNode("food", density = 6)
        val citizen = Things.createCitizen()
        val gatherer = Things.createGatherer("food")
        val api = createApi()
        api.createPlanet(planetName)
        api.createLand(planetName)
        api.addThing(planetName, landIndex, node)
        api.addThing(planetName, landIndex, colonizer)
        val expected = Land(
            planetName,
            landIndex,
            citizen to 1,
            gatherer to 1
        )

        // when
        api.colonize(planetName, landIndex, "colonize")
        val actual = api.getLand(planetName, landIndex)

        // then
        assertEquals(expected, actual)
    }

    private fun createApi(): Api = ApiImpl()
}
/*
colonize
{name:colonizer} - 1
{name:node, resource:food} > 0
{name:citizen} = 0
{name:citizen} + 1
{name:gatherer, resource:food} + 1

run-food-gatherer
a = {name:node, resource:food, activated:false } - 1
{name:node, resource:food, activated:true } + 1
{name:gatherer, resource:food, activated:false} - 1
{name:gatherer, resource:food, activated:true} + 1
{name:citizen, activated:false} - 1
{name:citizen, activated:true} + 1
{name:supply, resource:food} + a.density

new-citizens-enter
a = {name:citizen}
b = {name:supply, resource:food}
c = min(a,b)
{name:citizen} + c

build-farm
{name:gatherer, resource:food} < {name:node, resource:food}
{name:citizen, activated:false} - 1
{name:citizen, activated:true} + 1
{name:gatherer, resource:food} + 1

feed-citizen
{supply:citizen, resource:food} - 1
{name:citizen, activated:true} - 1
{name:citizen, activated:false} - 1

activated-citizens-leave
{name:citizen, activated:true} - 1

reset-gatherer
{name:gatherer, resource:food, activated:true} - 1
{name:gatherer, resource:food, activated:false} + 1

reset-node
{name:node, resource:food, activated:true } - 1
{name:node, resource:food, activated:false } + 1
*/

