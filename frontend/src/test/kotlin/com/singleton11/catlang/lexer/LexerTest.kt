package com.singleton11.catlang.lexer

import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals


internal class LexerTest {
    @Test
    fun test() = doTest("simple-positive")

    private fun doTest(testName: String) {
        javaClass.classLoader.getResource("$testName.cat")?.file?.let { sourceFile ->
            val code = Files.readString(Path.of(sourceFile))
            val actualResult = Lexer(code).scan()

            javaClass.classLoader.getResource("$testName.result")?.file?.let { resultFile ->
                val expectedResult = Files.readString(Path.of(resultFile))
                assertEquals(actualResult.toString(), expectedResult)
            }
        }
    }
}