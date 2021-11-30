import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import utils.IDailyPuzzle
import kotlin.test.assertEquals

abstract class ADailyTest<T : IDailyPuzzle> {
    private lateinit var instance: T
    protected abstract fun createInstance(): T
    protected abstract fun getInputPuzzle1(): String
    protected abstract fun getResultPuzzle1(): Int
    protected abstract fun getInputPuzzle2(): String
    protected abstract fun getResultPuzzle2(): Int

    @BeforeTest
    fun setUp() {
        instance = createInstance()
    }

    @Test
    fun testPuzzle1() {
        assertEquals(instance.getResultPuzzle1(getInputPuzzle1()), getResultPuzzle1())
    }

    @Test
    fun testPuzzle2() {
        assertEquals(instance.getResultPuzzle2(getInputPuzzle2()), getResultPuzzle2())
    }
}