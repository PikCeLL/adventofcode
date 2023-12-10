package framework.tests

import framework.src.IDailyPuzzle
import org.testng.annotations.Test
import kotlin.test.assertEquals

abstract class ADailyTest<T : IDailyPuzzle> {
    protected var instance: T = createInstance()
    protected abstract fun createInstance(): T
    protected abstract fun getInputPuzzle1(): String
    protected abstract fun getResultPuzzle1(): Long
    protected abstract fun getInputPuzzle2(): String
    protected abstract fun getResultPuzzle2(): Long

    @Test
    fun testPuzzle1() {
        assertEquals(getResultPuzzle1(), instance.getResultPuzzle1(getInputPuzzle1()))
    }

    @Test
    fun testPuzzle2() {
        assertEquals(getResultPuzzle2(), instance.getResultPuzzle2(getInputPuzzle2()))
    }
}