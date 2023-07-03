package io.explore.translator.arraylistinit;

import io.antlr.arraylist.ArrayListBaseListener;
import io.antlr.arraylist.ArrayListParser;
import io.explore.common.Translate;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ShortToOddEvenStringTranslator extends ArrayListBaseListener implements Translate {
    private StringBuilder translation = new StringBuilder();

    @Override
    public String translate() {
        return translation.toString();
    }

    @Override
    public void enterInit(ArrayListParser.InitContext ctx) {
        String targetTrans = ctx.getChild(0).getText();
        translation.append(targetTrans);
        log.debug("Init rule enter: translating {} to {}", targetTrans, targetTrans);
    }

    @Override
    public void exitInit(ArrayListParser.InitContext ctx) {
        String targetTrans = ctx.getChild(ctx.getChildCount() - 1).getText();
        translation.append(targetTrans);
        log.debug("Init rule exit: translating {} to {}", targetTrans, targetTrans);
    }

    @Override
    public void enterValue(ArrayListParser.ValueContext ctx) {
        if (null != ctx.INT()){
            int value = Integer.parseInt(ctx.INT().getText());
            String targetTrans = value % 2 == 0 ? "\"E\"" : "\"O\"";
            translation.append(targetTrans);
            log.debug("translating {} to {}", targetTrans, targetTrans);
        }
        log.debug("Value rule enter: {}, {}", ctx.getChildCount(), ctx.getText());

    }
}
