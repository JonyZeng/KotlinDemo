import kotlinx.coroutines.CoroutineName
import kotlin.Result
import kotlin.concurrent.thread
import kotlin.coroutines.*

fun main(){
    val b = suspend {
        val a = hello2()
        a
    }
    val createCoroutine = b.startCoroutine(object : Continuation<Int> {
        override fun resumeWith(result: Result<Int>) {
            println("${result.getOrNull()}")
        }

        override val context: CoroutineContext
            get() = CoroutineName("Co-01")
    })
//    createCoroutine.resume(Unit)
}

suspend fun hello2() = suspendCoroutine<Int> {
    thread {
        Thread.sleep(1000)
        it.resume(10086)
    }
}