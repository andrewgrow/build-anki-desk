package ankideckbuilder.testing

import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import javax.swing.SwingUtilities
import org.junit.After
import org.junit.Before

abstract class ComponentTest {
    protected lateinit var lifecycle: LifecycleRegistry
        private set

    @Before
    fun setUpLifecycle() = runOnUiThread {
        lifecycle = LifecycleRegistry()
    }

    @After
    fun tearDownLifecycle() = runOnUiThread {
        if (::lifecycle.isInitialized) {
            lifecycle.destroy()
        }
    }

    protected fun runOnUiThread(block: () -> Unit) {
        if (SwingUtilities.isEventDispatchThread()) {
            block()
        } else {
            SwingUtilities.invokeAndWait(block)
        }
    }
}
