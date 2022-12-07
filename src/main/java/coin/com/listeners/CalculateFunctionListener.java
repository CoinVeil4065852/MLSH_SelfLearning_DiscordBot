package coin.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CalculateFunctionListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        String[] chars = event.getMessage().getContentRaw().replaceAll("\\s+", "").replaceAll("=", "").split("");
        List<String> formula = new ArrayList<>();
        String lastNumber = "";
        for (String s : chars) {
            try {
                Integer.parseInt(s);
                lastNumber += s;
            } catch (Exception e) {
                if (s.equals(".")) {

                    lastNumber += s;
                } else {
                    if (!lastNumber.isEmpty()) {
                        formula.add(lastNumber);
                        lastNumber = "";
                    }
                    formula.add(s);
                }
            }
        }
        if (!lastNumber.isEmpty()) formula.add(lastNumber);
        if(formula.size()<=1)return;
        System.out.println(formula);
        String ans =doMath(formula);
        if (ans== null)return;
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("答案:").setDescription(ans).setColor(0xffea00);
        event.getMessage().replyEmbeds(eb.build()).queue();
    }

    String doMath(List<String> formula) {
//"(",")"
        for (int i = 0; i < formula.size(); i++) {
            if (formula.get(i).equals("(")) {
                int bracketsCount = 0;
                for (int j = i + 1; j < formula.size(); j++) {
                    if (formula.get(j).equals("("))
                        bracketsCount++;
                    if (formula.get(j).equals(")")) {
                        if (bracketsCount == 0) {
                            doMath(formula.subList(i + 1, j));
                            formula.remove(i);
                            formula.remove(i + 1);
                            break;
                        }
                        bracketsCount--;
                    }
                }
            }
        }


// "^"
        for (int i = 0; i < formula.size(); i++) {
            String s = formula.get(i);
            if (s.equals("^")) {
                double n1 = Double.parseDouble(formula.get(i - 1));
                double n2 = Double.parseDouble(formula.get(i + 1));
                formula.set(i, String.valueOf(Math.pow(n1, n2)));
                formula.remove(i + 1);
                formula.remove(i - 1);
                System.out.println(formula);
                i--;
            }
        }
// "*","/"
        for (int i = 0; i < formula.size(); i++) {
            String s = formula.get(i);
            if (s.equals("*") || s.equalsIgnoreCase("x") || s.equals("÷") || s.equals("/")) {
                double n1 = Double.parseDouble(formula.get(i - 1));
                double n2 = Double.parseDouble(formula.get(i + 1));
                if (s.equals("*") || s.equalsIgnoreCase("x"))
                    formula.set(i, String.valueOf(n1 * n2));
                else
                    formula.set(i, String.valueOf(n1 / n2));
                formula.remove(i + 1);
                formula.remove(i - 1);
                System.out.println(formula);
                i--;
            }
        }


// "+","-"
        for (int i = 0; i < formula.size(); i++) {
            String s = formula.get(i);
            if (s.equals("+") || s.equals("-")) {
                double n1 = Double.parseDouble(formula.get(i - 1));
                double n2 = Double.parseDouble(formula.get(i + 1));
                if (s.equals("+"))
                    formula.set(i, String.valueOf(n1 + n2));
                else
                    formula.set(i, String.valueOf(n1 - n2));

                formula.remove(i + 1);
                formula.remove(i - 1);
                System.out.println(formula);
                i--;
            }


        }
        if(formula.size()>1)return null;
        return formula.get(0);
    }
}
