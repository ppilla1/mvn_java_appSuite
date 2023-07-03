package io.explore;

import io.antlr.arraylist.ArrayListLexer;
import io.antlr.arraylist.ArrayListParser;
import io.explore.translator.arraylistinit.ShortToOddEvenStringTranslator;
import io.explore.translator.arraylistinit.ShortToUnicodeStringTranslator;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@Log4j2
public class AppTest {

    @Test
    public void testParsingArrayListInit() {
        try {

            // Create CharStream that reads from file
            CharStream input = CharStreams.fromStream(this.getClass().getResourceAsStream("/ArrayLIst.txt"));

            // Create lexer that feeds off of input CharStream
            ArrayListLexer lexer = new ArrayListLexer(input);

            // Create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create parser that feeds off the token buffer
            ArrayListParser parser = new ArrayListParser(tokens);

            // Begin parsing file
            ParseTree tree = parser.init();

            String parsedTree = tree.toStringTree(parser);
            assertNotNull(parsedTree);
            assertTrue(!parsedTree.isBlank() && !parsedTree.isBlank());

            log.info("{}", parsedTree);
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
            assertTrue(false);
        }
    }

    @Test
    public void test_Multi_Translation_Of_Parsed_ArrayListInit(){
        String arrayInitStr = "{1,{2,3}}";
        String transStrONE = "\"\\u0001\"\\u0002\\u0003\"\"";
        String transStrTWO = "{\"O\",{\"E\",\"O\"}}";

        // create charstream from String
        CharStream input = CharStreams.fromString(arrayInitStr);

        // create lexer to feed off of input
        ArrayListLexer lexer = new ArrayListLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create parser that feeds off the token buffer
        ArrayListParser parser = new ArrayListParser(tokens);

        // create AST (Abstract Syntax Tree) from parser
        ParseTree ast = parser.init();

        // create generic parse tree walker that can trigger callbacks in listener
        ParseTreeWalker walker = new ParseTreeWalker();

        // walk the tree created during the parse, trigger callbacks
        ShortToUnicodeStringTranslator shortToUnicodeStringTranslator = new ShortToUnicodeStringTranslator();
        walker.walk(shortToUnicodeStringTranslator, ast);

        ShortToOddEvenStringTranslator shortToOddEvenStringTranslator = new ShortToOddEvenStringTranslator();
        walker.walk(shortToOddEvenStringTranslator, ast);

        // validation
        String actualTransONE = shortToUnicodeStringTranslator.translate();
        log.info("Translation ONE  : {}", actualTransONE);
        assertEquals(transStrONE, actualTransONE);

        String actualTransTWO = shortToOddEvenStringTranslator.translate();
        log.info("Translation TWO  : {}", actualTransTWO);
        assertEquals(transStrTWO, actualTransTWO);

    }
}
