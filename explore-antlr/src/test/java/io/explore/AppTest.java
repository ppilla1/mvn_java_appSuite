package io.explore;

import io.antlr.arraylist.ArrayListLexer;
import io.antlr.arraylist.ArrayListParser;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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
}
