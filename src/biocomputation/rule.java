/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomputation;

/**
 *
 * @author user
 */
public class rule {

    public int[] cond = new int[5];
    public int[] rule = new int[6];

    public void geneRule() {
        for (int i = 0; i < 5; i++) {
            cond[i] = (int) Math.round(Math.random());
            rule[i] = cond[i];
        }
        out = (int) Math.round(Math.random());
        rule[5] = out;

    }

    public void inputRule(String newRule) {
        newRule = newRule.replace(" ", "");
        for (int i = 0; i < newRule.length(); i++) {
            if (i < newRule.length() - 1) {
                rule[i] = Integer.parseInt("" + newRule.charAt(i));
                cond[i] = Integer.parseInt("" + newRule.charAt(i));
            } else {
                rule[i] = Integer.parseInt("" + newRule.charAt(i));
                out = Integer.parseInt("" + newRule.charAt(i));
            }
        }

    }

    public String getCond() {
        String temp = "";
        for (int i = 0; i < cond.length; i++) {
            temp+=cond[i]+"";
        }
        return temp;
    }

    public void setCond(int[] cond) {
        this.cond = cond;
    }

    public String getOut() {
        return out+"";
    }

    public void setOut(int out) {
        this.out = out;
    }
    public int out;

    public String getRule() {
        String s = "";
        for (int i = 0; i < rule.length; i++) {
            s+=rule[i]+"";
        }
        return s;
    }
    
    
    
    public String toString(){        
        return("cond: "+getCond()+"\noutput: "+getOut());
    }

    public rule() {

        
    }
}
