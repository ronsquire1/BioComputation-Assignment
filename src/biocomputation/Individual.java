/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomputation;
//import java.util.Random;

/**
 *
 * @author user
 */
public class Individual {

    int answer;
    public rule[] genes;//= new rule[32];
    public int fitness;

    public Individual() {
        genes = new rule[32];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = new rule();
            genes[i].geneRule();
        }
        this.fitness = 0;
    }

    public rule getRule(int index) {
        return genes[index];
    }

//    @Override
//    public String toString() {
//        String genesString = "";
//        
//        for(int i = 0;i<genes.length;i++){
//            genesString+=Integer.toString(genes[i]);
//        }
//        
//        return "Individual{" + " genes=" + genesString + " fitness=" + fitness +'}';
//    }
}
