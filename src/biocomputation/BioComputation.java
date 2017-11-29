/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomputation;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class BioComputation {

    static rule[] dataset = new rule[32];

    public static int calcfit(Individual solution) {

        int fitness = 0;

        for (int i = 0; i < dataset.length; i++) {
            for (int j = 0; j < solution.genes.length; j++) {
                if (matchesCondition(dataset[j], solution.getRule(j))) {
                    if (matchesOutput(dataset[i], solution.getRule(i))) {
                        fitness++;
                    }
                    break;
                }
            }
        }

        return fitness;
    }

    public static boolean matchesCondition(rule dataExample, rule ruleBase) {
        return (dataExample.getCond().equals(ruleBase.getCond()));
    }

    public static boolean matchesOutput(rule dataExample, rule ruleBase) {
        return (dataExample.getOut().equals(ruleBase.getOut()));
    }

    public static void loadData() {
        Scanner sc = new Scanner(BioComputation.class.getResourceAsStream("data1.txt"));
        int i = 0;
        while (sc.hasNext() && i < 32) {
            String nextRule = sc.nextLine();
            rule newRule = new rule();
            newRule.inputRule(nextRule);
            dataset[i] = newRule;
            i++;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        int Population = 50;
        loadData();

        int i;
        int j;
//Generating genes and Individuals
        Individual[] poption = new Individual[Population];
        Individual[] offspring = new Individual[Population];
        for (i = 0; i < Population; i++) {
            poption[i] = new Individual();
            offspring[i] = new Individual();

            poption[i].fitness = calcfit(poption[i]);

        }

        for (int g = 0; g < 100; g++) {

            Random rn = new Random();

            for (int a = 0; a < Population; a++) {

                int parent1 = rn.nextInt(Population);
                int parent2 = rn.nextInt(Population);
                if (poption[parent1].fitness >= poption[parent2].fitness) {
                    for (int k = 0; k < 32; k++) {
                        offspring[a].genes[k].inputRule(poption[parent1].genes[k].getRule());
                    }

                } else {
                    for (int k = 0; k < 32; k++) {
                        offspring[a].genes[k].inputRule(poption[parent2].genes[k].getRule());
                    }

                }

            }

            //Crossover
            for (int c = 1; c < offspring.length - 1; c += 2) {
                Random rpoint = new Random();
                int randPoint = rpoint.nextInt(offspring[0].genes.length - 1);

                for (int d = 0; d <= randPoint; d++) {
                    rule temp1 = offspring[c].genes[d];
                    rule newRule = new rule();
                    newRule.inputRule(offspring[c + 1].genes[d].getRule());

                    offspring[c + 1].genes[d].inputRule(temp1.getRule());

                }
            }
            //Mutation
            int rateOfMutation = 5;
            Random mut = new Random();
            for (int c = 0; c < offspring.length; c++) {
                for (int e = 0; e < offspring[0].genes.length; e++) {

                    int mut1 = mut.nextInt(1000);

                    if (mut1 < rateOfMutation && c != 0) {

                        offspring[c].genes[e].geneRule();
                    }

                }

            }
            for (i = 0; i < Population; i++) {
                offspring[i].fitness = 0;
                offspring[i].fitness = calcfit(offspring[i]);
            }

            int bestfit = poption[0].fitness;
            int best = 0;

            for (int h = 0; h < Population; h++) {
                if (poption[h].fitness >= bestfit) {
                    bestfit = poption[h].fitness;

                    best = h;
                }
            }
            Individual temp = poption[best];

            int best1 = 0;

            for (int h = 0; h < Population; h++) {
                if (calcfit(poption[h]) >= bestfit) {
                    bestfit = calcfit(poption[h]);
                    best = h;
                }

            }
            Individual bestOffspring = offspring[best1];
            System.arraycopy(offspring, 0, poption, 0, poption.length);
            for (int k = 0; k < 32; k++) {
                poption[0].genes[k].inputRule(bestOffspring.genes[k].getRule());
            }

            for (int k = 0; k < poption.length; k++) {
                poption[k].fitness = calcfit(poption[k]);
            }

            int worstfit = poption[0].fitness;
            int worst = 0;
            for (int h = 0; h < Population; h++) {

                if (poption[h].fitness <= worstfit) {
                    worstfit = poption[h].fitness;
                    worst = h;
                }
            }
            offspring[worst] = temp;

            bestfit = 0;

            best = 0;

            for (int h = 0; h < Population; h++) {
                if (calcfit(poption[h]) >= bestfit) {
                    bestfit = calcfit(poption[h]);
                    best = h;
                }

            }

            int totalFitness = 0;
            for (int k = 0; k < poption.length; k++) {
                totalFitness += poption[k].fitness;

            }

            totalFitness /= poption.length;

            System.out.println("Generation: " + g + ", Best Fitness: " + calcfit(poption[best]) + ", Average Fitness: " + totalFitness);

        }
        for (int k = 0; k < Population; k++) {
            for (int l = 0; l < 32; l++) {
                System.out.println(poption[k].genes[l].toString());
            }
        }

    }
}
