package com.singleton11.catlang.lexer

import com.singleton11.catlang.lexer.util.prettyPrint
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.fail


internal class LexerTest {
    @Test
    fun test() = doTest("simple-positive")

    private fun doTest(testName: String) {
        // Given
        val sourceFileName = "$testName.cat"
        val sourceFile = javaClass.classLoader.getResource(sourceFileName)?.file
            ?: fail("There are no source file with name $testName.cat")
        val code = Files.readString(Path.of(sourceFile))

        val resultFileName = "$testName.result"
        val resultFile = javaClass.classLoader.getResource(resultFileName)?.file
            ?: fail("There is no result file with name $testName.result")
        val expectedResult = Files.readString(Path.of(resultFile))

        // When
        val actualResult = Lexer(code).scan()

        // Then
        assertEquals(expectedResult, actualResult.prettyPrint())
    }
}