import com.icerockdev.ktor_features_ime.shared.Greeting
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class Test {
    @Test
    fun testRequests() {
        val greeting = Greeting()
        runBlocking {
            println("1")
            greeting.sendRequest()
            println("2")
            greeting.addSomeCookie()
            println("3")
            greeting.sendRequest()
            println("4")
        }
    }
}