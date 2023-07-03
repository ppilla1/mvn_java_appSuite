/**
 * Convert short array inits .e.g. {1,2,3} to "\u0001\u0002\u0003"
 */
package io.explore.translator.arraylistinit;

import io.antlr.arraylist.ArrayListBaseListener;
import io.antlr.arraylist.ArrayListParser;
import io.explore.common.Translate;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.tree.TerminalNode;

@Log4j2
public class ShortToUnicodeStringTranslator extends ArrayListBaseListener implements Translate {

    private StringBuilder translation = new StringBuilder();

    @Override
    public String translate() {
        return translation.toString();
    }

    /** Translate { to " */
    @Override
    public void enterInit(ArrayListParser.InitContext ctx) {
        translation.append("\"");
        log.debug("translating {} to \"", ctx.getChild(0));
    }

    /** Translate } to " */
    @Override
    public void exitInit(ArrayListParser.InitContext ctx) {
        translation.append("\"");
        log.debug("translating {} to \"", ctx.getChild(ctx.getChildCount() - 1));
    }

    /** Translate integers to 4-digit hexadecimal strings prefixed with \\u */
    @Override
    public void enterValue(ArrayListParser.ValueContext ctx) {
        if (null != ctx.INT()) {
            int value = Integer.parseInt(ctx.INT().getText());
            String valueTranslation = String.format("\\u%04x", value);
            translation.append(valueTranslation);
            log.debug("translating {} to {}", ctx.getText(), valueTranslation);
        }
    }
}
